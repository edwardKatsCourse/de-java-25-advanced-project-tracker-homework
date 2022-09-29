package com.example.apts.utils;

import com.example.apts.entity.TaskType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TaskTypeConverter implements AttributeConverter<TaskType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TaskType taskType) {
        return taskType == null ? null : taskType.getTaskTypeId();
    }

    @Override
    public TaskType convertToEntityAttribute(Integer integer) {
        return integer == null ? null : TaskType.getTaskTypeById(integer);
    }
}
