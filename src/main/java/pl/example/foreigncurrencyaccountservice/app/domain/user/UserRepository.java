package pl.example.foreigncurrencyaccountservice.app.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserEntity, Long> {

}
