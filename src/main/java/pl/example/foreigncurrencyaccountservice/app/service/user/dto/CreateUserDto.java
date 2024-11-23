package pl.example.foreigncurrencyaccountservice.app.service.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserDto {

    private String uuid;

    private String name;

    private String surname;
}
