package com.taskTracker.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginRestControllerTest {

    @Mock
    private Authentication authentication;

    @InjectMocks
    private LoginRestController loginRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void login_ReturnsSuccessMessage() {
        // when
        String result = loginRestController.login();

        // then
        assertEquals("Login successful.", result);
    }
}
