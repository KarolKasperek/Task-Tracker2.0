package com.taskTracker.api;

import com.taskTracker.dto.ProjectDto;
import com.taskTracker.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProjectRestControllerTest {
    @Mock
    private ProjectService projectService;
    @InjectMocks
    private ProjectRestController projectRestController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("method getProjects returns projects names")
    public void getProjects() {

        //given
        ProjectDto projectDto1 = new ProjectDto();
        projectDto1.setName("project-x1");
        ProjectDto projectDto2 = new ProjectDto();
        projectDto2.setName("project-x2");
        ProjectDto projectDto3 = new ProjectDto();
        projectDto3.setName("project-x3");
        //when
        when(projectService.getProjectList()).thenReturn(new LinkedList<>(List.of(projectDto1, projectDto2, projectDto3)));

        //then
        assertThat(projectRestController.getProjects(), equalTo(projectDto1.getName() + "\n" + projectDto2.getName() + "\n" + projectDto3.getName() + "\n"));
        verify(projectService).getProjectList();
    }
}
