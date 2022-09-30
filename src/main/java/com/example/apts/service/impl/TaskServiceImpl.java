package com.example.apts.service.impl;

import com.example.apts.dto.TaskRequestDTO;
import com.example.apts.dto.TaskResponseDTO;
import com.example.apts.dto.TaskResponseDTOshort;
import com.example.apts.entity.*;
import com.example.apts.entity.type.AccountStatus;
import com.example.apts.entity.type.TaskStatus;
import com.example.apts.repository.AssigneeRepository;
import com.example.apts.repository.TaskRepository;
import com.example.apts.service.TaskService;
import com.example.apts.utils.ConverterDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final AssigneeRepository assigneeRepository;

    public void createTask(TaskRequestDTO request) {

        Assignee assignee = request.getAssigneeId() == null
                ? null
                : assigneeRepository.findById(request.getAssigneeId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Assignee with ID %s cannot be found", request.getAssigneeId())
                        )
                );

        var parentTask = request.getParentTaskId() == null
                ? null
                : taskRepository.findById(request.getParentTaskId())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Parent task with ID %s cannot be found", request.getParentTaskId())
                        )
                );

        if (assignee != null) {
            if (assignee.getAccountStatus() == AccountStatus.INACTIVE) {
                throw new ResponseStatusException(
                        HttpStatus.PRECONDITION_FAILED,
                        String.format(
                                "Assignee with ID %s is in status %s",
                                request.getAssigneeId(),
                                assignee.getAccountStatus().getAccountStatusName()
                        )
                );
            }
        }

        var task = TaskItem.builder()
                .name(request.getName())
                .assignee(assignee)
                .parentTask(parentTask)
                .taskType(parentTask.getTaskType().getChild())
                .taskStatus(TaskStatus.TODO)
                .build();

        taskRepository.save(task);
    }

    public List<TaskResponseDTOshort> findAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(ConverterDTO::convertTaskToDTOshort)
                .toList();
    }

    public TaskResponseDTO findTaskById(Long taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Task with ID %s cannot be found", taskId)
                        )
                );
        return ConverterDTO.convertTaskToDTO(task);
    }

    public void promoteTaskById(Long taskId) {
        var task = taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        task.setTaskStatus(task.getTaskStatus().getNextPhase());

        if (task.getTaskStatus() == TaskStatus.DONE) {
            var subTasks = task.getChildren();
            subTasks.forEach(t -> t.setTaskStatus(TaskStatus.DONE));
            taskRepository.saveAll(subTasks);
        }

        taskRepository.save(task);
    }

    public void demoteTaskById(Long taskId) {
        var task = taskRepository
                .findById(taskId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Task with ID %s cannot be found", taskId)
                        )
                );

        boolean fromDone = task.getTaskStatus() == TaskStatus.DONE;

        var previous = task.getTaskStatus().getPreviousPhase();
        task.setTaskStatus(previous);

        if (fromDone) {
            taskRepository
                    .findAllByParentTask(task)
                    .stream()
                    .peek(t -> t.setTaskStatus(previous))
                    .forEach(taskRepository::save);

        }

        taskRepository.save(task);
    }

}
