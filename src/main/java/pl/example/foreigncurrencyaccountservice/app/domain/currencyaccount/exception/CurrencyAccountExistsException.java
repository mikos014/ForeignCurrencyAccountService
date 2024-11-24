package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.exception;


public class CurrencyAccountExistsException extends Exception {

    public CurrencyAccountExistsException(String accountUuid) {
        super(String.format("Currency Account %s exists!", accountUuid));
    }
}
