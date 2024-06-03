package bank.controllers;


import bank.models.User;
import bank.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getUserByIdTest() throws Exception {
        User user = new User(1L, "Alice", "alice@example.com", null);
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.email").value("alice@example.com"));

        verify(userService).getUserById(1L);
    }

    @Test
    void getAllUsersTest() throws Exception {
        User user1 = new User(1L, "Alice", "alice@example.com", null);
        User user2 = new User(2L, "Bob", "bob@example.com", null);
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(userService).getAllUsers();
    }

    @Test
    void createUserTest() throws Exception {
        User user = new User(null, "Charlie", "charlie@example.com", null);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Charlie\",\"email\":\"charlie@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Charlie"))
                .andExpect(jsonPath("$.email").value("charlie@example.com"));

        verify(userService).createUser(argThat(newUser ->
                newUser.getName().equals("Charlie") && newUser.getEmail().equals("charlie@example.com")
        ));
    }

    @Test
    void deleteUserTest() throws Exception {
        User user = new User(1L, "Alice", "alice@example.com", null);
        when(userService.getUserById(1L)).thenReturn(user);
        doNothing().when(userService).deleteUser(user);

        mockMvc.perform(delete("/users/{id}", 1))
                .andExpect(status().isOk());

        verify(userService).deleteUser(user);
    }
}


