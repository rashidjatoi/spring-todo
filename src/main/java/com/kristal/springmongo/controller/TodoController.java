package com.kristal.springmongo.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kristal.springmongo.model.TodoModel;
import com.kristal.springmongo.repository.TodoRepository;

@RestController
public class TodoController {

    // Auto Wiring the object and map it
    @Autowired
    private TodoRepository todoRepository;

    // Getting all the todos API
    @GetMapping(path = "/todos")
    public ResponseEntity<?> getAllTodo() {
        List<TodoModel> todos = todoRepository.findAll();
        if (todos.size() > 0) {
            return new ResponseEntity<List<TodoModel>>(todos, HttpStatus.OK);
        }
        return new ResponseEntity<>("No todo found", HttpStatus.OK);
    }

    // Add Todo API
    @PostMapping(path = "/todo")
    public ResponseEntity<?> addTodo(@RequestBody TodoModel todoModel) {
        try {
            Date currentDate = new Date(System.currentTimeMillis());
            todoModel.setCreatedAt(currentDate);
            TodoModel todo = todoRepository.save(todoModel);
            return new ResponseEntity<>(todo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Todo by Id API
    @GetMapping(path = "/todo/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable("id") String id) {
        try {
            if (id != null) {
                Optional<TodoModel> todOptional = todoRepository.findById(id);
                if (todOptional.isPresent()) {
                    return new ResponseEntity<>(todOptional.get(), HttpStatus.OK);
                }
                return new ResponseEntity<>("No Todo Found with this Id", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Please Enter Id", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    // Update Todo API
    @PutMapping(path = "/todo/{id}")
    public ResponseEntity<?> updateTodoById(@PathVariable("id") String id, @RequestBody TodoModel todoModel) {
        try {
            if (id != null) {
                Optional<TodoModel> todOptional = todoRepository.findById(id);
                if (todOptional.isPresent()) {
                    TodoModel existingTodo = todOptional.get();
                    existingTodo.setTodo(todoModel.getTodo());
                    existingTodo.setDescription(todoModel.getDescription());
                    existingTodo.setIsCompleted(todoModel.getIsCompleted());
                    existingTodo.setUpdatedAt(new Date(System.currentTimeMillis()));
                    todoRepository.save(existingTodo);
                    return new ResponseEntity<>("Todo Updated", HttpStatus.OK);
                }
                return new ResponseEntity<>("No Todo Found with this Id", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Please Enter Id", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    // Delete Todo API
    @DeleteMapping(path = "/todo/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") String id) {
        try {
            if (id != null) {
                todoRepository.deleteById(id);
                return new ResponseEntity<>("Todo Deleted", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Please Enter Id", HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
