package com.taskTracker.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MembersRestController {

    @GetMapping
    public String getMembers() {
        //logika pobierania informacji  oczlonkach
        return "Members data";
    }
}
