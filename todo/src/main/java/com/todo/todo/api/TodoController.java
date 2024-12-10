/**
 * @author : tadiewa
 * date: 11/15/2024
 */


package com.todo.todo.api;

import com.todo.todo.model.TodoRequest;
import com.todo.todo.model.TodoResponse;
import com.todo.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping(value="v1/todo")
@Slf4j
@RequiredArgsConstructor
public class TodoController {
  private  final TodoService todoService;


 @RequestMapping(method = RequestMethod.OPTIONS)
 public ResponseEntity<Void> handlePreflight() {
  log.info("----------------------------------------------------------------------->Preflight request received");
  return ResponseEntity.ok().build();
 }

   @PostMapping("/create")
   public TodoResponse create(@RequestBody TodoRequest todo){
    log.info("todo------------------------------------->{}",todo);
    if(todo == null){
     throw new IllegalArgumentException("Request cannot be null");
    }
     return todoService.create(todo);
   }

   @PutMapping("/{id}")
   public TodoResponse update(@PathVariable Long id ,@RequestBody TodoRequest todo){
    log.info("update------------------------------------->id:{}  todo:{}",id ,todo);
    if(todo == null) {
     throw new IllegalArgumentException("Request cannot be null");
    }
    return todoService.update(id ,todo);
   }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> delete(@PathVariable Long id) {
  log.info("Request received to delete Todo with id: {}", id);
  todoService.delete(id);
  return ResponseEntity.ok().build();
 }


  @GetMapping("/{id}")
 public TodoResponse get(@PathVariable Long id){
   log.info("Request received to get Todo with id: {}", id);
  return todoService.get(id);

  }

// @GetMapping
// public ResponseEntity<List<TodoResponse>> getAllTodos() {
//  log.info("Request received to fetch all Todos");
//  // Fetch all Todos via the service
//  List<TodoResponse> todos = todoService.getAll();
//
//  return ResponseEntity.ok(todos);
// }


 @GetMapping
 public ResponseEntity<Page<TodoResponse>> getAllTodos(
         @RequestParam(defaultValue = "0") int page,
         @RequestParam(defaultValue = "5") int size
 ) {
  log.info("Request received to fetch all Todos with pagination");

  // Create a Pageable object
  Pageable pageable = PageRequest.of(page, size);

  // Fetch paginated Todos via the service
  Page<TodoResponse> todos = todoService.getAll(pageable);

  return ResponseEntity.ok(todos);
 }
}
