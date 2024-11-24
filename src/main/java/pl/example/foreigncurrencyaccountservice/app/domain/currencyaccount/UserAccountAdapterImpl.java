package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.foreigncurrencyaccountservice.app.domain.useraccount.UserAccountDomainService;
import pl.example.foreigncurrencyaccountservice.app.domain.useraccount.exception.UserAccountNotFoundException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserAccountAdapterImpl implements UserAccountAdapter {
    private final UserAccountDomainService userAccountDomainService;

    public void appendCurrencyAccount(UUID userAccount, CurrencyAccountEntity currencyAccount) throws UserAccountNotFoundException {
        userAccountDomainService.appendCurrencyAccount(userAccount, currencyAccount);
    }
}
