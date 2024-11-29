/*
package com.todolist;

import com.todolist.mapping.dtos.TaskDto;
import com.todolist.services.impl.TaskServiceImpl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ApplicationTests {

	@LocalServerPort
	private int port;

	@MockBean
	private TaskServiceImpl taskService;

	private static final String BASE_URI = "/api/tasks";
	private TaskDto taskDto;

	@BeforeEach
	public void setup() {
		RestAssured.port = port;
		RestAssured.baseURI = "http://localhost";
		RestAssured.basePath = BASE_URI;

		taskDto = TaskDto.builder()
				.id(1)
				.title("Leer Libro")
				.description("Un libro interesante sobre programación.")
				.status(false)
				.build();
	}

	@Test
	void testCreateTask() {
		when(taskService.save(ArgumentMatchers.any(TaskDto.class))).thenReturn(taskDto);

		given()
				.contentType(ContentType.JSON)
				.body(taskDto)
				.when()
				.post()
				.then()
				.statusCode(201)
				.body("id", is(taskDto.getId()))
				.body("title", is(taskDto.getTitle()))
				.body("description", is(taskDto.getDescription()))
				.body("status", is(taskDto.isStatus()));
	}

	@Test
	void testTaskById() {
		when(taskService.byId(1)).thenReturn(taskDto);

		given()
				.contentType(ContentType.JSON)
				.when()
				.get("/{id}", 1)
				.then()
				.statusCode(200)
				.body("id", is(taskDto.getId()))
				.body("title", is(taskDto.getTitle()))
				.body("description", is(taskDto.getDescription()))
				.body("status", is(taskDto.isStatus()));
	}

	@Test
	void testUpdateTask() {
		TaskDto updatedTask = TaskDto.builder()
				.id(1)
				.title("Tareas Actualizadas")
				.description("Descripción actualizada.")
				.status(true)
				.build();

		when(taskService.byId(1)).thenReturn(taskDto);
		when(taskService.save(ArgumentMatchers.any(TaskDto.class))).thenReturn(updatedTask);

		given()
				.contentType(ContentType.JSON)
				.body(updatedTask)
				.when()
				.put("/{id}", 1)
				.then()
				.statusCode(200)
				.body("id", is(updatedTask.getId()))
				.body("title", is(updatedTask.getTitle()))
				.body("description", is(updatedTask.getDescription()))
				.body("status", is(updatedTask.isStatus()));
	}

	@Test
	void testDeleteTask() {
		doNothing().when(taskService).delete(1);

		given()
				.contentType(ContentType.JSON)
				.when()
				.delete("/{id}", 1)
				.then()
				.statusCode(204);

		verify(taskService, times(1)).delete(1);
	}

}
*/
