package com.TaskTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private Long id;
    private String name;
    private String status;
    private String description;
    private LocalDate deadline;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    private Long userId;
}
