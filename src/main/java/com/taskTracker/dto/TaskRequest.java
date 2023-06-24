package com.taskTracker.dto;

import com.taskTracker.entity.Account;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private Long id;

    @NotEmpty(message = "Do not forget about task name!")
    private String name;
    private String status;
    private String description;
    private LocalDate deadline;
    private Account account;
    private LocalDate startDate;

    public Account getAccount() {
        return account;
    }
}
