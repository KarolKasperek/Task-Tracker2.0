package com.taskTracker.mapper;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.dto.TaskDto;
import com.taskTracker.entity.Project;
import com.taskTracker.exception.TaskDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProjectMapper {

    public Project toProjectEntity(ProjectDto projectDto) {

        if (projectDto == null) {
            throw new TaskDoesNotExistException("Project does not exist!");
        }

        Project project = new Project();

        if (projectDto.getId() != 0) {
            project.setId(projectDto.getId());
        }

        project.setName(projectDto.getName());
        project.setTaskIds(projectDto.getTaskDtoList().stream()
                .map(TaskDto::getId)
                .collect(Collectors.toList()));
        return project;
    }

    public ProjectDto toProjectDto(Project project) {

        if (project == null) {
            throw new TaskDoesNotExistException("Project does not exist!");
        }

        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());

        return projectDto;
    }
}
