package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.exception.CurrencyAccountExistsException;
import pl.example.foreigncurrencyaccountservice.app.domain.useraccount.exception.UserAccountNotFoundException;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyAccountService;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyEnum;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CreateCurrencyAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CurrencyAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;

import java.math.BigDecimal;

@Service
class CurrencyAccountDomainDomainServiceImpl implements CurrencyAccountDomainService, CurrencyAccountService {
    private final CurrencyAccountRepository currencyAccountRepository;
    private UserAccountAdapter userAccountAdapter;

    CurrencyAccountDomainDomainServiceImpl(CurrencyAccountRepository currencyAccountRepository, @Lazy UserAccountAdapter userAccountAdapter) {
        this.currencyAccountRepository = currencyAccountRepository;
        this.userAccountAdapter = userAccountAdapter;
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



}
