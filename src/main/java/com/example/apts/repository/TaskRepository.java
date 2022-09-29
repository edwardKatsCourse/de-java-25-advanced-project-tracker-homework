package com.example.apts.repository;

import com.example.apts.entity.TaskItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskItem, Long> {

    List<TaskItem> findAllByParentTask(TaskItem parentTask);
}
