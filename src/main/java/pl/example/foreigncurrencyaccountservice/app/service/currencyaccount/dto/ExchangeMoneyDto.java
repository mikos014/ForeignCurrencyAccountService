package pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExchangeMoneyDto {
    private UUID userAccountUuid;
    private BigDecimal amount;
    private CurrencyEnum currency;
    private boolean isSell;
    private LocalDateTime boughtDateTime;
}
