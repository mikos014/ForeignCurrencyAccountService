package pl.example.foreigncurrencyaccountservice.app.domain.useraccount;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.example.foreigncurrencyaccountservice.app.domain.useraccount.exception.UserAccountNotFoundException;

import java.util.Optional;
import java.util.UUID;

interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {

    Optional<UserAccountEntity> getUserAccountEntityByUuid(UUID uuid);

    default UserAccountEntity receiveUserAccountEntity(UUID uuid) throws UserAccountNotFoundException {
        return getUserAccountEntityByUuid(uuid)
                .orElseThrow(() -> new UserAccountNotFoundException(uuid.toString()));
    }
}
