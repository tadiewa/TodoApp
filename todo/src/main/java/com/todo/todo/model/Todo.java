/**
 * @author : tadiewa
 * date: 11/15/2024
 */


package com.todo.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 500)
    private String description;

    private Boolean completed = false;

    @Column(nullable = false)
    private String createdBy;

    @Column(nullable = false)
    private LocalDateTime created;

    private LocalDateTime updated;

    private LocalDate dueDate;
}

