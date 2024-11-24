package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyAccountService;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyEnum;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;

@Service
@RequiredArgsConstructor
class CurrencyAccountDomainService implements CurrencyAccountService {
    private final CurrencyAccountRepository currencyAccountRepository;

    public CurrencyAccountEntity createCurrencyAccount(CreateUserAccountDto dto) {
        var entity = new CurrencyAccountEntity(dto.getAmount(), CurrencyEnum.PLN);
        return currencyAccountRepository.saveAndFlush(entity);
    }
}
