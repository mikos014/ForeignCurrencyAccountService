package pl.example.foreigncurrencyaccountservice.app.domain.user;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    private String uuid;

    private String name;

    private String surname;

    private String password;
}

