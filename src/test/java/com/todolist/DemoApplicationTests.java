package com.todolist;

import io.restassured.common.mapper.TypeRef;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.AllOf.allOf;

@SpringBootTest
class DemoApplicationTests {

	@BeforeAll
	public static void setup() {
		baseURI = "http://localhost:8080/api/tasks";
	}

	@Test
	public void testGetTasks() {
		List<Map<String, Object>> tasks = get("").as(new TypeRef<List<Map<String, Object>>>() {});

		assertThat(tasks, hasSize(7));

		assertThat(tasks, containsInAnyOrder(
				allOf(hasEntry("title", "Leer Libro")),
				allOf(hasEntry("title", "Hacer Tarea")),
				allOf(hasEntry("title", "Something")),
				allOf(hasEntry("title", "Tas")),
				allOf(hasEntry("title", "Estudiar ")),
				allOf(hasEntry("title", "Hola")),
				allOf(hasEntry("title", "Si"))
		));
	}

	@Test
	public void testCreateTask() {
		Map<String, Object> newTask = Map.of("title", "Nueva Tarea");
        Map<String, Object> createdTask = post("")
                .as(new TypeRef<Map<String, Object>>() {})
                .body(newTask);

        assertThat(createdTask, allOf(
                hasEntry("title", "Nueva Tarea")
        ));
	}

}
