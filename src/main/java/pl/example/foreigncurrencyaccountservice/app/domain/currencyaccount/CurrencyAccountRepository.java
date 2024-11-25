package pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.example.foreigncurrencyaccountservice.app.domain.currencyaccount.exception.CurrencyAccountExistsException;
import pl.example.foreigncurrencyaccountservice.app.service.currencyaccount.CurrencyEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface CurrencyAccountRepository extends JpaRepository<CurrencyAccountEntity, Long> {

    @Query("select ca from UserAccountEntity ua " +
            "join ua.currencyAccountSet ca " +
            "where ua.uuid = ?1 and ca.currency = ?2")
    Optional<CurrencyAccountEntity> accountExistsByUserAccountUserUuid(UUID userAccountUuid, CurrencyEnum currencyEnum);

    @Query("select ca from UserAccountEntity ua " +
            "join ua.currencyAccountSet ca " +
            "where ua.uuid = ?1 and (ca.currency = ?2 or ca.currency = 'PLN')")
    List<CurrencyAccountEntity> fetchTwoCurrencyAccounts(UUID userAccountUuid, CurrencyEnum currencyEnum);

    default void checkIfExists(UUID userAccountUuid, CurrencyEnum currencyEnum) throws CurrencyAccountExistsException {
        if (accountExistsByUserAccountUserUuid(userAccountUuid, currencyEnum).isPresent()) {
            throw new CurrencyAccountExistsException(userAccountUuid.toString());
        }
    }
}
