package com.taskTracker.dto;

import com.taskTracker.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    private Long id;
    @NotNull(message = "username should not be null!")
    private String name;
    @Email
    private String email;
    @NotNull(message = "password should not be null!")
    private String password;
    private Role role;
}
