package com.taskTracker.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;

    @NotEmpty(message = "Do not forget about task name!")
    private String name;
    private String status;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    private Long accountId;
    private LocalDate startDate;
}
