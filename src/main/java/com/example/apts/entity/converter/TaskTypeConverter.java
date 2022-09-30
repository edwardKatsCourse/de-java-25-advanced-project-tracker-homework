package com.example.apts.entity.converter;

import com.example.apts.entity.type.TaskType;

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
