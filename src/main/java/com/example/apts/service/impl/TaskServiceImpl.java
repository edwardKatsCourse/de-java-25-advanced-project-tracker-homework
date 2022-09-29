package com.example.apts.service.impl;

import com.example.apts.dto.TaskRequestDTO;
import com.example.apts.dto.TaskResponseDTO;
import com.example.apts.dto.TaskResponseDTOshort;
import com.example.apts.entity.Assignee;
import com.example.apts.entity.TaskItem;
import com.example.apts.entity.TaskStatus;
import com.example.apts.entity.TaskType;
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
    private final ConverterDTO converterDTO;

    public void createTask(TaskRequestDTO request) {

        Assignee assignee = request.getAssigneeId() == null
                ? null
                : assigneeRepository.findById(request.getAssigneeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Assignee with ID %s cannot be found", request.getAssigneeId())));
        var parentTask = request.getParentTaskId() == null
                ? null
                : taskRepository.findById(request.getParentTaskId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Parent task with ID %s cannot be found", request.getParentTaskId())));

        if (assignee != null && assignee.getAccountStatus() != null) {
            if (assignee.getAccountStatus()
                    .getAccountStatusName()
                    .equalsIgnoreCase("Inactive")) {
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                        String.format("Assignee with ID %s is in status %s",
                                request.getAssigneeId(),
                                assignee.getAccountStatus().getAccountStatusName()));
            }
        }

        var task = TaskItem.builder()
                .name(request.getName())
                .assignee(assignee /*== null
                        || assignee.getAccountStatus() == null
                        || assignee.getAccountStatus()
                        .getAccountStatusName()
                        .equalsIgnoreCase("Inactive")
                        ? null : assignee*/)
                .parentTask(parentTask)
                .taskType(TaskType.getTaskTypeByParent(parentTask))
                .taskStatus(TaskStatus.getTaskStatusByName(null))
                .build();

        taskRepository.save(task);
    }

    public List<TaskResponseDTOshort> findAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(converterDTO::convertTaskToDTOshort)
                .toList();
    }

    public TaskResponseDTO findTaskById(Long taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Task with ID %s cannot be found", taskId)));
        return converterDTO.convertTaskToDTO(task);
    }

    public void promoteTaskById(Long taskId) {
        var task = taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        task.setTaskStatus(task.getTaskStatus().getNextPhase());
        if (task.getTaskStatus().getTaskStatusName().equalsIgnoreCase("Done")) {
            var subTasks = taskRepository.findAllByParentTask(task);
            subTasks.forEach(t -> t.setTaskStatus(TaskStatus.DONE));
            taskRepository.saveAll(subTasks);
        }
        taskRepository.save(task);
    }

    public void demoteTaskById(Long taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Task with ID %s cannot be found", taskId)));
        boolean fromDone = task.getTaskStatus().getTaskStatusName().equalsIgnoreCase("Done");

        var previous = task.getTaskStatus().getPreviousPhase();
        task.setTaskStatus(previous);

        if (fromDone) {
            var subTasks = taskRepository.findAllByParentTask(task);
            subTasks.forEach(t -> t.setTaskStatus(previous));
            taskRepository.saveAll(subTasks);
        }

        taskRepository.save(task);
    }

}
