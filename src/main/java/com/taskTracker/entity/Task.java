package com.taskTracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
