package com.taskTracker.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private int id;
    private String name;
    private String description;
    private String visibility;
    private List<TaskDto> taskDtoList;

}
