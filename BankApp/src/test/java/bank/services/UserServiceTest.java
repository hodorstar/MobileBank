package bank.services;

import bank.exceptions.UserNotFoundException;
import bank.models.User;
import bank.models.User;
import bank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@ActiveProfiles("test")
 class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    void testAllReadUser() {
        List<User> users = userService.getAllUsers();
        long iter = 1;
        for (User p : users) {
            assertEquals(iter, p.getId());
            iter++;
        }
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testGetUserBiId() {
        User user = userService.getUserById(3L);
        assertEquals(3L, user.getId());
        assertEquals("Charlie", user.getName());
        assertEquals("charlie@example.com", user.getEmail());
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testGetUserBiIdThrow() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(30L));
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testCreateUser() {
        User user = new User(null, "Mark",  "mark@example.com", null);
        userService.createUser(user);
        User findUser = userService.getUserById(6L);
        assertEquals(6L, findUser.getId());
        assertEquals("Mark", user.getName());
        assertEquals("mark@example.com", user.getEmail());
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testUpdateUser() {
        userService.updateUser(2L, "Bob", "new_bob@example.com");
        User findUser = userService.getUserById(2L);
        assertEquals(2L, findUser.getId());
        assertEquals("Bob", findUser.getName());
        assertEquals("new_bob@example.com", findUser.getEmail());
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testUpdateUserThrow() {
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(20L, "Bob", "new_bob@example.com"));
    }

    @Test
    @Sql("classpath:test_database.sql")
    void testDeleteUser() {
        User findUser = userService.getUserById(4L);
        userService.deleteUser(findUser);
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(4L));
    }
}
