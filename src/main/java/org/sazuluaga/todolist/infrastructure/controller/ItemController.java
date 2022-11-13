package org.sazuluaga.todolist.infrastructure.controller;


import org.sazuluaga.todolist.domain.Item.ItemMediator;
import org.sazuluaga.todolist.domain.model.ToDoItem;
import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.infrastructure.mapper.ToDoItemMapper;
import org.sazuluaga.todolist.infrastructure.mapper.ToDoListMapper;
import org.sazuluaga.todolist.infrastructure.model.ToDoItemInfra;
import org.sazuluaga.todolist.infrastructure.model.ToDoListInfra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/lists/{listId}")

public class ItemController {

    @Autowired
    private final ItemMediator itemMediator;

    public ItemController(ItemMediator itemMediator) { this.itemMediator = itemMediator; }

    @PostMapping(path = "/items")
    public ResponseEntity<ToDoListInfra> create(@RequestBody ToDoItemInfra toDoItemInfra, @PathVariable("listId") Long listId) {
        ToDoItem itemToCreate = ToDoItemMapper.toToDoItem(toDoItemInfra);
        ToDoList itemCreated = itemMediator.create(itemToCreate, listId);
        ToDoListInfra itemInfraCreated = ToDoListMapper.toToDoListInfra(itemCreated);
        return new ResponseEntity<>(itemInfraCreated, HttpStatus.CREATED);
    }
}
