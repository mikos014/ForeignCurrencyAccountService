package pl.example.foreigncurrencyaccountservice.app.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExchangeRateResponse {

    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;
}
