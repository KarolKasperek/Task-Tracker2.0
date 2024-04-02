package com.taskTracker.controller;

import com.taskTracker.service.impl.RegisterServiceImpl;
import com.taskTracker.service.impl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MembersController {
    private RegisterServiceImpl registerServiceImpl;
    private TaskServiceImpl taskServiceImpl;

    @GetMapping("/members")
    public String getMembersPage(Model model) {

        model.addAttribute("taskService", taskServiceImpl);
        model.addAttribute("accounts", registerServiceImpl.getAllAccounts());

        return "members";
    }
}
