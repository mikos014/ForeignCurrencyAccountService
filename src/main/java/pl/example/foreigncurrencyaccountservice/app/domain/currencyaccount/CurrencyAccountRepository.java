package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount;

import org.springframework.data.jpa.repository.JpaRepository;

interface CurrencyAccountRepository extends JpaRepository<CurrencyAccountEntity, Long> {
}
