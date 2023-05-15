package com.TaskTracker.service;

import com.TaskTracker.dto.RegisterRequest;
import com.TaskTracker.entity.User;
import com.TaskTracker.repo.UserRepository;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    public void register(RegisterRequest registerRequest) {

        checkFields(registerRequest);
        userRepository.save(new User(registerRequest.getName(), registerRequest.getEmail(), registerRequest.getPassword()));
    }

    private void checkFields(RegisterRequest registerRequest) {

        if (registerRequest.getName().isBlank() || registerRequest.getEmail().isBlank() || registerRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Every field should be filled in!");
        } else if (userRepository.existsByEmail(registerRequest.getEmail()) || registerRequest.getPassword().equals(registerRequest.getPasswordRepetition())) {
            throw new MessageDescriptorFormatException("Email or password is incorrect!");
        }
    }
}
