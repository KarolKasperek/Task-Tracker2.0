package com.taskTracker.api;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.service.impl.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project")
@RequiredArgsConstructor
public class ProjectRestController {
    private final ProjectServiceImpl projectServiceImpl;

    @GetMapping("/projects")
    public String getProjects() {
        List<ProjectDto> projects = projectServiceImpl.getProjectList();
        StringBuilder projectsSchedule = new StringBuilder();
        for (ProjectDto project : projects) {
            projectsSchedule.append(project.getName()).append("\n");
        }
        return projectsSchedule.toString();
    }

    @PostMapping("new-project")
    @ResponseStatus(HttpStatus.OK)
    public void addNewProject(ProjectDto projectDto) {
        projectServiceImpl.addProject(projectDto);
    }
}
