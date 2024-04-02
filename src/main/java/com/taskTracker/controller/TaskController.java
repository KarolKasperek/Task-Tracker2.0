package com.taskTracker.controller;

import com.taskTracker.dto.TaskDto;
import com.taskTracker.entity.Task;
import com.taskTracker.exception.TaskDoesNotExistException;
import com.taskTracker.service.impl.ProjectServiceImpl;
import com.taskTracker.service.impl.RegisterServiceImpl;
import com.taskTracker.service.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("project")
public class TaskController {
    private final TaskServiceImpl taskServiceImpl;
    private final RegisterServiceImpl registerServiceImpl;
    private final ProjectServiceImpl projectServiceImpl;

    @GetMapping("{pId}/task-details")
    public String getTask(@RequestParam(required = false) String status, @PathVariable("pId") int projectId, Model model) {

        TaskDto taskDto = new TaskDto();
        taskDto.setStatus(status);
        model.addAttribute("taskDto", taskDto);
        model.addAttribute("accounts", registerServiceImpl.getAllAccounts());
        model.addAttribute("projectId", projectId);
        return "task";
    }

    @PostMapping("{pId}/task-details")
    public String addTask(TaskDto taskDto, @PathVariable("pId") int projectId, Model model) {

        Task task = taskServiceImpl.addTask(taskDto);
        projectServiceImpl.addTaskToProject(projectId, task.getId());
        return "redirect:/project/"+projectId;
    }

    @GetMapping("{pId}/edit/{id}")
    public String getTaskDetails(@PathVariable Long id, Model model, @PathVariable("pId") int projectId) {

        addUsersAttribute(model);
        model.addAttribute("accounts", registerServiceImpl.getAllAccounts());
        try {
            model.addAttribute("taskDto", taskServiceImpl.getTaskInfo(id));
        } catch (TaskDoesNotExistException e) {
            model.addAttribute("noTaskMessage", e.getMessage());
        }

        model.addAttribute("projectId", projectId);

        return "task";
    }

    @GetMapping("{pId}/changeStatus/{id}")
    public String changeTaskStatus(@PathVariable("id") Long taskId, @PathVariable("pId") int projectId) {

        switch (taskServiceImpl.getTask(taskId).getStatus()) {
            case "toDo" -> taskServiceImpl.setTaskStatus(taskId, "inProgress");
            case "inProgress" -> taskServiceImpl.setTaskStatus(taskId, "inReview");
            case "inReview" -> taskServiceImpl.setTaskStatus(taskId, "done");
            case "done" -> taskServiceImpl.setTaskStatus(taskId, "archived");
        }

        return "redirect:/project/"+projectId;
    }

    @GetMapping("{pId}/archive/{id}")
    public String archiveTask(@PathVariable("id") Long taskId, @PathVariable("pId") int projectId) {

        taskServiceImpl.setTaskStatus(taskId, "archived");

        return "redirect:/project/" + projectId;
    }

    private void addUsersAttribute(Model model) {
        model.addAttribute("users", registerServiceImpl.getAllAccounts());
    }
}
