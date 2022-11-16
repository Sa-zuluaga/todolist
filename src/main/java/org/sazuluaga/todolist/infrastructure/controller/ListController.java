package org.sazuluaga.todolist.infrastructure.controller;

import org.sazuluaga.todolist.domain.Lists.ListMediator;
import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.infrastructure.mapper.ToDoListMapper;
import org.sazuluaga.todolist.infrastructure.model.ToDoListInfra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListController {

    @Autowired
    private final ListMediator listMediator;

    public ListController(ListMediator listMediator) {
        this.listMediator = listMediator;
    }

    @PostMapping(path = "/lists")
    public ResponseEntity<ToDoList> create(@RequestBody ToDoList toDoList) {
        ToDoList toDoListCreated = listMediator.create(toDoList);
        return new ResponseEntity<>(toDoListCreated, HttpStatus.CREATED);
    }

    @GetMapping(path = "/lists/{listId}")
    public ResponseEntity<ToDoListInfra> getById(@PathVariable("listId") Long listId) {
        ToDoList toDoListFinded = listMediator.getListById(listId);
        ToDoListInfra toDoListInfraFinded = ToDoListMapper.toToDoListInfra(toDoListFinded);
        return new ResponseEntity<>(toDoListInfraFinded, HttpStatus.OK);
    }

    @PutMapping(path = "/lists/{listId}")
    public ResponseEntity<ToDoListInfra> updateList(@PathVariable("listId") Long listId, @RequestBody ToDoListInfra toDoListInfra) {
        ToDoList toDoListToUpdate = ToDoListMapper.toToDoList(toDoListInfra);
        ToDoList toDoListUpdated = listMediator.updateList(toDoListToUpdate, listId);
        ToDoListInfra toDoListInfraUpdated = ToDoListMapper.toToDoListInfra(toDoListUpdated);
        return new ResponseEntity<>(toDoListInfraUpdated, HttpStatus.OK);
    }

    @DeleteMapping(path = "/lists/{listId}")
    public void deleteById(@PathVariable("listId") Long listId) {
        listMediator.deleteById(listId);
    }
}
