package pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExchangeMoneyDto {
    private UUID userAccountUuid;
    private BigDecimal amountSell;
    private String sellCurrency;
    private String buyCurrency;
}
