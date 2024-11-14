package com.todolist.services;

import com.todolist.mapping.dtos.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> list();

    TaskDto byId(int id);

    TaskDto save(TaskDto t);

    void update(int id, TaskDto updated);

    void delete(int id);
}

