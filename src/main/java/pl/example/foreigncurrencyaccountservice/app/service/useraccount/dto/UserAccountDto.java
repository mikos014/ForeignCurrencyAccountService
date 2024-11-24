package pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.dto.CurrencyAccountDto;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserAccountDto {
    private UUID uuid;
    private String name;
    private String surname;
    private List<CurrencyAccountDto> currencyAccounts;
}
