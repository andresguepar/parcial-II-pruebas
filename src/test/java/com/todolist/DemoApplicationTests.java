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

		assertThat(tasks, hasSize(2));

		assertThat(tasks, containsInAnyOrder(
				allOf(hasEntry("title", "Leer Libro")),
				allOf(hasEntry("title", "Hacer Tarea"))
		));
	}

/*	@Test
	public void testCreateTasks() {
		List<Map<String, Object>> tasks = post("").as(new TypeRef<List<Map<String, Object>>>() {});

		assertThat(tasks, containsInAnyOrder(
				allOf(hasEntry("title", "Cocinar"))
		));
	}

	@Test
	public void testUpdateTasks() {
		List<Map<String, Object>> tasks = put("").as(new TypeRef<List<Map<String, Object>>>() {});

		assertThat(tasks, hasSize(7));

		assertThat(tasks, containsInAnyOrder(
				allOf(hasEntry("title", "Leer Libro")),
				allOf(hasEntry("title", "Hacer Tarea"))
		));
	}*/

}
