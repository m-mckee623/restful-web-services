package com.example.restful_web_services.service;

import com.example.restful_web_services.pojo.User;
import com.example.restful_web_services.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserByUsername() {
        // Given
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(user);

        // When
        User result = userService.getUserByUsername(username);

        // Then
        assertThat(result).isEqualTo(user);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testSaveUser() {
        // Given
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        // When
        User result = userService.saveUser(user);

        // Then
        assertThat(result).isEqualTo(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testValidateUser_ValidUser() {
        // Given
        String username = "testuser";
        String password = "password";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        when(userRepository.findByUsername(username)).thenReturn(user);

        // When
        boolean result = userService.validateUser(username, password);

        // Then
        assertThat(result).isTrue();
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testValidateUser_InvalidUser() {
        // Given
        String username = "testuser";
        String password = "wrongpassword";
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        when(userRepository.findByUsername(username)).thenReturn(user);

        // When
        boolean result = userService.validateUser(username, password);

        // Then
        assertThat(result).isFalse();
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testValidateUser_UserNotFound() {
        // Given
        String username = "nonexistentuser";
        when(userRepository.findByUsername(username)).thenReturn(null);

        // When
        boolean result = userService.validateUser(username, "password");

        // Then
        assertThat(result).isFalse();
        verify(userRepository, times(1)).findByUsername(username);
    }
}