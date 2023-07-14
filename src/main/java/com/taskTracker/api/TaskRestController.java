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

        StringBuilder stringBuilder = new StringBuilder("Tasks:\n");
        for (TaskRequest task : taskService.getAllTasks()) {
            stringBuilder.append(task.getName()).append("\n");
        }
        return stringBuilder.toString();
    }

    @GetMapping("/{id}")
    public String getTaskDetails(@PathVariable Long id) {
        try {
            return "Task details for ID: " + id + "\n" + taskService.getTaskInfo(id);
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
