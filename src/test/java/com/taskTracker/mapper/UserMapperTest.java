package com.taskTracker.mapper;

import com.taskTracker.dto.AccountDto;
import com.taskTracker.entity.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class UserMapperTest {
    private final UserMapper userMapper = new UserMapper();

    @Test
    @DisplayName("converting accountDto to AccountEntity")
    public void toAccount() {

        //given
        AccountDto accountDto = new AccountDto();
        accountDto.setName("Mark");
        accountDto.setEmail("mark24@gmail.com");
        accountDto.setPassword("ajwdJIKoaiwj102983");
        Account account1;
        Account account2 = new Account();
        account2.setName("Mark");
        account2.setEmail("mark24@gmail.com");
        account2.setPassword("ajwdJIKoaiwj102983");

        //when
        account1 = userMapper.toAccount(accountDto);

        //then
        assertThat(account1.getName(), equalTo(account2.getName()));
        assertThat(account1.getEmail(), equalTo(account2.getEmail()));
    }

    @Test
    @DisplayName("converting AccountEntity to AccountDto")
    public void toAccountRequest() {

        //given
        Account account = new Account();
        account.setName("Mark");
        account.setEmail("mark24@gmail.com");
        account.setPassword("ajwdJIKoaiwj102983");
        AccountDto accountDto1;
        AccountDto accountDto2 = new AccountDto();
        accountDto2.setName("Mark");
        accountDto2.setEmail("mark24@gmail.com");
        accountDto2.setPassword("ajwdJIKoaiwj102983");

        //when
        accountDto1 = userMapper.toAccountRequest(account);

        //then
        assertThat(accountDto1.getName(), equalTo(accountDto2.getName()));
        assertThat(accountDto1.getEmail(), equalTo(accountDto2.getEmail()));
    }
}
