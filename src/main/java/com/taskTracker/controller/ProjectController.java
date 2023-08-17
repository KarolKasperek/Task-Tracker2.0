package com.taskTracker.controller;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;

@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/projects")
    public String getProjectListPage(Model model) {
        model.addAttribute("projectList", projectService.getProjectList());
        return "projectList";
    }

    @GetMapping("/project-details")
    public String getProjectCreationPage(Model model) {
        ProjectDto projectDto = new ProjectDto();
        model.addAttribute("projectDto", projectDto);
        return "project";
    }

    @PostMapping("project-details")
    public String addNewProject(ProjectDto projectDto) {
        projectService.addProject(projectDto);
        return "redirect:/projects";
    }

    @GetMapping("/{id}")
    public String getProjectPage(@PathVariable("id") int projectId, Model model) {
        ProjectDto projectDto = projectService.getProject(projectId);
        model.addAttribute("taskList", projectDto.getTaskDtoList());
        return "index";
    }
}
