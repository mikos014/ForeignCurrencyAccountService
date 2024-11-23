package pl.example.foreigncurrencyaccountservice.app.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
