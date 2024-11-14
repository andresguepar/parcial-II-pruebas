package com.todolist;

import com.todolist.mapping.dtos.TaskDto;
import com.todolist.services.impl.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskServiceImpl service;

    public TaskController(TaskServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> listTasks() {
        return ResponseEntity.ok(service.list());
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        try {
            TaskDto createdTask = service.save(taskDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") int id) {
        Optional<TaskDto> taskDto = Optional.ofNullable(service.byId(id));
        return taskDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") int id, @RequestBody TaskDto taskDto) {
        Optional<TaskDto> existingTask = Optional.ofNullable(service.byId(id));
        if (existingTask.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        TaskDto updatedTask = service.save(taskDto);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}