package com.taskTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccessDeniedController {
    @GetMapping("/access-denied")
    @ResponseBody
    public String getAccessDeniedPage() {
        return "access-denied";
    }
}
