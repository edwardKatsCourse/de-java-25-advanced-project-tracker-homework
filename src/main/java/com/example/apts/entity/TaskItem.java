package com.example.apts.entity;

import com.example.apts.entity.type.TaskStatus;
import com.example.apts.entity.type.TaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private TaskType taskType;

    @Column(name = "status", nullable = false)
    private TaskStatus taskStatus;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Assignee assignee;

    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private TaskItem parentTask;

    @OneToMany(mappedBy = "parentTask")
    private List<TaskItem> children;

    // PROJECT 1
    //         FEATURE 2
    //          EPIC 3  <-----
    // TASK4,  TASK5,  TASK6

    // select * from task_item where parent_task_id = 3


}
