package com.taskTracker.service.impl;

import com.taskTracker.dto.RegisterDto;
import com.taskTracker.dto.AccountDto;
import com.taskTracker.entity.Account;
import com.taskTracker.mapper.UserMapper;
import com.taskTracker.repo.AccountRepository;
import com.taskTracker.service.RegisterService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class RegisterServiceImpl implements UserDetailsService, RegisterService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public void register(RegisterDto registerDto) {

        checkFields(registerDto);
        accountRepository.save(new Account(registerDto.getName(), registerDto.getEmail(), passwordEncoder.encode(registerDto.getPassword())));
    }

    private void checkFields(RegisterDto registerDto) {

        if (registerDto.getName().isBlank() || registerDto.getEmail().isBlank() || registerDto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Every field should be filled in!");
        } else if (accountRepository.existsByEmail(registerDto.getEmail()) || !registerDto.getPassword().equals(registerDto.getPasswordRepetition())) {
            throw new RuntimeException("Email or password is incorrect!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        return new User(account.getEmail(), account.getPassword(), new ArrayList<>());
    }

    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(userMapper::toAccountRequest)
                .collect(Collectors.toList());
    }
}
