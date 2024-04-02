package com.taskTracker.service.impl;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.entity.Project;
import com.taskTracker.entity.Task;
import com.taskTracker.mapper.ProjectMapper;
import com.taskTracker.repo.ProjectRepository;
import com.taskTracker.repo.TaskRepository;
import com.taskTracker.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;
    private TaskRepository taskRepository;

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
            Project project = projectRepository.findById(id).get();
            return projectMapper.toProjectDto(project);
        } else {
            log.error("Project not found");
            return null;
        }
    }

    public void addTaskToProject(int projectId, Long taskId) {

        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            Task task = taskRepository.findById(taskId).orElseThrow();
            project.addTask(task);
          //  List<Long> taskIds = project.getTaskIds() == null ? new LinkedList<>() : project.getTaskIds();
            //taskIds.add(taskId);
           // project.setTaskIds(taskIds);
            projectRepository.save(project);
//            taskRepository.save(task); no need because of cascade
        }
    }
}
