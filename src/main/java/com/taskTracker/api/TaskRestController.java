package com.taskTracker.api;

import com.taskTracker.dto.TaskRequest;
import com.taskTracker.service.TaskService;
import com.taskTracker.exception.TaskDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskRestController {
    private TaskService taskService;

    @GetMapping
    public String getTasks() {
        // Logika pobierania zadań
        return "Tasks data";
    }

    @GetMapping("/{id}")
    public String getTaskDetails(@PathVariable Long id) {
        try {
            // Logika pobierania szczegółów zadania o podanym ID
            return "Task details for ID: " + id;
        } catch (TaskDoesNotExistException e) {
            return e.getMessage();
        }
    }

    @PostMapping
    public String addTask(@RequestBody TaskRequest taskRequest) {
        taskService.addTask(taskRequest);
        return "Task added successfully.";
    }
}
