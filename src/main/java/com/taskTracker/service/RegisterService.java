package com.taskTracker.service;

import com.taskTracker.dto.RegisterRequest;
import com.taskTracker.dto.AccountRequest;
import com.taskTracker.entity.Account;
import com.taskTracker.mapper.UserMapper;
import com.taskTracker.repo.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegisterService implements UserDetailsService {

    private AccountRepository accountRepository;

    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    public void register(RegisterRequest registerRequest) {

        checkFields(registerRequest);
        accountRepository.save(new Account(registerRequest.getName(), registerRequest.getEmail(), passwordEncoder.encode(registerRequest.getPassword())));
    }

    private void checkFields(RegisterRequest registerRequest) {

        if (registerRequest.getName().isBlank() || registerRequest.getEmail().isBlank() || registerRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Every field should be filled in!");
        } else if (accountRepository.existsByEmail(registerRequest.getEmail()) || !registerRequest.getPassword().equals(registerRequest.getPasswordRepetition())) {
            throw new RuntimeException("Email or password is incorrect!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        return new User(account.getEmail(), account.getPassword(), new ArrayList<>());
    }

    public List<AccountRequest> getAllUsers() {
        return accountRepository.findAll().stream()
                .map(userEntity -> userMapper.toAccountRequest(userEntity))
                .collect(Collectors.toList());
    }
}
