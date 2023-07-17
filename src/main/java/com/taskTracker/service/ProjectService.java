package com.taskTracker.service;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.entity.Task;
import com.taskTracker.mapper.ProjectMapper;
import com.taskTracker.repo.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {
    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;

    public void addProject(ProjectDto projectDto) {
        projectRepository.save(projectMapper.toProjectEntity(projectDto));
    }
}
