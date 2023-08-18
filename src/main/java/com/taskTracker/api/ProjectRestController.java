package com.taskTracker.api;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project")
@RequiredArgsConstructor
public class ProjectRestController {
    private final ProjectService projectService;

    @GetMapping("/projects")
    public String getProjects() {
        List<ProjectDto> projects = projectService.getProjectList();
        StringBuilder projectsSchedule = new StringBuilder();
        for (ProjectDto project : projects) {
            projectsSchedule.append(project.getName()).append("\n");
        }
        return projectsSchedule.toString();
    }

    @PostMapping("new-project")
    @ResponseStatus(HttpStatus.OK)
    public void addNewProject(ProjectDto projectDto) {
        projectService.addProject(projectDto);
    }
}
