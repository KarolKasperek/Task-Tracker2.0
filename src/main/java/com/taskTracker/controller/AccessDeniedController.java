package com.taskTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class that handles requests for the access-denied message page. This page is displayed
 * to users when they attempt to access a part of the application that they do not have permission
 * to access.
 */
@Controller
public class AccessDeniedController {

    /**
     * Handles GET request for the access-denied page.
     *
     * @return the name of the view (access-denied.html) to be rendered by the Spring MVC framework
     */
    @GetMapping("/access-denied")
    public String getLoginPage() {
        return "access-denied";
    }
}
