package com.example.apts.dto;

import com.example.apts.entity.type.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AssigneeResponseDTO {
    private Long id;
    private String name;
    private AccountStatus accountStatus;
}
