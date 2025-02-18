package com.example.todoapp.controller;

import com.example.todoapp.model.Todo;
import com.example.todoapp.model.User;
import com.example.todoapp.service.TodoService;
import com.example.todoapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;
    private final UserService userService;

    public TodoController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Todo>> getTodosByUser(@PathVariable String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isPresent()) {
            List<Todo> todos = todoService.getTodosByUser(userOptional.get());
            return ResponseEntity.ok(todos);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTodo(@RequestParam String username,
                                          @RequestParam String title,
                                          @RequestParam String description) {
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isPresent()) {
            todoService.addTodo(userOptional.get(), title, description);
            return ResponseEntity.ok("To-Do erfolgreich hinzugefügt");
        }
        return ResponseEntity.badRequest().body("Benutzer nicht gefunden");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("To-Do erfolgreich gelöscht");
    }
}
