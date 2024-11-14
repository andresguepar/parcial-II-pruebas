package com.todolist.services.impl;

import com.todolist.domain.entities.Task;
import com.todolist.mapping.dtos.TaskDto;
import com.todolist.mapping.mappers.TaskMapper;
import com.todolist.repositories.TaskRepository;
import com.todolist.services.TaskService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
//..

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }


    @Override
    @Cacheable("tasks")
    public List<TaskDto> list() {
        List<Task> Task = repository.findAll();
        return TaskMapper.mapFromDto(Task);
    }

    @Override
    public TaskDto byId(int id) {
        Task Task = repository.findById(id).orElseThrow();
        return TaskMapper.mapFrom(Task);
    }

    @Override
    public TaskDto save(TaskDto t) {
        if (t.getTitle() == null || t.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be null or empty");
        }

        Task task = TaskMapper.mapFrom(t);
        repository.save(task);
        return t;
    }

    @Override
    public void update(int id, TaskDto update) {
        Task task = repository.findById(id).orElseThrow();
        Task updated = TaskMapper.mapFrom(update);

        task.setTitle(updated.getTitle());
        repository.save(task);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }


}
