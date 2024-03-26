package com.taskTracker.api;

import com.taskTracker.dto.TaskDto;
import com.taskTracker.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.util.List;

@RestController
@RequestMapping("/api/home")
@AllArgsConstructor
public class HomeRestController {
    private TaskService taskService;

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String addTask(@Valid @RequestBody TaskDto taskDto) {

        try {
            taskService.addTask(taskDto);
            return "Task added successfully.";
        } catch (IllegalArgumentException | DateTimeException e) {
            return e.getMessage();
        }
    }
}
