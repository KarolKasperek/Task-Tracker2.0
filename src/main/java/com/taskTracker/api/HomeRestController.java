package com.taskTracker.api;

import com.taskTracker.dto.TaskDto;
import com.taskTracker.service.impl.TaskServiceImpl;
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
    private TaskServiceImpl taskServiceImpl;

    @GetMapping
    public List<TaskDto> getTasks() {
        return taskServiceImpl.getAllTasks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String addTask(@Valid @RequestBody TaskDto taskDto) {

        try {
            taskServiceImpl.addTask(taskDto);
            return "Task added successfully.";
        } catch (IllegalArgumentException | DateTimeException e) {
            return e.getMessage();
        }
    }
}
