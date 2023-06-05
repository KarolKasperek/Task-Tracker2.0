package com.TaskTracker.controller;

import com.TaskTracker.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController {
    private TaskService taskService;

    @GetMapping("/add-task")
    public String getTask() {

        return "task";
    }
}
