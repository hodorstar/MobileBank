package bank.services;

import bank.exceptions.UserNotFoundException;
import bank.models.User;
import bank.models.User;

import bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("Could not find user with id: " + id)
                );
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void updateUser(Long id, String newName, String newEmail) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("Could not find user with id: " + id)
                );
        if (newName != null && !newName.isEmpty())
            user.setName(newName);
        if (newEmail != null && !newEmail.isEmpty())
            user.setEmail(newEmail);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
