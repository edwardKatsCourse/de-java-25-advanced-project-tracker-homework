package com.example.apts.repository;

import com.example.apts.entity.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssigneeRepository extends JpaRepository<Assignee, Long> {
}
