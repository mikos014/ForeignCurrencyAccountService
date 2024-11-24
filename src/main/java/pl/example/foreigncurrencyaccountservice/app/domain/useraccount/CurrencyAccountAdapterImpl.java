package pl.example.foreigncurrencyaccountservice.app.domain.useraccount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.CurrencyAccountEntity;
import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.CurrencyAccountDomainService;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;

@Service
@RequiredArgsConstructor
class CurrencyAccountAdapterImpl implements CurrencyAccountAdapter {

    private final CurrencyAccountDomainService currencyAccountDomainService;

    public CurrencyAccountEntity createCurrencyAccount(CreateUserAccountDto dto) {
        return currencyAccountDomainService.createCurrencyAccount(dto);
    }

}
