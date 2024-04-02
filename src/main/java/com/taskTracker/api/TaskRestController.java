package com.taskTracker.api;

import com.taskTracker.dto.TaskDto;
import com.taskTracker.service.impl.TaskServiceImpl;
import com.taskTracker.exception.TaskDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskRestController {
    private TaskServiceImpl taskServiceImpl;

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.FOUND)
    public String getTasks() {

        StringBuilder stringBuilder = new StringBuilder("Tasks:\n");
        for (TaskDto task : taskServiceImpl.getAllTasks()) {
            stringBuilder.append(task.getName()).append("\n");
        }
        return stringBuilder.toString();
    }

    @GetMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public String getTaskDetails(@PathVariable Long id) {
        try {
            return "Task details for ID: " + id + "\n" + taskServiceImpl.getTaskInfo(id);
        } catch (TaskDoesNotExistException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/task-details")
    @ResponseStatus(HttpStatus.CREATED)
    public String addTask(@RequestBody TaskDto taskDto) {
        taskServiceImpl.addTask(taskDto);
        return "Task added successfully.";
    }
}
