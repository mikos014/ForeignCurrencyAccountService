package pl.example.foreigncurrencyaccountservice.app.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;

    public UserEntity createNewUser(UserEntity user) {
        return userRepository.save(user);
    }

}
