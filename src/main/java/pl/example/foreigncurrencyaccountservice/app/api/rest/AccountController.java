package pl.example.foreigncurrencyaccountservice.app.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.example.foreigncurrencyaccountservice.app.service.account.AccountFacade;
import pl.example.foreigncurrencyaccountservice.app.service.account.dto.CreateAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.user.dto.CreateUserDto;

@RestController("/account")
@RequiredArgsConstructor
class AccountController {
    private final AccountFacade accountFacade;

    @PostMapping()
    ResponseEntity<?> createAccount(CreateAccountDto dto) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
