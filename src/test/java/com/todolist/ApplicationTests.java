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
	public  void setup() {
		RestAssured.port = port;
		RestAssured.baseURI = "http://localhost";
		RestAssured.basePath = BASE_URI;

		taskDto = new TaskDto();
		taskDto.setId(1);
		taskDto.setTitle("Leer Libro");
	}

	@Test
	public void testGetTasks() {
		List<TaskDto> tasksList = Arrays.asList(taskDto);
		when(taskService.list()).thenReturn(tasksList);

		given()
				.contentType(ContentType.JSON)
				.when()
				.get()
				.then()
				.statusCode(200)
				.body("size()", is(1))
				.body("[0].id", is(taskDto.getId()))
				.body("[0].title", is(taskDto.getTitle()));
	}

	@Test
	void testCreateTask() {
		when((taskService.save(ArgumentMatchers.any(TaskDto.class)))).thenReturn(taskDto);

		given()
				.contentType(ContentType.JSON)
				.body(taskDto)
				.when()
				.post()
				.then()
				.statusCode(201)
				.body("id", is(taskDto.getId()))
				.body("title", is(taskDto.getTitle()));
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
				.body("title", is(taskDto.getTitle()));
	}

	@Test
	void testUpdateTask() {
		TaskDto updateTask = new TaskDto();
		updateTask.setId(1);
		updateTask.setTitle("Tareas");

		when(taskService.byId(1)).thenReturn(taskDto);
		when(taskService.save(ArgumentMatchers.any(TaskDto.class))).thenReturn(updateTask);

		given()
				.contentType(ContentType.JSON)
				.body(updateTask)
				.when()
				.put("/{id}", 1)
				.then()
				.statusCode(200)
				.body("id", is(updateTask.getId()))
				.body("title", is(updateTask.getTitle()));


	}

	@Test
	void testDeleteTask() {
		doNothing().when(taskService).delete(1);

		given()
				.contentType(ContentType.JSON)
				.when()
				.delete("/{id}",1)
				.then()
				.statusCode(204);
		verify(taskService).delete(1);

	}

}
