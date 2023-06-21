package com.taskTracker.controller;

import com.taskTracker.dto.TaskRequest;
import com.taskTracker.service.TaskService;
import com.taskTracker.exception.TaskDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @GetMapping("/new-task")
    public String getTask(Model model) {
        TaskRequest taskRequest = new TaskRequest();
        model.addAttribute("taskRequest", taskRequest);
        return "task";
    }

    @GetMapping("/task-details/{id}")
    public String getTaskDetails(@PathVariable("id") Long id, Model model) {

        try {
            model.addAttribute("taskToView", taskService.getTaskInfo(id));
        } catch (TaskDoesNotExistException e) {
            model.addAttribute("noTaskMessage", e.getMessage());
        }

        return "task";
    }

    @PostMapping("/new-task")
    public String addTask(TaskRequest taskRequest, Model model) {

        taskService.addTask(taskRequest);
        return "redirect:/";
    }
}
