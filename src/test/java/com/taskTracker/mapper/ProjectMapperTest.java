package com.taskTracker.mapper;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.entity.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProjectMapperTest {
    private final ProjectMapper projectMapper = new ProjectMapper();

    @Test
    @DisplayName("converting projectDto to projectEntity")
    public void toProjectEntity() {

        //given
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("project-xyz");
        projectDto.setDescription("here is the description of project-xyz");
        Project project1;
        Project project2 = new Project();
        project2.setName("project-xyz");
        project2.setDescription("here is the description of project-xyz");

        //when
        project1 = projectMapper.toProjectEntity(projectDto);

        //then
        assertThat(project1.getName(), equalTo(project2.getName()));
        assertThat(project1.getDescription(), equalTo(project2.getDescription()));
    }

    @Test
    @DisplayName("converting projectEntity to projectDto")
    public void toProjectDto() {

        //given
        Project project = new Project();
        project.setName("project-xyz");
        project.setDescription("here is the description of project-xyz");
        ProjectDto projectDto1;
        ProjectDto projectDto2 = new ProjectDto();
        projectDto2.setName("project-xyz");
        projectDto2.setDescription("here is the description of project-xyz");

        //when
        projectDto1 = projectMapper.toProjectDto(project);
        //then
        assertThat(projectDto1.getName(), equalTo(projectDto2.getName()));
        assertThat(projectDto1.getDescription(), equalTo(projectDto2.getDescription()));
    }
}
