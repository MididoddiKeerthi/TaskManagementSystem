package com.codewithkeerthi.fullstackbackend.controller;

import com.codewithkeerthi.fullstackbackend.exception.TaskNotFoundException;
import com.codewithkeerthi.fullstackbackend.model.Task;
import com.codewithkeerthi.fullstackbackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/task")
    public ResponseEntity<?> newTask(@RequestBody Task newTask) {
        // Validate description length
        if (newTask.getDescription().length() > 255) {
            return ResponseEntity.badRequest().body("Description must be less than 255 characters.");
        }

        // Save the task
        Task savedTask = taskRepository.save(newTask);
        return ResponseEntity.ok(savedTask);
    }

    @GetMapping("/tasks")
    List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/task/{id}")
    Task getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PutMapping("/task/{id}")
    ResponseEntity<?> updateTask(@RequestBody Task newTask, @PathVariable Long id) {
        // Validate description length
        if (newTask.getDescription().length() > 255) {
            return ResponseEntity.badRequest().body("Description must be less than 255 characters.");
        }

        // Update the task
        Task updatedTask = taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(newTask.getTitle());
                    task.setDescription(newTask.getDescription());
                    task.setDeadline(newTask.getDeadline());
                    task.setPriority(newTask.getPriority());
                    task.setStatus(newTask.getStatus());
                    return taskRepository.save(task);
                }).orElseThrow(() -> new TaskNotFoundException(id));

        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/task/{id}")
    String deleteTask(@PathVariable Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
        return "Task with id " + id + " has been deleted successfully.";
    }
}