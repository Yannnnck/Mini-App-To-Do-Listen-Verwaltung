package com.example.todoapp.service;

import com.example.todoapp.model.User;
import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodosByUser(User user) {
        return todoRepository.findByUser(user);
    }

    public void addTodo(User user, String title, String description) {
        Todo todo = new Todo();
        todo.setUser(user);
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setCompleted(false);
        todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
