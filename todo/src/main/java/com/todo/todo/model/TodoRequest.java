/**
 * @author : tadiewa
 * date: 11/15/2024
 */


package com.todo.todo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
public class TodoRequest {
    String title ;
    String description ;
    Boolean completed ;
    String createdBy;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDate dueDate;
}
