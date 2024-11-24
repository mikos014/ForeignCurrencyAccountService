package pl.example.foreigncurrencyaccountservice.app.service.useraccount;

import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.UserAccountDto;

public interface UserAccountService {

    UserAccountDto createUserAccount(CreateUserAccountDto dto);
}
