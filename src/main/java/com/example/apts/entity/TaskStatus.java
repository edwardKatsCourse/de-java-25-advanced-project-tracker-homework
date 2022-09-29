package com.example.apts.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

@AllArgsConstructor
@Getter
public enum TaskStatus {
    TODO(1, "To Do"),
    ANALYSIS(2, "Analysis"),
    IN_PROGRESS(3, "In Progress"),
    DONE(4, "Done");

    private Integer taskStatusId;
    private String taskStatusName;

    @JsonValue
    public String getTaskStatusName() {
        return taskStatusName;
    }

    public static TaskStatus getTaskStatusById(Integer statusId) {
        if (statusId == null) {
            return TaskStatus.TODO;
        }

        return Arrays.stream(TaskStatus.values())
                .filter(x -> x.getTaskStatusId().equals(statusId))
                .findFirst()
                .orElse(null);
    }

    @JsonCreator
    public static TaskStatus getTaskStatusByName(String statusName) {
        if (statusName == null) {
            return TaskStatus.TODO;
        }

        return Arrays.stream(TaskStatus.values())
                .filter(x -> x.getTaskStatusName().equalsIgnoreCase(statusName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Task status with name [%s] does not exist", statusName)));
    }

    public TaskStatus getNextPhase() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing(TaskStatus::getTaskStatusId))
                .filter(x -> x.getTaskStatusId().equals(this.getTaskStatusId() + 1))
                .findFirst()
                .orElse(TaskStatus.DONE);
    }

    public TaskStatus getPreviousPhase() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing(TaskStatus::getTaskStatusId))
                .filter(x -> x.getTaskStatusId().equals(this.getTaskStatusId() - 1))
                .findFirst()
                .orElse(TaskStatus.TODO);
    }

}
