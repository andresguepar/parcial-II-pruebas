package com.todolist.unitTest;

import com.todolist.domain.entities.Task;
import com.todolist.mapping.dtos.TaskDto;
import com.todolist.mapping.mappers.TaskMapper;
import com.todolist.repositories.TaskRepository;
import com.todolist.services.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UnitTest {
    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task existingTask;
    private TaskDto existingTaskDto;

    @BeforeEach
    void setUp() {
        existingTask = new Task();
        existingTask.setId(1);
        existingTask.setTitle("Existing Task");
        existingTask.setDescription("Existing Description");
        existingTask.setStatus(false);

        existingTaskDto = new TaskDto();
        existingTaskDto.setId(1);
        existingTaskDto.setTitle("Existing Task");
        existingTaskDto.setDescription("Existing Description");
        existingTaskDto.setStatus(false);
    }

    @Test
    void byId_ExistingTask_ReturnsTaskDto() {
        // Arrange
        when(repository.findById(1)).thenReturn(Optional.of(existingTask));

        // Act
        TaskDto result = taskService.byId(1);

        // Assert
        assertNotNull(result);
        assertEquals("Existing Task", result.getTitle());
        assertEquals("Existing Description", result.getDescription());
        assertFalse(result.isStatus());
        verify(repository).findById(1);
    }

    @Test
    void byId_NonExistingTask_ThrowsNoSuchElementException() {
        // Arrange
        when(repository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            taskService.byId(999);
        });
    }

    @Test
    void save_ValidTask_SavesSuccessfully() {
        // Arrange
        TaskDto newTaskDto = new TaskDto();
        newTaskDto.setTitle("New Task");
        newTaskDto.setDescription("New Description");
        newTaskDto.setStatus(true);

        Task newTask = TaskMapper.mapFrom(newTaskDto);
        when(repository.save(any(Task.class))).thenReturn(newTask);

        // Act
        TaskDto savedTask = taskService.save(newTaskDto);

        // Assert
        assertNotNull(savedTask);
        assertEquals("New Task", savedTask.getTitle());
        assertEquals("New Description", savedTask.getDescription());
        assertTrue(savedTask.isStatus());
        verify(repository).save(any(Task.class));
    }

    @Test
    void save_EmptyTitle_ThrowsIllegalArgumentException() {
        // Arrange
        TaskDto invalidTaskDto = new TaskDto();
        invalidTaskDto.setTitle("");
        invalidTaskDto.setDescription("Some Description");
        invalidTaskDto.setStatus(false);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.save(invalidTaskDto);
        });
    }

    @Test
    void update_ExistingTask_UpdatesSuccessfully() {
        // Arrange
        TaskDto updateDto = new TaskDto();
        updateDto.setTitle("Updated Task");
        updateDto.setDescription("Updated Description");
        updateDto.setStatus(true);

        when(repository.findById(1)).thenReturn(Optional.of(existingTask));
        when(repository.save(any(Task.class))).thenReturn(existingTask);

        // Act
        taskService.update(1, updateDto);

        // Assert
        verify(repository).findById(1);
        verify(repository).save(existingTask);
        assertEquals("Updated Task", existingTask.getTitle());
        assertEquals("Updated Description", existingTask.getDescription());
        assertTrue(existingTask.isStatus());
    }

    @Test
    void update_NonExistingTask_ThrowsNoSuchElementException() {
        // Arrange
        TaskDto updateDto = new TaskDto();
        updateDto.setTitle("Updated Task");
        updateDto.setDescription("Updated Description");
        updateDto.setStatus(true);

        when(repository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            taskService.update(999, updateDto);
        });
    }

    @Test
    void delete_ExistingTask_DeletesSuccessfully() {
        // Act
        taskService.delete(1);

        // Assert
        verify(repository).deleteById(1);
    }
}
