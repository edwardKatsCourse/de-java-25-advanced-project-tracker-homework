package com.example.apts.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TaskType {
    PROJECT(1, "Project"),
    FEATURE(2, "Feature"),
    EPIC(3, "Epic"),
    TASK(4, "Task");

    private Integer taskTypeId;
    private String taskTypeName;

    @JsonValue
    public String getTaskTypeName() {
        return taskTypeName;
    }

    public static TaskType getTaskTypeById(Integer typeId) {
        if (typeId == null) {
            throw new IllegalArgumentException(String.format("Task type with ID [%s] does not exist", typeId));
        }

        return Arrays.stream(TaskType.values())
                .filter(x -> x.getTaskTypeId().equals(typeId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Task type with ID [%s] does not exist", typeId)));
    }

    public static TaskType getTaskTypeByName(String typeName) {
        if (typeName == null) {
            throw new IllegalArgumentException(String.format("Task type with name [%s] does not exist", typeName));
        }

        return Arrays.stream(TaskType.values())
                .filter(x -> x.getTaskTypeName().equals(typeName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Task type with name [%s] does not exist", typeName)));
    }
    @JsonCreator
    public static TaskType getTaskTypeByParent(TaskItem parentTask) {
        if (parentTask == null) {
            return PROJECT;
        }

        return Arrays.stream(TaskType.values())
                .filter(x -> x.getTaskTypeId()
                        .equals(parentTask.getTaskType().taskTypeId + 1))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        String.format("Cannot create a subtask out of the lowest task type [Task]")
                ));
    }
}
