package pl.example.foreigncurrencyaccountservice.app.service.useraccount;

import pl.example.foreigncurrencyaccountservice.app.domain.useraccount.exception.UserAccountNotFoundException;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.UserAccountDto;

import java.util.UUID;

public interface UserAccountService {

    UserAccountDto createUserAccount(CreateUserAccountDto dto);

    UserAccountDto getUserAccount(UUID accountId) throws UserAccountNotFoundException;
}
