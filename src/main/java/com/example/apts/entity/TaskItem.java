package com.example.apts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tasks")

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Assignee assignee;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskItem parentTask;

    @Column(name = "type")
    private TaskType taskType;

    @Column(name = "status")
    private TaskStatus taskStatus;
}
