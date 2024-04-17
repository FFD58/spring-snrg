package ru.fafurin.lesson3.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.fafurin.lesson3.exception.TaskNotFoundException;
import ru.fafurin.lesson3.model.Task;
import ru.fafurin.lesson3.model.TaskStatus;
import ru.fafurin.lesson3.service.TaskService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    @PostMapping("/add")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping()
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable TaskStatus status) {
        return taskService.getTasksByStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task) {
        try {
            return taskService.updateTaskStatus(id, task);
        } catch (TaskNotFoundException e) {
            throw new TaskNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
