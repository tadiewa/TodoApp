/**
 * @author : tadiewa
 * date: 11/15/2024
 */

package com.todo.todo.repository;

import com.todo.todo.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    Page<Todo> findAll(Pageable pageable);
}
