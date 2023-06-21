package com.taskTracker.dto;

import com.taskTracker.entity.User;
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
    private User user;
    private LocalDate startDate;

    public User getUser() {
        return user;
    }
}
