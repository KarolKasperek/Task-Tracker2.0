package com.TaskTracker.controller;

import com.TaskTracker.service.TaskService;
import com.TaskTracker.exception.TaskDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping("/add-task")
    public String getTask() {

        return "task";
    }

    @GetMapping("/task-details/{id}")
    public String getTaskDetails(@PathVariable("id") Long id, Model model) {

        try {
            model.addAttribute("taskToView", taskService.getTaskInfo(id));
        } catch (TaskDoesNotExistException e) {
            model.addAttribute("noTaskMessage", e.getMessage());
        }

        return "task-details";
    }
}
