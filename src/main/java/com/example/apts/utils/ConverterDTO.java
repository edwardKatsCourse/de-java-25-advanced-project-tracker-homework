package com.example.apts.utils;

import com.example.apts.dto.AssigneeRequestDTO;
import com.example.apts.dto.AssigneeResponseDTO;
import com.example.apts.dto.TaskResponseDTO;
import com.example.apts.dto.TaskResponseDTOshort;
import com.example.apts.entity.Assignee;
import com.example.apts.entity.TaskItem;

public class ConverterDTO {

    public static TaskResponseDTO convertTaskToDTO(TaskItem task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .taskType(task.getTaskType())
                .taskStatus(task.getTaskStatus())
                .parentTask(task.getParentTask())
                .assignee(task.getAssignee())
                .subTasks(task.getChildren())
                .build();
    }

    public static TaskResponseDTOshort convertTaskToDTOshort(TaskItem task) {
        return TaskResponseDTOshort.builder()
                .id(task.getId())
                .name(task.getName())
                .taskType(task.getTaskType())
                .taskStatus(task.getTaskStatus())
                .build();
    }
    public static AssigneeResponseDTO convertAssigneeToDTO(Assignee assignee) {
        return AssigneeResponseDTO.builder()
                .id(assignee.getId())
                .name(assignee.getName())
                .accountStatus(assignee.getAccountStatus())
                .build();
    }
    public static Assignee convertDTOToAssignee(AssigneeRequestDTO request) {
        return Assignee.builder()
                .name(request.getName())
                .build();
    }
}
