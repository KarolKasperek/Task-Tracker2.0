package com.TaskTracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name")
    private String name;

    @Column(name = "task_status")
    private String status;

    @Column(name = "task_description")
    private String description;

    @Column(name = "task_start_date")
    private LocalDate startDate;

    @Column(name = "task_deadline")
    private LocalDate deadline;

    @Column(name = "task_user_id")
    private long userId;

    public Task(String name, String status, String description, LocalDate deadline, long userId) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.startDate = LocalDate.now();
        this.deadline = deadline;
        this.userId = userId;
    }

    public Task() {
    }
}
