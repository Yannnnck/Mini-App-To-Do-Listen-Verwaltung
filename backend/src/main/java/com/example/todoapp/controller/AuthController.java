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
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final TodoService todoService;

    public AuthController(UserService userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String username, 
                                               @RequestParam String email, 
                                               @RequestParam String password) {
        if (userService.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body("Benutzername existiert bereits");
        }
        userService.registerUser(username, email, password);
        return ResponseEntity.ok("Benutzer erfolgreich registriert");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isPresent() && 
            new BCryptPasswordEncoder().matches(password, userOptional.get().getPassword())) {
            return ResponseEntity.ok("Login erfolgreich");
        }
        return ResponseEntity.badRequest().body("Ungültige Anmeldedaten");
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getUserTodos(@RequestParam String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isPresent()) {
            List<Todo> todos = todoService.getTodosByUser(userOptional.get());
            return ResponseEntity.ok(todos);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/todos")
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
}
