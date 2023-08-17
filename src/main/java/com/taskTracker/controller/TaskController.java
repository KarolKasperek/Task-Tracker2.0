package com.taskTracker.controller;

import com.taskTracker.dto.TaskDto;
import com.taskTracker.service.RegisterService;
import com.taskTracker.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DateTimeException;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    private final RegisterService registerService;

    @GetMapping("/task-details")
    public String createNewTask(@RequestParam(required = false) String status, Model model) {

        TaskDto taskDto = new TaskDto();
        taskDto.setStatus(status);
        model.addAttribute("taskDto", taskDto);
        model.addAttribute("accounts", registerService.getAllAccounts());
        return "task";
    }

    @PostMapping("/task-details")
    public String processAddingTask(@Valid @ModelAttribute("taskRequest") TaskDto taskDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "index";
        } else {

            try {
                taskService.addTask(taskDto);
            } catch (IllegalArgumentException e) {
                model.addAttribute("fieldsNotFilledMsg", e.getMessage());
                return "index";
            } catch (DateTimeException d) {
                model.addAttribute("invalidDateMsg", d.getMessage());
                return "index";
            }
        }
        return "redirect:/";
    }
}
