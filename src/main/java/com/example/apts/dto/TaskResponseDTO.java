package com.example.apts.dto;

import com.example.apts.entity.Assignee;
import com.example.apts.entity.TaskItem;
import com.example.apts.entity.type.TaskStatus;
import com.example.apts.entity.type.TaskType;
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

    // FRONTEND  |  BACKEND  | DATABASE


    private TaskType taskType; // {"taskType": "Project"}
    private TaskStatus taskStatus;
    private TaskItem parentTask;
    private Assignee assignee;
    private List<TaskItem> subTasks;
}
