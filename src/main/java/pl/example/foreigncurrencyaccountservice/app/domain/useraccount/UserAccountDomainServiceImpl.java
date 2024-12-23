package pl.example.foreigncurrencyaccountservice.app.domain.useraccount;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.CurrencyAccountEntity;
import pl.example.foreigncurrencyaccountservice.app.domain.useraccount.exception.UserAccountNotFoundException;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CurrencyAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.UserAccountService;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.UserAccountDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
class UserAccountDomainServiceImpl implements UserAccountDomainService, UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final CurrencyAccountAdapter currencyAccountAdapter;

    UserAccountDomainServiceImpl(UserAccountRepository userAccountRepository, @Lazy CurrencyAccountAdapter currencyAccountAdapter) {
        this.userAccountRepository = userAccountRepository;
        this.currencyAccountAdapter = currencyAccountAdapter;
    }

    @Transactional
    @Override
    public UserAccountDto createUserAccount(CreateUserAccountDto dto) {
        var plnAccount = currencyAccountAdapter.createCurrencyAccount(dto);
        var userAccount = buildUserAccountEntity(dto, plnAccount);
        userAccount = userAccountRepository.save(userAccount);
        return buildUserAccount(userAccount);
    }

    @Override
    public UserAccountDto getUserAccount(UUID accountId) throws UserAccountNotFoundException {
        return buildUserAccount(userAccountRepository.receiveUserAccountEntity(accountId));
    }

    @Transactional
    @Override
    public void appendCurrencyAccount(UUID userAccountUuid, CurrencyAccountEntity currencyAccount) throws UserAccountNotFoundException {
        var userAccount = userAccountRepository.receiveUserAccountEntity(userAccountUuid);
        userAccount.getCurrencyAccountSet().add(currencyAccount);
        userAccountRepository.save(userAccount);
    }

    private UserAccountDto buildUserAccount(UserAccountEntity userAccount) {
        var plnAccountEntity = userAccount.getCurrencyAccountSet().stream().toList().getFirst();
        var plnAccountDto = CurrencyAccountDto.builder()
                .amount(plnAccountEntity.getAmount())
                .currency(plnAccountEntity.getCurrency())
                .build();

        return UserAccountDto.builder()
                .uuid(userAccount.getUuid())
                .name(userAccount.getName())
                .surname(userAccount.getSurname())
                .currencyAccounts(List.of(plnAccountDto))
                .build();
    }

    private UserAccountEntity buildUserAccountEntity(CreateUserAccountDto dto, CurrencyAccountEntity plnAccount) {
        return new UserAccountEntity(UUID.randomUUID(), dto.getName(), dto.getSurname(), Set.of(plnAccount));
    }
}
