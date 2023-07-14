package com.taskTracker.service;

import com.taskTracker.dto.RegisterRequest;
import com.taskTracker.mapper.UserMapper;
import com.taskTracker.repo.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private RegisterService registerService;

    @Test
    @DisplayName("Check if the user is registered properly")
    void register() {
        // given
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("Zoe");
        registerRequest.setEmail("zoeRose@gmail.com");
        registerRequest.setPassword("haslo1234");
        registerRequest.setPasswordRepetition("haslo1234");
        // when
        registerService.register(registerRequest);

        // then
        assertTrue(accountRepository.existsByEmail("zoeRose@gmail.com"));
    }
}
