package pl.example.foreigncurrencyaccountservice.app.domain.useraccount;

import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.CurrencyAccountEntity;
import pl.example.foreigncurrencyaccountservice.app.domain.useraccount.exception.UserAccountNotFoundException;

import java.util.UUID;

public interface UserAccountDomainService {
    void appendCurrencyAccount(UUID userAccountUuid, CurrencyAccountEntity currencyAccount) throws UserAccountNotFoundException;
}
