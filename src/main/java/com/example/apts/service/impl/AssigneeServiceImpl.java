package com.example.apts.service.impl;

import com.example.apts.dto.AssigneeRequestDTO;
import com.example.apts.dto.AssigneeResponseDTO;
import com.example.apts.entity.type.AccountStatus;
import com.example.apts.entity.Assignee;
import com.example.apts.repository.AssigneeRepository;
import com.example.apts.service.AssigneeService;
import com.example.apts.utils.ConverterDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class AssigneeServiceImpl implements AssigneeService {

    private final AssigneeRepository assigneeRepository;

    public void createAssignee(AssigneeRequestDTO request) {
        Assignee assignee = ConverterDTO.convertDTOToAssignee(request);
        assigneeRepository.save(assignee);
    }

    public List<AssigneeResponseDTO> findAllAssignees() {
        return assigneeRepository.findAll().stream()
                .map(ConverterDTO::convertAssigneeToDTO)
                .toList();
    }

    public AssigneeResponseDTO findAssigneeById(Long assigneeId) {
        Assignee assignee = assigneeRepository.findById(assigneeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ConverterDTO.convertAssigneeToDTO(assignee);
    }

    public void toggleAssignee(Long id) {

        Assignee assignee = assigneeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        AccountStatus status = assignee.getAccountStatus();

        if (status == null || status == AccountStatus.INACTIVE) {
            assignee.setAccountStatus(AccountStatus.ACTIVE);
        } else {
            assignee.setAccountStatus(AccountStatus.INACTIVE);
        }
        assigneeRepository.save(assignee);
    }


}
