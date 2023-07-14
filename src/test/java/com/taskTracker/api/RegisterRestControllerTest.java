package com.taskTracker.api;

import com.taskTracker.dto.RegisterRequest;
import com.taskTracker.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RegisterRestControllerTest {

    @Mock
    private RegisterService registerService;

    @InjectMocks
    private RegisterRestController registerRestController;

    public RegisterRestControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ValidRequest_ReturnsSuccessMessage() {
        // given
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("testuser");
        registerRequest.setPassword("password");

        // when
        String result = registerRestController.registerUser(registerRequest);

        // then
        assertEquals("User registered successfully.", result);
        verify(registerService, times(1)).register(registerRequest);
    }

    @Test
    void registerUser_ExceptionThrown_ReturnsErrorMessage() {
        // given
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName("testuser");
        registerRequest.setPassword("password");

        doThrow(new RuntimeException("Registration failed.")).when(registerService).register(registerRequest);

        // when
        String result = registerRestController.registerUser(registerRequest);

        // then
        assertEquals("Registration failed.", result);
        verify(registerService, times(1)).register(registerRequest);
    }
}
