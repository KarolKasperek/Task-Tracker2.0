package com.taskTracker.service;

import com.taskTracker.dto.RegisterDto;
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
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("Zoe");
        registerDto.setEmail("zoeRose@gmail.com");
        registerDto.setPassword("haslo1234");
        registerDto.setPasswordRepetition("haslo1234");
        // when
        registerService.register(registerDto);

        // then
        assertTrue(accountRepository.existsByEmail("zoeRose@gmail.com"));
    }
}
