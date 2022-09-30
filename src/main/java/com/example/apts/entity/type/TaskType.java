package com.example.apts.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TaskType {

    TASK(4, "Task", null),
    EPIC(3, "Epic", TaskType.TASK),
    FEATURE(2, "Feature", TaskType.EPIC),
    PROJECT(1, "Project", TaskType.FEATURE),

    ;


    private final Integer taskTypeId;
    private final String taskTypeName;
    private final TaskType child;

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

    // JSON ->  backend -> DB
    // Project -> enum -> int

    @JsonCreator
    public static TaskType getTaskTypeByName(String typeName) {
        if (typeName == null) {
            throw new IllegalArgumentException(String.format("Task type with name [%s] does not exist", typeName));
        }

        return Arrays.stream(TaskType.values())
                .filter(x -> x.getTaskTypeName().equalsIgnoreCase(typeName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Task type with name [%s] does not exist", typeName)));
    }


    public TaskType getChild() {
        return this.child;
    }
}
