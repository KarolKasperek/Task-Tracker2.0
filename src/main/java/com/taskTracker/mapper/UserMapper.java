package com.taskTracker.mapper;

import com.taskTracker.dto.AccountDto;
import com.taskTracker.entity.Account;
import com.taskTracker.enums.ExceptionMessage;
import com.taskTracker.enums.Role;
import com.taskTracker.exception.UserDoesNotExistException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public Account toAccount(AccountDto accountDto) {

        if (accountDto == null) {
            throw new UserDoesNotExistException(ExceptionMessage.USER_DOES_NOT_EXIST.getExceptionText());
        }

        Account account = new Account();

        if (accountDto.getId() != null) {
            account.setId(accountDto.getId());
        }

        account.setName(accountDto.getName());
        account.setEmail(accountDto.getEmail());
        account.setPassword(new BCryptPasswordEncoder().encode(accountDto.getPassword()));
        account.setRole(Role.USER);

        return account;
    }

    public AccountDto toAccountRequest(Account account) {

        if (account == null) {
            throw new UserDoesNotExistException(ExceptionMessage.USER_DOES_NOT_EXIST.getExceptionText());
        }

        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setName(account.getName());
        accountDto.setEmail(account.getEmail());
        accountDto.setPassword(account.getPassword());
        accountDto.setRole(account.getRole());

        return accountDto;
    }
}
