package com.example.apts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskRequestDTO {
    private String name;
    private Long assigneeId;
    private Long parentTaskId;
}
