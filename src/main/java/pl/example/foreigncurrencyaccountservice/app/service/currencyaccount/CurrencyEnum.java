package pl.example.foreigncurrencyaccountservice.app.service.currencyaccount;

public enum CurrencyEnum {
    PLN, USD;

    public static CurrencyEnum getEnum(String currency) {
//        case waluta podana w parametrze nie istnieje - wymaga obsługi
        return CurrencyEnum.valueOf(currency);
    }
}
