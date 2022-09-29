package com.example.apts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "assignees")

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Assignee {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "account_status")
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "assignee")
    private List<TaskItem> tasks;
}
