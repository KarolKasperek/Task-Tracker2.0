package com.taskTracker.api;

import com.taskTracker.dto.RegisterDto;
import com.taskTracker.service.impl.RegisterServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RegisterRestControllerTest {

    @Mock
    private RegisterServiceImpl registerServiceImpl;

    @InjectMocks
    private RegisterRestController registerRestController;

    public RegisterRestControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ValidRequest_ReturnsSuccessMessage() {
        // given
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("testuser");
        registerDto.setPassword("password");

        // when
        String result = registerRestController.registerUser(registerDto);

        // then
        assertEquals("User registered successfully.", result);
        verify(registerServiceImpl, times(1)).register(registerDto);
    }

    @Test
    void registerUser_ExceptionThrown_ReturnsErrorMessage() {
        // given
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("testuser");
        registerDto.setPassword("password");

        doThrow(new RuntimeException("Registration failed.")).when(registerServiceImpl).register(registerDto);

        // when
        String result = registerRestController.registerUser(registerDto);

        // then
        assertEquals("Registration failed.", result);
        verify(registerServiceImpl, times(1)).register(registerDto);
    }
}
