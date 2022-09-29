package com.example.apts.controller;

import com.example.apts.dto.AssigneeRequestDTO;
import com.example.apts.dto.AssigneeResponseDTO;
import com.example.apts.service.AssigneeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AssigneeController {

    private final AssigneeService assigneeService;

    @PostMapping("/assignees")
    public void createAssignee(@RequestBody AssigneeRequestDTO request) {
        assigneeService.createAssignee(request);
    }

    @GetMapping("/assignees")
    public List<AssigneeResponseDTO> findAllAssignees() {
        return assigneeService.findAllAssignees();
    }

    @GetMapping("/assignees/{id}")
    public AssigneeResponseDTO findAssigneeById(@PathVariable(name = "id") Long assigneeId) {
        return assigneeService.findAssigneeById(assigneeId);
    }

    @PatchMapping("/assignees/{id}/toggle-active")
    public void toggleAssignee(@PathVariable(name = "id") Long assigneeId) {
        assigneeService.toggleAssignee(assigneeId);
    }

}
