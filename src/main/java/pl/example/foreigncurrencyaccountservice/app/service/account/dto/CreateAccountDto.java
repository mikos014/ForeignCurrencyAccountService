package pl.example.foreigncurrencyaccountservice.app.service.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAccountDto {

//    private UUID uuid;
    private String name;
    private String surname;
    private String password;
}
