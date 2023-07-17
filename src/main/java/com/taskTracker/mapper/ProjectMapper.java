package com.taskTracker.mapper;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.dto.TaskDto;
import com.taskTracker.entity.Project;
import com.taskTracker.entity.Task;
import com.taskTracker.exception.TaskDoesNotExistException;
import com.taskTracker.repo.AccountRepository;
import com.taskTracker.repo.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProjectMapper {
    private TaskMapper taskMapper;

    public Project toProjectEntity(ProjectDto projectDto) {

        if (projectDto == null) {
            throw new TaskDoesNotExistException("Task does not exist!");
        }

        Project project = new Project();

        if (projectDto.getId() != 0) {
            project.setId(projectDto.getId());
        }

        project.setName(projectDto.getName());
        project.setTaskList(projectDto.getTaskDtoList().stream()
                .map(taskDto -> taskMapper.toTaskEntity(taskDto))
                .collect(Collectors.toList()));
        return project;
    }

    public ProjectDto toProjectDto(Project project) {

        if (project == null) {
            throw new TaskDoesNotExistException("Task does not exist!");
        }

        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());

        return projectDto;
    }
}
