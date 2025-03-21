package com.hacksisters.reborn_market_backend.register;

import com.hacksisters.reborn_market_backend.product.ProductRepository;
import com.hacksisters.reborn_market_backend.user.User;
import com.hacksisters.reborn_market_backend.user.UserDtoRequest;
import com.hacksisters.reborn_market_backend.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class UserRegisterServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UserRegisterService userRegisterService;

    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    void testRegisterUser_Success() {

        String encodedPassword = Base64.getEncoder().encodeToString("password123".getBytes());
        UserDtoRequest request = new UserDtoRequest("test@example.com", "user", encodedPassword);

        when(userRepository.findByUsername(request.username())).thenReturn(Optional.empty());

        Map<String, String> response = userRegisterService.registerUser(request);

        assertEquals("Success", response.get("message"));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_UserAlreadyExists() {

        String encodedPassword = Base64.getEncoder().encodeToString("password123".getBytes());
        UserDtoRequest request = new UserDtoRequest("test@example.com","user", encodedPassword);

        when(userRepository.findByUsername(request.username())).thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class, () -> userRegisterService.registerUser(request));

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testFindAllUsers() {

        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userRegisterService.findAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testDeleteUserById_Success() {

        Long userId = 2L;

        when(userRepository.existsById(userId)).thenReturn(true);

        userRegisterService.deleteUserById(userId);
        verify(productRepository, times(1)).deleteByUserId(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUserById_UserNotFound() {

        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userRegisterService.deleteUserById(userId));

        verify(userRepository, never()).deleteById(userId);
    }
}
