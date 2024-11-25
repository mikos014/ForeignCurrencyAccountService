package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.exception;


public class CurrencyAccountFewAmountException extends Exception {

    public CurrencyAccountFewAmountException(String currency) {
        super(String.format("Not enough money on %s Account!", currency));
    }
}
