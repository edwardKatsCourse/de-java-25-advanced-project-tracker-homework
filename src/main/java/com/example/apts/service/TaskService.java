package com.example.apts.service;


import com.example.apts.dto.TaskRequestDTO;
import com.example.apts.dto.TaskResponseDTO;
import com.example.apts.dto.TaskResponseDTOshort;

import java.util.List;

public interface TaskService {

    void createTask(TaskRequestDTO request);
    List<TaskResponseDTOshort> findAllTasks();
    TaskResponseDTO findTaskById(Long taskId);

    void promoteTaskById(Long taskId);
    void demoteTaskById(Long taskId);
}
