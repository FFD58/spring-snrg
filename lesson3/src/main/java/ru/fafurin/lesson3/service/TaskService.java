package ru.fafurin.lesson3.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fafurin.lesson3.exception.TaskNotFoundException;
import ru.fafurin.lesson3.model.Task;
import ru.fafurin.lesson3.model.TaskStatus;
import ru.fafurin.lesson3.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public Task addTask(Task task) {
        return repository.save(task);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return repository.findByStatus(status);
    }

    public Task updateTaskStatus(Long id, Task task) {
        Optional<Task> updatedTaskOptional = repository.findById(id);
        if (updatedTaskOptional.isPresent()) {
            Task updatedTask = updatedTaskOptional.get();
            updatedTask.setDescription(task.getDescription());
            updatedTask.setStatus(task.getStatus());
            return repository.save(updatedTask);
        } else throw new TaskNotFoundException(id);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
