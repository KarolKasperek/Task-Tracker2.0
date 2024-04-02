package com.taskTracker.service;

import com.taskTracker.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    void addProject(ProjectDto projectDto);
    List<ProjectDto> getProjectList();
    ProjectDto getProject(int id);
    void addTaskToProject(int projectId, Long taskId);
}
