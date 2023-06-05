package com.TaskTracker.controller;

import com.TaskTracker.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.TaskTracker.service.RegisterService;

@Controller
@AllArgsConstructor
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/sign-up")
    public String getRegistration(Model model) {
        RegisterRequest registerRequest = new RegisterRequest();
        model.addAttribute("registerRequest", registerRequest);
        return "registration";
    }

    @PostMapping("/registered")
    public String register(RegisterRequest registerRequest, Model model) {

        try {
            registerService.register(registerRequest);
        } catch (IllegalArgumentException e) {
            model.addAttribute("fieldsNotFilledMsg", e.getMessage());
            return "registration";
        } catch (Exception e) {
            model.addAttribute("registrationErrorMsg", e.getMessage());
            return "registration";
        }

        return "login";
    }
}
