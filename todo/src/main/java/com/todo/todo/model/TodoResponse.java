/**
 * @author : tadiewa
 * date: 11/15/2024
 */


package com.todo.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class TodoResponse {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private String createdBy;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDate dueDate;
}
