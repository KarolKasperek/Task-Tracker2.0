package com.taskTracker.entity;

import com.taskTracker.enums.VisibilityStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {
    @Id
    private int id;
    private String name;
    private List<Long> taskIds;
    VisibilityStatus status;

}
