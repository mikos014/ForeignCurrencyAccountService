package pl.example.foreigncurrencyaccountservice.app.service.currencyaccount;

import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.exception.CurrencyAccountExistsException;
import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.exception.CurrencyAccountFewAmountException;
import pl.example.foreigncurrencyaccountservice.app.domain.useraccount.exception.UserAccountNotFoundException;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CreateCurrencyAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CurrencyAccountDto;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.ExchangeMoneyDto;

import java.util.List;

public interface CurrencyAccountService {

    CurrencyAccountDto createCurrencyAccount(CreateCurrencyAccountDto dto) throws CurrencyAccountExistsException, UserAccountNotFoundException;

    List<CurrencyAccountDto> exchangeMoney(ExchangeMoneyDto dto) throws CurrencyAccountFewAmountException;
}
