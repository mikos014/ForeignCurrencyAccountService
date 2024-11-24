package pl.example.foreigncurrencyaccountservice.app.service.currencyaccount;

import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.CurrencyAccountEntity;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;

public interface CurrencyAccountService {

    CurrencyAccountEntity createCurrencyAccount(CreateUserAccountDto dto);
}
