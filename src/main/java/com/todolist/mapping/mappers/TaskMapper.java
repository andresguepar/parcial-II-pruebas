package com.todolist.mapping.mappers;

import com.todolist.domain.entities.Task;
import com.todolist.mapping.dtos.TaskDto;

import java.util.List;


public class TaskMapper {
    public static TaskDto mapFrom(Task source){
        return TaskDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .build();
    }

    public static Task mapFrom(TaskDto source){
        return new Task(source.getId(),
                source.getTitle()
           );
    }
//++
    public static List<Task> mapFrom(List<TaskDto> source){
        return source.parallelStream()
                .map(TaskMapper::mapFrom)
                .toList();

    }
    public static List<TaskDto> mapFromDto(List<Task> source){
        return source.parallelStream()
                .map(TaskMapper::mapFrom)
                .toList();

    }
}
