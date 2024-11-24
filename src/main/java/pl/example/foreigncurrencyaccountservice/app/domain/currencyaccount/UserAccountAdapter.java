package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount;

import pl.example.foreigncurrencyaccountservice.app.domain.useraccount.exception.UserAccountNotFoundException;

import java.util.UUID;

public interface UserAccountAdapter {
    void appendCurrencyAccount(UUID userAccount, CurrencyAccountEntity currencyAccount) throws UserAccountNotFoundException;
}
