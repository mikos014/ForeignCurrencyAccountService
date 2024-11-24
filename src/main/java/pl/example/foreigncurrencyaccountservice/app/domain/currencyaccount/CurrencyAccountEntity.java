package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyEnum;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class CurrencyAccountEntity {

    @Id
    @GeneratedValue(generator = "accounts_id_seq", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "accounts_id_seq", sequenceName = "accounts_id_seq", allocationSize = 1)
    private Long id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    CurrencyAccountEntity(BigDecimal amount, CurrencyEnum currency) {
        this.amount = amount;
        this.currency = currency;
    }
}
