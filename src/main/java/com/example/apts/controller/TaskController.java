package com.example.apts.controller;

import com.example.apts.dto.TaskRequestDTO;
import com.example.apts.dto.TaskResponseDTO;
import com.example.apts.dto.TaskResponseDTOshort;
import com.example.apts.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/tasks")
    public void createTask(@RequestBody TaskRequestDTO request) {
        taskService.createTask(request);
    }

    @GetMapping("/tasks")
    public List<TaskResponseDTOshort> findAllTasks() {
        return taskService.findAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public TaskResponseDTO findTaskById(@PathVariable(name = "id") Long taskId) {

        return taskService.findTaskById(taskId);
    }

    @PatchMapping("/tasks/{id}/promote")
    public void promoteTaskById(@PathVariable(name = "id") Long taskId) {
        taskService.promoteTaskById(taskId);
    }

    @PatchMapping("/tasks/{id}/demote")
    public void demoteTaskById(@PathVariable(name = "id") Long taskId) {
        taskService.demoteTaskById(taskId);
    }


}
