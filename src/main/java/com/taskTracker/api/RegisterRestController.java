package com.taskTracker.api;

import com.taskTracker.dto.RegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.taskTracker.service.RegisterService;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegisterRestController {

    private RegisterService registerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@RequestBody RegisterDto registerDto) {

        try {
            registerService.register(registerDto);
            return "User registered successfully.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
