package com.taskTracker.mapper;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.dto.TaskDto;
import com.taskTracker.entity.Project;
import com.taskTracker.exception.TaskDoesNotExistException;
import com.taskTracker.repo.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectMapper {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public Project toProjectEntity(ProjectDto projectDto) {

        if (projectDto == null) {
            throw new TaskDoesNotExistException("Project does not exist!");
        }

        Project project = new Project();

        if (projectDto.getId() != 0) {
            project.setId(projectDto.getId());
        }

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        if (projectDto.getTaskDtoList() != null) {
            project.setTasks(projectDto.getTaskDtoList().stream()
                    .map(taskDto -> taskRepository.findById(taskDto.getId()).orElseThrow())
                    .collect(Collectors.toList()));
        }
        return project;
    }

    public ProjectDto toProjectDto(Project project) {

        if (project == null) {
            throw new TaskDoesNotExistException("Project does not exist!");
        }

        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setTaskDtoList(project.getTasks().stream()
                .map(task -> taskMapper.toTaskRequest(task))
                .toList()
        );

        return projectDto;
    }
}
