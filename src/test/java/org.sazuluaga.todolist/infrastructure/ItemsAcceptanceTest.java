package org.sazuluaga.todolist.infrastructure;


import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.sazuluaga.todolist.domain.model.ToDoItem;
import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.domain.persistance.IListRepository;
import org.sazuluaga.todolist.domain.persistance.UItemRepository;
import org.sazuluaga.todolist.infrastructure.controller.ItemController;
import org.sazuluaga.todolist.infrastructure.model.ToDoItemInfra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemsAcceptanceTest {

    @MockBean
    UItemRepository itemRepository;
    @MockBean
    IListRepository listRepository;
    @LocalServerPort
    private int port;
    private ToDoItem toDoItemOut;
    private ToDoList toDoListOut;
    @InjectMocks
    private ItemController controller;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        toDoListOut = ToDoList.builder()
                .listId(1L)
                .name("lavar")
                .description("lavar la ropa en lavadora")
                .email("sazukyaga@gmail.com").build();
        toDoItemOut = ToDoItem.builder()
                .itemId(1L)
                .description("lavar ropita")
                .done(false)
                .list(toDoListOut)
                .build();

        openMocks(this);
        webAppContextSetup(context);
        RestAssuredMockMvc.standaloneSetup(controller);
    }

    @Test
    void shouldCreateAItemSuccesfully() {
        when(itemRepository.create(Mockito.any(ToDoItem.class))).thenReturn(toDoItemOut);
        when(listRepository.getListById(anyLong())).thenReturn(toDoListOut);
        given().contentType(ContentType.JSON)
                .body(toDoItemOut)
                .when()
                .post(String.format("http://localhost:%s/lists/1/items", port))
                .then()
                .statusCode(201)
                .body(containsString("lavar"));
    }

    @Test
    void shouldNotCreateAItemWithoutDescriptionAndReturnStatusCode400() {
        ToDoItemInfra toDoItemInfra = ToDoItemInfra.builder()
                .description(null)
                .done(false)
                .build();
        when(listRepository.getListById(anyLong())).thenReturn(toDoListOut);
        given().contentType(ContentType.JSON)
                .body(toDoItemInfra)
                .when()
                .post(String.format("http://localhost:%s/lists/1/items", port))
                .then()
                .statusCode(400)
                .body(containsString("Description not valid"));
    }

    @Test
    void shouldFindAItemSuccesfully() {
        when(itemRepository.getItemById(anyLong())).thenReturn(toDoItemOut);
        when(listRepository.getListById(anyLong())).thenReturn(toDoListOut);
        given().contentType(ContentType.JSON)
                .when()
                .get(String.format("http://localhost:%s/lists/1/items/1", port))
                .then()
                .statusCode(200)
                .body(containsString("1"))
                .body(containsString("lavar"));
    }

    @Test
    void shouldNotFindAItemAndReturnStatusCode400() {
        when(itemRepository.getItemById(2L)).thenReturn(toDoItemOut);
        when(listRepository.getListById(anyLong())).thenReturn(toDoListOut);
        given().contentType(ContentType.JSON)
                .when()
                .get(String.format("http://localhost:%s/lists/1/items/1", port))
                .then()
                .statusCode(400)
                .body(containsString("Couldn't find the item"));
    }

    @Test
    void shouldUpdateAItemSuccesfully() {
    when(itemRepository.getItemById(anyLong())).thenReturn(toDoItemOut);
    when(itemRepository.updateItem(any(ToDoItem.class))).thenReturn(toDoItemOut);
    ToDoItemInfra toDoItemInfra = ToDoItemInfra.builder()
            .description("lavar el piso")
            .done(false)
            .build();
    given().contentType(ContentType.JSON)
            .body(toDoItemInfra)
            .when()
            .put(String.format("http://localhost:%s/lists/1/items/1", port))
            .then()
            .statusCode(200)
            .body(containsString("1"))
            .body(containsString("lavar el piso"));
    }
    @Test
    void shouldUpdateAItemWithoutDescriptionAndReturnStatusCode200() {

    when(itemRepository.getItemById(anyLong())).thenReturn(toDoItemOut);
    when(itemRepository.updateItem(any(ToDoItem.class))).thenReturn(toDoItemOut);
    ToDoItemInfra toDoItemInfra = ToDoItemInfra.builder()
            .description(null)
            .done(false)
            .build();
    given().contentType(ContentType.JSON)
            .body(toDoItemInfra)
            .when()
            .put(String.format("http://localhost:%s/lists/1/items/1", port))
            .then()
            .statusCode(200)
            .body(containsString("1"))
            .body(containsString("lavar ropita"));
    }


    @Test
    void shouldUpdateAItemWithoutDoneAndReturnStatusCode200() {
        when(itemRepository.getItemById(anyLong())).thenReturn(toDoItemOut);
        when(itemRepository.updateItem(any(ToDoItem.class))).thenReturn(toDoItemOut);
        ToDoItemInfra toDoItemInfra = ToDoItemInfra.builder()
                .description("limpiar basura")
                .build();
        given().contentType(ContentType.JSON)
                .body(toDoItemInfra)
                .when()
                .put(String.format("http://localhost:%s/lists/1/items/1", port))
                .then()
                .statusCode(200)
                .body(containsString("1"))
                .body(containsString("limpiar basura"));
    }
    @Test
    void shouldDeleteAItemSuccesful() {
    when(itemRepository.getItemById(anyLong())).thenReturn(toDoItemOut);
    given().contentType(ContentType.JSON)
            .when()
            .delete(String.format("http://localhost:%s/lists/1/items/1", port))
            .then()
            .statusCode(200);
    }

    @Test
    void shouldNotDeleteAItemAndReturnStatusCode400() {
    when(itemRepository.getItemById(2L)).thenReturn(toDoItemOut);
    given().contentType(ContentType.JSON)
            .when()
            .delete(String.format("http://localhost:%s/lists/1/items/1", port))
            .then()
            .statusCode(400)
            .body(containsString("Couldn't find the item"));
    }
}

