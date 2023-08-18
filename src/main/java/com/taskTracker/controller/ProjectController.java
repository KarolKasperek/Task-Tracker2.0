package com.taskTracker.controller;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.dto.TaskDto;
import com.taskTracker.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("projectId", projectId);
        return "index";
    }
}
