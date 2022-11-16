package org.sazuluaga.todolist.infrastructure.controller;


import org.sazuluaga.todolist.domain.Item.ItemMediator;
import org.sazuluaga.todolist.domain.model.ToDoItem;
import org.sazuluaga.todolist.infrastructure.mapper.ToDoItemMapper;
import org.sazuluaga.todolist.infrastructure.model.ToDoItemInfra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/lists/{listId}")

public class ItemController {

    @Autowired
    private final ItemMediator itemMediator;

    public ItemController(ItemMediator itemMediator) {
        this.itemMediator = itemMediator;
    }

    @PostMapping("/items")
    public ResponseEntity<ToDoItem> create(@RequestBody ToDoItem toDoItem, @PathVariable("listId") Long listId) {
        ToDoItem itemCreated = itemMediator.create(toDoItem, listId);
        return new ResponseEntity<>(itemCreated, HttpStatus.CREATED);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ToDoItem>> getByList(@PathVariable("listId") Long listId) {
        List<ToDoItem> items = itemMediator.getByList(listId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<ToDoItemInfra> getItemById(@PathVariable("itemId") Long itemId) {
        ToDoItem toDoItemFinded = itemMediator.getItemById(itemId);
        ToDoItemInfra toDoItemInfraFinded = ToDoItemMapper.toToDoItemInfra(toDoItemFinded);
        return new ResponseEntity<>(toDoItemInfraFinded, HttpStatus.OK);
    }

    @PutMapping(path = "/items/{itemId}")
    public ResponseEntity<ToDoItemInfra> updateItem(@PathVariable("itemId") Long itemId, @PathVariable("listId") Long listId, @RequestBody ToDoItemInfra toDoItemInfra) {
        ToDoItem toDoItemToUpdate = ToDoItemMapper.toToDoItem(toDoItemInfra);
        ToDoItem toDoItemUpdated = itemMediator.updateItem(toDoItemToUpdate, itemId, listId);
        ToDoItemInfra toDoItemInfraUpdated = ToDoItemMapper.toToDoItemInfra(toDoItemUpdated);
        return new ResponseEntity<>(toDoItemInfraUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/items/{itemId}")
    public void deleteItemById(@PathVariable("itemId") Long itemId) {
        itemMediator.deleteItemById(itemId);
    }
}
