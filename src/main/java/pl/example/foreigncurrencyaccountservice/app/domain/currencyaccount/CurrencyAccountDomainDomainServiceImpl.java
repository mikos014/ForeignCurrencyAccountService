package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.exception.CurrencyAccountExistsException;
import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.exception.CurrencyAccountFewAmountException;
import pl.example.foreigncurrencyaccountservice.app.domain.useraccount.exception.UserAccountNotFoundException;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyAccountService;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyEnum;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CreateCurrencyAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CurrencyAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CurrencyRateDto;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.ExchangeMoneyDto;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
class CurrencyAccountDomainDomainServiceImpl implements CurrencyAccountDomainService, CurrencyAccountService {
    private final CurrencyAccountRepository currencyAccountRepository;
    private final UserAccountAdapter userAccountAdapter;
    private final CurrencyRateAdapter currencyRateAdapter;

    CurrencyAccountDomainDomainServiceImpl(CurrencyAccountRepository currencyAccountRepository, @Lazy UserAccountAdapter userAccountAdapter, CurrencyRateAdapter currencyRateAdapter) {
        this.currencyAccountRepository = currencyAccountRepository;
        this.userAccountAdapter = userAccountAdapter;
        this.currencyRateAdapter = currencyRateAdapter;
    }

    @Override
    public CurrencyAccountEntity createCurrencyAccount(CreateUserAccountDto dto) {
        var entity = new CurrencyAccountEntity(dto.getAmount(), CurrencyEnum.PLN);
        return currencyAccountRepository.saveAndFlush(entity);
    }

    @Override
    public CurrencyAccountDto createCurrencyAccount(CreateCurrencyAccountDto dto) throws CurrencyAccountExistsException, UserAccountNotFoundException {
        var currencyEnum = CurrencyEnum.getEnum(dto.getRequestedAccountCurrency());
        currencyAccountRepository.checkIfExists(dto.getUserAccountUuid(), currencyEnum);
        var entity = currencyAccountRepository.save(new CurrencyAccountEntity(BigDecimal.ZERO, currencyEnum));
        userAccountAdapter.appendCurrencyAccount(dto.getUserAccountUuid(), entity);
        return CurrencyAccountDto.builder()
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .build();
    }

    @Override
    public List<CurrencyAccountDto> exchangeMoney(ExchangeMoneyDto exchangeMoneyDto) throws CurrencyAccountFewAmountException {
        var currencyRateDto = currencyRateAdapter.getRate(exchangeMoneyDto.getCurrency());
        var currencyAccounts = currencyAccountRepository
                .fetchTwoCurrencyAccounts(exchangeMoneyDto.getUserAccountUuid(), exchangeMoneyDto.getCurrency());
        var accountMap = validateAndGroupCurrencyAccounts(exchangeMoneyDto, currencyAccounts);
        exchange(exchangeMoneyDto, currencyRateDto, accountMap);
        var plnAccount = currencyAccountRepository.saveAndFlush(accountMap.get(CurrencyEnum.PLN).getFirst());
        var foreignAccount = currencyAccountRepository.saveAndFlush(accountMap.get(exchangeMoneyDto.getCurrency()).getFirst());
        return List.of(buildCurrencyAccountDto(plnAccount), buildCurrencyAccountDto(foreignAccount));
    }

    private Map<CurrencyEnum, List<CurrencyAccountEntity>> validateAndGroupCurrencyAccounts(ExchangeMoneyDto dto,
                                                            List<CurrencyAccountEntity> currencyAccounts) throws CurrencyAccountFewAmountException {
        var currencyAccountMap = currencyAccounts.stream()
                .collect(Collectors.groupingBy(
                        CurrencyAccountEntity::getCurrency,
                        Collectors.toList()
                ));
        validateBaseAccountAmount(dto, currencyAccountMap.get(dto.getCurrency()).getFirst());
        return currencyAccountMap;
    }

    private void validateBaseAccountAmount(ExchangeMoneyDto dto,
                                           CurrencyAccountEntity currencyAccount) throws CurrencyAccountFewAmountException {
        if (dto.getAmount().compareTo(currencyAccount.getAmount()) > 0) {
            throw new CurrencyAccountFewAmountException(currencyAccount.getCurrency().name());
        }
    }

//  todo przemyśleć logikę i przetestować
    private void exchange(ExchangeMoneyDto exchangeMoneyDto,
                          CurrencyRateDto rateDto,
                          Map<CurrencyEnum, List<CurrencyAccountEntity>> accountMap) {
        if (exchangeMoneyDto.isSell()) {
            var availableAmount = accountMap.get(exchangeMoneyDto.getCurrency())
                    .getFirst().getAmount();
            accountMap.get(exchangeMoneyDto.getCurrency())
                    .getFirst().setAmount(availableAmount.subtract(exchangeMoneyDto.getAmount()));
            var plnAmount = accountMap.get(CurrencyEnum.PLN).getFirst().getAmount();
            accountMap.get(CurrencyEnum.PLN).getFirst().setAmount(plnAmount.multiply(rateDto.getBuyRate()));
        } else {
            var availableAmount = accountMap.get(CurrencyEnum.PLN)
                    .getFirst().getAmount();
            accountMap.get(exchangeMoneyDto.getCurrency())
                    .getFirst().setAmount(availableAmount.subtract(exchangeMoneyDto.getAmount()));
            var plnAmount = accountMap.get(CurrencyEnum.PLN).getFirst().getAmount();
            accountMap.get(CurrencyEnum.PLN).getFirst().setAmount(plnAmount.multiply(rateDto.getBuyRate()));
        }
    }

    private CurrencyAccountDto buildCurrencyAccountDto(CurrencyAccountEntity currencyAccount) {
        return CurrencyAccountDto.builder()
                .amount(currencyAccount.getAmount())
                .currency(currencyAccount.getCurrency())
                .build();
    }
}
