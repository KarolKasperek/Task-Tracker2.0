package com.taskTracker.service;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.entity.Project;
import com.taskTracker.entity.Task;
import com.taskTracker.mapper.ProjectMapper;
import com.taskTracker.repo.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectService {
    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;

    public void addProject(ProjectDto projectDto) {
        projectRepository.save(projectMapper.toProjectEntity(projectDto));
    }

    public List<ProjectDto> getProjectList() {
        return projectRepository.findAll().stream()
                .map(project -> projectMapper.toProjectDto(project))
                .collect(Collectors.toList());
    }

    public ProjectDto getProject(int id) {
        if (projectRepository.findById(id).isPresent()) {
            return projectMapper.toProjectDto(projectRepository.findById(id).get());
        } else {
            log.error("Project not found");
            return null;
        }
    }

    public void addTaskToProject(int projectId, Long taskId) {
        if (projectRepository.findById(projectId).isPresent()) {
            List<Long> taskIds = projectRepository.findById(projectId).get().getTaskIds() == null ? new LinkedList<>() : projectRepository.findById(projectId).get().getTaskIds();
            taskIds.add(taskId);
            Project project = projectRepository.findById(projectId).get();
            project.setTaskIds(taskIds);
            projectRepository.save(project);
        }
    }
}
