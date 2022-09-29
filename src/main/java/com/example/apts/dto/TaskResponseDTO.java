package com.example.apts.dto;

import com.example.apts.entity.Assignee;
import com.example.apts.entity.TaskItem;
import com.example.apts.entity.TaskStatus;
import com.example.apts.entity.TaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskResponseDTO {
    private Long id;
    private String name;
    private TaskType taskType;
    private TaskStatus taskStatus;
    private TaskItem parentTask;
    private Assignee assignee;
    private List<TaskItem> subTasks;
}
