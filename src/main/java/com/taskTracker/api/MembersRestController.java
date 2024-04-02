package com.taskTracker.api;

import com.taskTracker.dto.AccountDto;
import com.taskTracker.service.impl.RegisterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MembersRestController {
    private final RegisterServiceImpl registerServiceImpl;

    @GetMapping
    public String getMembers() {
        List<AccountDto> accounts = registerServiceImpl.getAllAccounts();
        List<String> accountsAsStrings = accounts.stream()
                .map(AccountDto::toString)
                .toList();
        return String.join(",", accountsAsStrings);
    }
}
