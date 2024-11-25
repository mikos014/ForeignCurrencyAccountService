package pl.example.foreigncurrencyaccountservice.app.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rate {
    private String no;
    private String effectiveDate;
    private BigDecimal bid;
    private BigDecimal ask;
}

