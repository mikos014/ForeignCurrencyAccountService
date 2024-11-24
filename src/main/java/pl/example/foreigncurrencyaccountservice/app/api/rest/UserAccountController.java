package pl.example.foreigncurrencyaccountservice.app.api.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.example.foreigncurrencyaccountservice.app.domain.useraccount.exception.UserAccountNotFoundException;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.UserAccountService;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.CreateUserAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto.UserAccountDto;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user-account")
@RequiredArgsConstructor
class UserAccountController {
    private final UserAccountService userAccountService;

    @PostMapping
    ResponseEntity<UserAccountDto> createAccount(@RequestBody CreateUserAccountDto dto) {
        return new ResponseEntity<>(userAccountService.createUserAccount(dto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{accountId}")
    ResponseEntity<?> getAccount(@PathVariable UUID accountId) {
        try {
            return new ResponseEntity<>(userAccountService.getUserAccount(accountId), HttpStatus.OK);
        } catch (UserAccountNotFoundException e) {
            log.error(e.getMessage());
//            tymczasowe rozwiązanie, dalej bym zastosował @ExceptionHandler
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
