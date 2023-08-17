package com.taskTracker.controller;

import com.taskTracker.dto.TaskDto;
import com.taskTracker.service.RegisterService;
import com.taskTracker.service.TaskService;
import com.taskTracker.exception.TaskDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;
    private RegisterService registerService;

    @GetMapping("/task-details")
    public String getTask(@RequestParam(required = false) String status, Model model) {

        TaskDto taskDto = new TaskDto();
        taskDto.setStatus(status);
        model.addAttribute("taskDto", taskDto);
        model.addAttribute("accounts", registerService.getAllAccounts());
        return "task";
    }

    @PostMapping("/task-details")
    public String addTask(TaskDto taskDto, Model model) {

        taskService.addTask(taskDto);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String getTaskDetails(@PathVariable Long id, Model model) {

        addUsersAttribute(model);
        model.addAttribute("accounts", registerService.getAllAccounts());
        try {
            model.addAttribute("taskDto", taskService.getTaskInfo(id));
        } catch (TaskDoesNotExistException e) {
            model.addAttribute("noTaskMessage", e.getMessage());
        }

        return "task";
    }

    @GetMapping("/changeStatus/{id}") //todo
    public String changeTaskStatus(@PathVariable("id") Long taskId) {
        System.out.println("task status:"+taskService.getTask(taskId).getStatus());
        switch (taskService.getTask(taskId).getStatus()) {
            case "toDo" -> taskService.setTaskStatus(taskId, "inProgress");
            case "inProgress" -> taskService.setTaskStatus(taskId, "inReview");
            case "inReview" -> taskService.setTaskStatus(taskId, "done");
            case "done" -> taskService.setTaskStatus(taskId, "archived");
        }

        return "redirect:/";
    }

    @GetMapping("/archive/{id}")
    public String archiveTask(@PathVariable("id") Long taskId) {

        taskService.setTaskStatus(taskId, "archived");

        return "redirect:/";
    }

    private void addUsersAttribute(Model model) {
        model.addAttribute("users", registerService.getAllAccounts());
    }
}
