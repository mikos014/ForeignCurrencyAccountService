package pl.example.foreigncurrencyaccountservice.app.domain.useraccount.exception;


public class UserAccountNotFoundException extends Exception {

    public UserAccountNotFoundException(String accountUuid) {
        super(String.format("User Account %s does not exist!", accountUuid));
    }
}
