package pl.example.foreigncurrencyaccountservice.app.domain.useraccount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.CurrencyAccountEntity;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CurrencyAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.UserAccountService;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.UserAccountDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserAccountDomainService implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountCurrencyAccountAdapter currencyAccountAdapter;

    @Transactional
    public UserAccountDto createUserAccount(CreateUserAccountDto dto) {
        var plnAccount = currencyAccountAdapter.createCurrencyAccount(dto);
        var userAccount = buildUserAccountEntity(dto, plnAccount);
        userAccount = userAccountRepository.save(userAccount);
        return buildUserAccount(userAccount);
    }

    private UserAccountDto buildUserAccount(UserAccountEntity userAccount) {
        var plnAccountEntity = userAccount.getCurrencyAccountSet().stream().toList().getFirst();
        var plnAccountDto = CurrencyAccountDto.builder()
                .amount(plnAccountEntity.getAmount())
                .currency(plnAccountEntity.getCurrency())
                .build();

        return UserAccountDto.builder()
                .name(userAccount.getName())
                .surname(userAccount.getSurname())
                .currencyAccounts(List.of(plnAccountDto))
                .build();
    }

    private UserAccountEntity buildUserAccountEntity(CreateUserAccountDto dto, CurrencyAccountEntity plnAccount) {
        return new UserAccountEntity(UUID.randomUUID(), dto.getName(), dto.getSurname(), Set.of(plnAccount));
    }
}
