package com.example.apts.dto;

import com.example.apts.entity.type.TaskStatus;
import com.example.apts.entity.type.TaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskResponseDTOshort {
        private Long id;
        private String name;
        private TaskType taskType;
        private TaskStatus taskStatus;
}
