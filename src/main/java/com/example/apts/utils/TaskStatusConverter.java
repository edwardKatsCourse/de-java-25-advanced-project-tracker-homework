package com.example.apts.utils;

import com.example.apts.entity.TaskStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TaskStatusConverter implements AttributeConverter<TaskStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TaskStatus taskStatus) {
        return taskStatus == null ? null : taskStatus.getTaskStatusId();
    }

    @Override
    public TaskStatus convertToEntityAttribute(Integer integer) {
        return integer == null ? null : TaskStatus.getTaskStatusById(integer);
    }
}
