package com.todolist.mapping.mappers;

import com.todolist.domain.entities.Task;
import com.todolist.mapping.dtos.TaskDto;

import java.util.List;

/**
 * Clase encargada de mapear entre las entidades Task y TaskDto, facilitando la
 * conversión de datos entre estas dos representaciones. Incluye métodos para
 * convertir de Task a TaskDto y viceversa, así como para listas de ambas clases.
 * Utiliza streams paralelos para mejorar el rendimiento en las conversiones de listas.
 */

public class TaskMapper {
    public static TaskDto mapFrom(Task source){
        return TaskDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .build();
    }

    public static Task mapFrom(TaskDto source){
        return new Task(source.getId(),
                source.getTitle(),
                source.getDescription(),
                source.getPriority(),
                source.getCreationDate(),
                source.getLimitDate(),
                source.isCompleted(),
                source.getCompletedDate(),
                source.getRewardLevel()

           );
    }

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
