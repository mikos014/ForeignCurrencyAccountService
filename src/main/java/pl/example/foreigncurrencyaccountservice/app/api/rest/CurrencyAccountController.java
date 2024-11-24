package pl.example.foreigncurrencyaccountservice.app.api.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.exception.CurrencyAccountExistsException;
import pl.example.foreigncurrencyaccountservice.app.domain.useraccount.exception.UserAccountNotFoundException;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyAccountService;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CreateCurrencyAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CurrencyAccountDto;

@Slf4j
@RestController
@RequestMapping("/currency-account")
@RequiredArgsConstructor
class CurrencyAccountController {
    private final CurrencyAccountService currencyAccountDomainService;

    @PostMapping
    ResponseEntity<?> setUpNewCurrencyAccount(@RequestBody CreateCurrencyAccountDto dto) {
        try {
            CurrencyAccountDto newAccount = currencyAccountDomainService.createCurrencyAccount(dto);
            return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
        } catch (CurrencyAccountExistsException e) {
            log.error(e.getMessage());
//            tymczasowe rozwiązanie, dalej bym zastosował @ExceptionHandler
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (UserAccountNotFoundException e) {
            log.error(e.getMessage());
//            tymczasowe rozwiązanie, dalej bym zastosował @ExceptionHandler
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
