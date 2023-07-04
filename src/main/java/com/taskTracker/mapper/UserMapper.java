package com.taskTracker.mapper;

import com.taskTracker.dto.AccountRequest;
import com.taskTracker.entity.Account;
import com.taskTracker.enums.ExceptionMessage;
import com.taskTracker.enums.Role;
import com.taskTracker.exception.UserDoesNotExistException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public Account toAccount(AccountRequest accountRequest) {

        if (accountRequest == null) {
            throw new UserDoesNotExistException(ExceptionMessage.USER_DOES_NOT_EXIST.getExceptionText());
        }

        Account account = new Account();

        if (accountRequest.getId() != null) {
            account.setId(accountRequest.getId());
        }

        account.setName(accountRequest.getName());
        account.setEmail(accountRequest.getEmail());
        account.setPassword(new BCryptPasswordEncoder().encode(accountRequest.getPassword()));
        account.setRole(Role.USER);

        return account;
    }

    public AccountRequest toAccountRequest(Account account) {

        if (account == null) {
            throw new UserDoesNotExistException(ExceptionMessage.USER_DOES_NOT_EXIST.getExceptionText());
        }

        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setId(accountRequest.getId());
        accountRequest.setName(accountRequest.getName());
        accountRequest.setEmail(accountRequest.getEmail());
        accountRequest.setPassword(accountRequest.getPassword());
        accountRequest.setRole(accountRequest.getRole());

        return accountRequest;
    }
}
