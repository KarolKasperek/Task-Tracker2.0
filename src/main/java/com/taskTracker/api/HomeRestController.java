package com.taskTracker.api;

import com.taskTracker.dto.TaskRequest;
import com.taskTracker.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.util.List;

@RestController
@RequestMapping("/api/home")
@AllArgsConstructor
public class HomeRestController {
    private TaskService taskService;

    @GetMapping
    public List<TaskRequest> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public String addTask(@Valid @RequestBody TaskRequest taskRequest) {

        try {
            taskService.addTask(taskRequest);
            return "Task added successfully.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (DateTimeException d) {
            return d.getMessage();
        }
    }
}
