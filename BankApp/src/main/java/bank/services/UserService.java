package bank.services;

import bank.repository.UserPepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserPepository userRepository;

    public UserService(UserPepository userRepository) {
        this.userRepository = userRepository;
    }
}
