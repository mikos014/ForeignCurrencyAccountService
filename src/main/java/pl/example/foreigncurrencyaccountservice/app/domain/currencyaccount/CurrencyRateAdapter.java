package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount;

import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyEnum;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CurrencyRateDto;

import java.math.BigDecimal;

public interface CurrencyRateAdapter {

    BigDecimal getSellRate(CurrencyEnum currencyEnum);
    BigDecimal getBuyRate(CurrencyEnum currencyEnum);
    CurrencyRateDto getRate(CurrencyEnum currencyEnum);
}
