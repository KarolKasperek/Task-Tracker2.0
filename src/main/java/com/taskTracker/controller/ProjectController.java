package com.taskTracker.controller;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.dto.TaskDto;
import com.taskTracker.exception.TaskDoesNotExistException;
import com.taskTracker.service.ProjectService;
import com.taskTracker.service.RegisterService;
import com.taskTracker.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/")
    public String getProjectListPage(Model model) {
        model.addAttribute("projectList", projectService.getProjectList());
        return "projectList";
    }

    @GetMapping("/new-project")
    public String getProjectCreationPage(Model model) {
        ProjectDto projectDto = new ProjectDto();
        model.addAttribute("projectDto", projectDto);
        return "project";
    }

    @PostMapping("new-project")
    public String addNewProject(ProjectDto projectDto) {
        projectService.addProject(projectDto);
        return "redirect:/";
    }

    @GetMapping("/project/{id}")
    public String getProjectPage(@PathVariable("id") int projectId, Model model) {
        ProjectDto projectDto = projectService.getProject(projectId);
        List<TaskDto> taskList = projectDto.getTaskDtoList();
        model.addAttribute("taskList", taskList);
        return "index";
    }

//    @PostMapping("/task-details")
//    public String addTask(TaskDto taskDto, Model model) {
//
//        taskService.addTask(taskDto);
//        return "redirect:/";
//    }
}
