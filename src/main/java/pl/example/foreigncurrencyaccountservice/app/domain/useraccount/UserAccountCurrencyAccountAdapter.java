package pl.example.foreigncurrencyaccountservice.app.domain.useraccount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.CurrencyAccountEntity;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyAccountService;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;

@Service
@RequiredArgsConstructor
class UserAccountCurrencyAccountAdapter {

    private final CurrencyAccountService currencyAccountService;

    CurrencyAccountEntity createCurrencyAccount(CreateUserAccountDto dto) {
        return currencyAccountService.createCurrencyAccount(dto);
    }

}
