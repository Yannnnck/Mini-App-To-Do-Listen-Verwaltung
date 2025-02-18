package com.example.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.todoapp.model")
public class TodoAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoAppApplication.class, args);
    }
}