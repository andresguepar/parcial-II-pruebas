package com.todolist.mapping.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO (Data Transfer Object) que representa una tarea para transferir datos
 * entre capas de la aplicación. Contiene atributos como id, título, descripción,
 * prioridad, fechas de creación y límite, estado de completado y nivel de recompensa.
 * Utiliza anotaciones Lombok para la generación automática de métodos.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private int id;
    private String title;

}
