package com.taskTracker.service;

import com.taskTracker.dto.AccountDto;
import com.taskTracker.dto.RegisterDto;

import java.util.List;

public interface RegisterService {
    void register(RegisterDto registerDto);
    List<AccountDto> getAllAccounts();
}
