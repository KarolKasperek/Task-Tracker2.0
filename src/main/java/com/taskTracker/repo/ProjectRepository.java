package com.taskTracker.repo;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
