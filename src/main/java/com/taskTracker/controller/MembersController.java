package com.taskTracker.controller;

import com.taskTracker.service.RegisterService;
import com.taskTracker.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MembersController {
    private RegisterService registerService;
    private TaskService taskService;

    @GetMapping("/members")
    public String getMembersPage(Model model) {

        model.addAttribute("taskService", taskService);
        model.addAttribute("accounts", registerService.getAllAccounts());

        return "members";
    }
}
