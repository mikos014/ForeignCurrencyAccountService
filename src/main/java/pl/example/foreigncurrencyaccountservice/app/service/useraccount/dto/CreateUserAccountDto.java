package pl.example.foreigncurrencyaccountservice.app.service.useraccount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserAccountDto {

    private String uuid;

    private String name;

    private String surname;

    private BigDecimal amount;
}
