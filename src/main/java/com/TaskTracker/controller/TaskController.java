package com.TaskTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController {

    @RequestMapping("add-task")
    public String getTask() {
        return "task";
    }
}
