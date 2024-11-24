package pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateCurrencyAccountDto {
    private UUID userAccountUuid;
    private String requestedAccountCurrency;
}
