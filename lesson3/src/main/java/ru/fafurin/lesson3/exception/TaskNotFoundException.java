package ru.fafurin.lesson3.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super(String.format("Task with id %d not found", id));
    }
}
