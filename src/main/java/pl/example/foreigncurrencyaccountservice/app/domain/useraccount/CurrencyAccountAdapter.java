package pl.example.foreigncurrencyaccountservice.app.domain.useraccount;

import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.CurrencyAccountEntity;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;

public interface CurrencyAccountAdapter {
    CurrencyAccountEntity createCurrencyAccount(CreateUserAccountDto dto);
}
