package pl.example.foreigncurrencyaccountservice.app.domain.useraccount;


import org.springframework.data.jpa.repository.JpaRepository;

interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {

}
