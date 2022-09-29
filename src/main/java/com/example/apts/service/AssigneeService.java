package com.example.apts.service;

import com.example.apts.dto.AssigneeRequestDTO;
import com.example.apts.dto.AssigneeResponseDTO;

import java.util.List;

public interface AssigneeService {

    void createAssignee(AssigneeRequestDTO request);
    List<AssigneeResponseDTO> findAllAssignees();

    AssigneeResponseDTO findAssigneeById(Long assigneeId);

    void toggleAssignee(Long id);
}
