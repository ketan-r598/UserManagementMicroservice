package com.project.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.model.User;
import com.project.model.UserCredentials;
import com.project.repository.UserRepository;

public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        // Arrange
        User user1 = new User(1, "test1@example.com", "password1", "John", "Doe", "1234567890", "Address1", "ROLE_USER");
        User user2 = new User(2, "test2@example.com", "password2", "Jane", "Smith", "0987654321", "Address2", "ROLE_USER");
        List<User> userList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> result = userService.getAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));

        // Verify the interaction with the repository
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindUserByEmailAndPassword() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        User user = new User(1, email, password, "John", "Doe", "1234567890", "Address", "ROLE_USER");

        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.findUserByEmailAndPassword(email, password);

        // Assert
        assertEquals(Optional.of(user), result);

        // Verify the interaction with the repository
        verify(userRepository, times(1)).findByEmailAndPassword(email, password);
    }

    @Test
    public void testAuthenticateUser() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        User user = new User(1, email, password, "John", "Doe", "1234567890", "Address", "ROLE_USER");
        UserCredentials expectedCredentials = new UserCredentials(user.getEmail(),user.getPassword());

        when(userRepository.findByEmailAndPassword(email, password)).thenReturn(Optional.of(user));

        // Act
        UserCredentials result = userService.authenticateUser(email, password);

        // Assert
        assertEquals(expectedCredentials, result);

        // Verify the interaction with the repository
        verify(userRepository, times(1)).findByEmailAndPassword(email, password);
    }

    @Test
    public void testAddUser() {
        // Arrange
        User user = new User(1, "test@example.com", "password", "John", "Doe", "1234567890", "Address", "ROLE_USER");

        // Act
        userService.addUser(user);

        // Verify the interaction with the repository
        verify(userRepository, times(1)).save(user);
    }

//    @Test
//    public void testUpdateUser() {
//        // Arrange
//        User user = new User(1, "test@example.com", "password", "John", "Doe", "1234567890", "Address", "ROLE_USER");
//
//        // Act
//        userService.updateUser(user);
//
//        // Verify the interaction with the repository
//        verify(userRepository, times(1)).save(user);
//    }

    @Test
    public void testDeleteUser() {
        // Act
        userService.deleteUser();

        // Verify the interaction with the repository
        verify(userRepository, times(1)).deleteAll();
    }
}
