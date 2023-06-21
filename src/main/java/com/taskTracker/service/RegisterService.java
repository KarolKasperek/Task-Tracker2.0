package com.taskTracker.service;

import com.taskTracker.dto.RegisterRequest;
import com.taskTracker.entity.User;
import com.taskTracker.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {


    private UserRepository userRepository;

    public void register(RegisterRequest registerRequest) {
        checkFields(registerRequest);
        userRepository.save(new User(registerRequest.getName(), registerRequest.getEmail(), registerRequest.getPassword()));
    }

    private void checkFields(RegisterRequest registerRequest) {

        if (registerRequest.getName().isBlank() || registerRequest.getEmail().isBlank() || registerRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Every field should be filled in!");
        } else if (userRepository.existsByEmail(registerRequest.getEmail()) || !registerRequest.getPassword().equals(registerRequest.getPasswordRepetition())) {
            throw new RuntimeException("Email or password is incorrect!");
        }
    }
}
