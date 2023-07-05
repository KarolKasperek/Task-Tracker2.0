package com.taskTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MembersController {

    @GetMapping("/members")
    public String getMembersPage() {
        return "members";
    }
}
