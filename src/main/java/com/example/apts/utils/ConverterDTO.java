package com.example.apts.utils;

import com.example.apts.dto.*;
import com.example.apts.entity.Assignee;
import com.example.apts.entity.TaskItem;
import com.example.apts.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConverterDTO {

    private final TaskRepository taskRepository;

    public TaskResponseDTO convertTaskToDTO(TaskItem task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .taskType(task.getTaskType())
                .taskStatus(task.getTaskStatus())
                .parentTask(task.getParentTask())
                .assignee(task.getAssignee())
                .subTasks(taskRepository.findAllByParentTask(task))
                .build();
    }

    public TaskResponseDTOshort convertTaskToDTOshort(TaskItem task) {
        return TaskResponseDTOshort.builder()
                .id(task.getId())
                .name(task.getName())
                .taskType(task.getTaskType())
                .taskStatus(task.getTaskStatus())
                .build();
    }
    public AssigneeResponseDTO convertAssigneeToDTO(Assignee assignee) {
        return AssigneeResponseDTO.builder()
                .id(assignee.getId())
                .name(assignee.getName())
                .accountStatus(assignee.getAccountStatus())
                .build();
    }
    public Assignee convertDTOToAssignee(AssigneeRequestDTO request) {
        return Assignee.builder()
                .name(request.getName())
                .build();
    }
}
