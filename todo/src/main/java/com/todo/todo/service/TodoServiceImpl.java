/**
 * @author : tadiewa
 * date: 11/15/2024
 */


package com.todo.todo.service;

import com.todo.todo.model.Todo;
import com.todo.todo.model.TodoRequest;
import com.todo.todo.model.TodoResponse;
import com.todo.todo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class TodoServiceImpl implements TodoService{
  private  final TodoRepository todoRepository;

  @Override
  public TodoResponse create(TodoRequest request) {
    log.info("request---------------------------------------------------> {}", request);
    if(request== null){
      throw new IllegalArgumentException("Request cannot be null");
    }
    var date = request.getDueDate();
    log.info("date --------------------------------->{}", date);

    Todo   savedTodo = new Todo();
      savedTodo.setCreatedBy(request.getCreatedBy());
      savedTodo.setCreated(LocalDateTime.now());
      savedTodo.setTitle(request.getTitle());
      savedTodo.setDescription(request.getDescription());
      savedTodo.setCompleted(request.getCompleted());
      savedTodo.setUpdated(LocalDateTime.now());
      savedTodo.setDueDate(request.getDueDate());
log.info("savedTodo----------------------------------------------------->{}",savedTodo);
      todoRepository.save(savedTodo);
//    Todo savedTodo = todoRepository.save(Todo.builder()
//            .created(LocalDateTime.now())
//            .createdBy(request.getCreatedBy())
//            .title(request.getTitle())
//            .description(request.getDescription())
//            .completed(request.getCompleted())
//            .updated(LocalDateTime.now())
//            .dueDate(request.getDueDate())
//            .build());
    log.info("request saved---------------------------------------------------------------->");

    return  TodoResponse.builder()
            .title(savedTodo.getTitle())
            .description(savedTodo.getDescription())
            .completed(savedTodo.getCompleted())
            .createdBy(savedTodo.getCreatedBy())
            .created(savedTodo.getCreated())
            .updated(savedTodo.getUpdated())
            .dueDate(savedTodo.getDueDate())
            .build();
  }

  @Override
    public TodoResponse update(Long id, TodoRequest todoRequest) {
      log.info("Updating Todo with id: {}", id);

      // Find the existing Todo by id
      Todo existingTodo = todoRepository.findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Todo not found with id: " + id));

      // Create a new Todo object using the builder pattern with updated values
    existingTodo.setTitle(todoRequest.getTitle()); // Update title
    existingTodo.setDescription(todoRequest.getDescription()); // Update description (if provided)
    existingTodo.setCompleted(todoRequest.getCompleted()); // Update completed status
    existingTodo.setUpdated(LocalDateTime.now()); // Update the timestamp
    existingTodo.setDueDate(todoRequest.getDueDate());

    Todo updatedTodo = todoRepository.save(existingTodo);


      // Map the updated entity to a response object
      return TodoResponse.builder()
              .id(updatedTodo.getId())
              .title(updatedTodo.getTitle())
              .description(updatedTodo.getDescription())
              .completed(updatedTodo.getCompleted())
              .createdBy(updatedTodo.getCreatedBy())
              .created(updatedTodo.getCreated())
              .updated(updatedTodo.getUpdated())
              .dueDate(updatedTodo.getDueDate())
              .build();
    }

  @Override
  public void delete(Long id) {
    log.info("Deleting Todo with id: {}", id);

    // Check if the entity exists
    Todo existingTodo = todoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Todo not found with id: " + id));

    // Delete the entity
    todoRepository.delete(existingTodo);

    log.info("Todo with id: {} deleted successfully", id);
  }

  @Override
  public TodoResponse get(Long id) {

    log.info("getting object for id: {}",id);
    Todo existingTodo = todoRepository.findById(id)
            .orElseThrow(()-> new EntityNotFoundException("Todo not found with id: " + id));


    return  TodoResponse.builder()
            .title(existingTodo.getTitle())
            .description(existingTodo.getDescription())
            .completed(existingTodo.getCompleted())
            .createdBy(existingTodo.getCreatedBy())
            .created(existingTodo.getCreated())
            .updated(existingTodo.getUpdated())
            .dueDate(existingTodo.getDueDate())
            .build();
  }

//  @Override
//  public List<TodoResponse> getAll() {
//    log.info("Fetching all Todos");
//
//    // Fetch all Todos from the repository
//    List<Todo> todos = todoRepository.findAll();
//    if (todos.isEmpty())
//      throw new RuntimeException("no todos");
//
//    // Map entities to response objects
//    return todos.stream()
//            .map(todo -> TodoResponse.builder()
//                    .title(todo.getTitle())
//                    .description(todo.getDescription())
//                    .completed(todo.getCompleted())
//                    .created(todo.getCreated())
//                    .updated(todo.getUpdated())
//                    .dueDate(todo.getDueDate())
//                    .createdBy(todo.getCreatedBy())
//                    .build())
//            .collect(Collectors.toList());
//  }

  public Page<TodoResponse> getAll(Pageable pageable) {
    log.info("Fetching all Todos with pagination");

    // Fetch paginated Todos from the repository
    Page<Todo> todoPage = todoRepository.findAll(pageable);
    var px = todoPage.getContent();
    log.info("todpage------------------------------------------------------------->{}",px);
    // Map entities to response objects
    return todoPage.map(todo -> TodoResponse.builder()
            .id(todo.getId())
            .title(todo.getTitle())
            .description(todo.getDescription())
            .completed(todo.getCompleted())
            .created(todo.getCreated())
            .updated(todo.getUpdated())
            .dueDate(todo.getDueDate())
            .createdBy(todo.getCreatedBy())
            .build());
  }
}
