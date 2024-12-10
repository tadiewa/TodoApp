/**
 * @author : tadiewa
 * date: 11/15/2024
 */

package com.todo.todo.service;

import com.todo.todo.model.TodoRequest;
import com.todo.todo.model.TodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TodoService {

    TodoResponse create(TodoRequest todo);

    TodoResponse update(Long id ,TodoRequest todo);
     void delete(Long id);

    TodoResponse get(Long id);

    Page<TodoResponse> getAll(Pageable pageable);

    // List<TodoResponse> getAll();
}
