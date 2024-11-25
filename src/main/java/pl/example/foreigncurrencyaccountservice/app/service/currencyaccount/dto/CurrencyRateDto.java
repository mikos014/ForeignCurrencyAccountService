package pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyEnum;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CurrencyRateDto {
    private CurrencyEnum currency;
    private BigDecimal sellRate;
    private BigDecimal buyRate;
}
