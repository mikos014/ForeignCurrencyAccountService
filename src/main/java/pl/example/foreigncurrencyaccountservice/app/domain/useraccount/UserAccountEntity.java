package pl.example.foreigncurrencyaccountservice.app.domain.useraccount;


import jakarta.persistence.*;
import lombok.*;
import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.CurrencyAccountEntity;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserAccountEntity {

    @Id
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;
    private UUID uuid;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "users")
    private List<CurrencyAccountEntity> currencyAccountList;

    UserAccountEntity(UUID uuid, String name, String surname, List<CurrencyAccountEntity> currencyAccountList) {
        this.uuid = uuid;
        this.name = name;
        this.surname = surname;
        this.currencyAccountList = currencyAccountList;
    }
}

