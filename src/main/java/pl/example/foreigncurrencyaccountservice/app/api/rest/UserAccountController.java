package pl.example.foreigncurrencyaccountservice.app.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.UserAccountService;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.UserAccountDto;

@RestController
@RequestMapping("/user-account")
@RequiredArgsConstructor
class UserAccountController {
    private final UserAccountService userAccountService;

    @PostMapping
    ResponseEntity<UserAccountDto> createAccount(@RequestBody CreateUserAccountDto dto) {

        return new ResponseEntity<>(userAccountService.createUserAccount(dto), HttpStatus.CREATED);
    }


}
