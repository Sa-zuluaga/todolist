package org.sazuluaga.todolist.infrastructure;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.domain.persistance.IListRepository;
import org.sazuluaga.todolist.infrastructure.controller.ListController;
import org.sazuluaga.todolist.infrastructure.model.ToDoListInfra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ListAcceptanceTest {

    @MockBean
    IListRepository repository;
    @LocalServerPort
    private int port;
    private ToDoList toDoListOut;

    @InjectMocks
    private ListController controller;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        toDoListOut = ToDoList.builder()
                .listId(1L)
                .name("lavar")
                .description("lavar la ropa en lavadora")
                .email("sazukyaga@gmail.com").build();

        openMocks(this);
        webAppContextSetup(context);
        RestAssuredMockMvc.standaloneSetup(controller);
    }

    @Test
    void shouldCreateAListSuccesfully() {
        ToDoList toDoList = ToDoList.builder()
                .name("lavar")
                .description("lavar la ropa en lavadora")
                .email("sazukyaga@gmail.com")
                .build();
        when(repository.create(any(ToDoList.class))).thenReturn(toDoListOut);
        given().contentType(ContentType.JSON)
                .body(toDoList)
                .when()
                .post(String.format("http://localhost:%s/lists", port))
                .then()
                .statusCode(201)
                .body(containsString("lavar"))
                .body(containsString("lavar la ropa en lavadora"))
                .body(containsString("sazukyaga@gmail.com"))
                .body(containsString("1"));
        Assertions.assertNull(toDoList.getListId());
    }

    @Test
    void shouldNotCreateAListWithoutNameAndReturnStatusCode400() {
        ToDoListInfra toDoListInfra = ToDoListInfra.builder()
                .name(null)
                .description("lavar la ropa en lavadora")
                .email("sazukyaga@gmail.com")
                .build();
        given().contentType(ContentType.JSON)
                .body(toDoListInfra)
                .when()
                .post(String.format("http://localhost:%s/lists", port))
                .then()
                .statusCode(400)
                .body(containsString("Name not valid"));
    }

    @Test
    void shouldNotCreateAListWithoutEmailAndReturnStatusCode400() {
        ToDoListInfra toDoListInfra = ToDoListInfra.builder()
                .name("lavar")
                .description("lavar la ropa en lavadora")
                .email(null)
                .build();
        given().contentType(ContentType.JSON)
                .body(toDoListInfra)
                .when()
                .post(String.format("http://localhost:%s/lists", port))
                .then()
                .statusCode(400)
                .body(containsString("Email not valid"));
    }

    @Test
    void shouldFindAListSuccesfully() {
        when(repository.getListById(1L)).thenReturn(toDoListOut);
        given().contentType(ContentType.JSON)
                .when()
                .get(String.format("http://localhost:%s/lists/1", port))
                .then()
                .statusCode(200)
                .body(containsString("1"))
                .body(containsString("lavar"));
    }

    @Test
    void shouldNotFindAListAndReturnStatusCode400() {
        when(repository.getListById(2L)).thenReturn(toDoListOut);
        given().contentType(ContentType.JSON)
                .when()
                .get(String.format("http://localhost:%s/lists/1", port))
                .then()
                .statusCode(400)
                .body(containsString("Couldn't find the list"));
    }

    @Test
    void shouldUpdateAListSuccesfully() {
        when(repository.getListById(1L)).thenReturn(toDoListOut);
        when(repository.updateList(any(ToDoList.class))).thenReturn(toDoListOut);
        ToDoListInfra toDoListInfra = ToDoListInfra.builder()
                .name("lavar")
                .description("colgar")
                .build();
        given().contentType(ContentType.JSON)
                .body(toDoListInfra)
                .when()
                .put(String.format("http://localhost:%s/lists/1", port))
                .then()
                .statusCode(200)
                .body(containsString("colgar"));
    }

    @Test
    void shouldUpdateAListWithoutNameAndReturnStatusCode200() {
        when(repository.getListById(1L)).thenReturn(toDoListOut);
        when(repository.updateList(any(ToDoList.class))).thenReturn(toDoListOut);
        ToDoListInfra toDoListInfra = ToDoListInfra.builder()
                .name(null)
                .description("lavar la ropa en lavadora")
                .build();
        given().contentType(ContentType.JSON)
                .body(toDoListInfra)
                .when()
                .put(String.format("http://localhost:%s/lists/1", port))
                .then()
                .statusCode(200)
                .body(containsString("lavar"));
    }

    @Test
    void shouldUpdateAListWithoutEmailAndReturnStatusCode200() {
        when(repository.getListById(1L)).thenReturn(toDoListOut);
        when(repository.updateList(any(ToDoList.class))).thenReturn(toDoListOut);
        ToDoListInfra toDoListInfra = ToDoListInfra.builder()
                .name("lavar")
                .email(null)
                .build();
        given().contentType(ContentType.JSON)
                .body(toDoListInfra)
                .when()
                .put(String.format("http://localhost:%s/lists/1", port))
                .then()
                .statusCode(200)
                .body(containsString("sazukyaga@gmail.com"));
    }

    @Test
    void shouldUpdateAListWithoutDescriptionAndReturnStatusCode200() {
        when(repository.getListById(1L)).thenReturn(toDoListOut);
        when(repository.updateList(any(ToDoList.class))).thenReturn(toDoListOut);
        ToDoListInfra toDoListInfra = ToDoListInfra.builder()
                .name("lavar")
                .description(null)
                .build();
        given().contentType(ContentType.JSON)
                .body(toDoListInfra)
                .when()
                .put(String.format("http://localhost:%s/lists/1", port))
                .then()
                .statusCode(200)
                .body(containsString("lavar la ropa en lavadora"));
    }

    @Test
    void shouldDeleteAListSuccesful() {
        when(repository.getListById(1L)).thenReturn(toDoListOut);
        given().contentType(ContentType.JSON)
                .when()
                .delete(String.format("http://localhost:%s/lists/1", port))
                .then()
                .statusCode(200);
    }

    @Test
    void shouldNotDeleteAListAndReturnStatusCode400() {
        when(repository.getListById(2L)).thenReturn(toDoListOut);
        given().contentType(ContentType.JSON)
                .when()
                .delete(String.format("http://localhost:%s/lists/1", port))
                .then()
                .statusCode(400)
                .body(containsString("Couldn't find the list"));
    }
}
