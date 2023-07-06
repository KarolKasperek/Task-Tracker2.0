package com.taskTracker.api;

import com.taskTracker.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskTracker.service.RegisterService;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegisterRestController {

    private RegisterService registerService;

    @PostMapping
    public String registerUser(@RequestBody RegisterRequest registerRequest) {

        try {
            registerService.register(registerRequest);
            return "User registered successfully.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
