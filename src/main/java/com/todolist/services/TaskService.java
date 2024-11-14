package com.todolist.services;

import com.todolist.mapping.dtos.TaskDto;

import java.util.List;
/**
 * Interfaz que define los contratos para los servicios relacionados con la gestión de tareas.
 * Incluye métodos para listar tareas, obtener tareas por ID, guardar, actualizar y eliminar tareas,
 * así como asignar prioridades. También soporta funcionalidades de modo de enfoque, estadísticas
 * de tareas y métodos para obtener listas de tareas ordenadas por prioridad, fecha de vencimiento
 * y estado de completado.
 */

public interface TaskService {
    List<TaskDto> list();

    TaskDto byId(int id);

    TaskDto save(TaskDto t);

    void update(int id, TaskDto updated);

    void delete(int id);
}

