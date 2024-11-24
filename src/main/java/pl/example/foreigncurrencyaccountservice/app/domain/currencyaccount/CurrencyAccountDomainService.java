package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount;

import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;

public interface CurrencyAccountDomainService {

    CurrencyAccountEntity createCurrencyAccount(CreateUserAccountDto dto);
}
