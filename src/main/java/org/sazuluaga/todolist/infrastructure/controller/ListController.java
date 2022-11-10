package org.sazuluaga.todolist.infrastructure.controller;

import org.sazuluaga.todolist.domain.Lists.ListMediator;
import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.infrastructure.mapper.ToDoListMapper;
import org.sazuluaga.todolist.infrastructure.model.ToDoListInfra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListController {

    @Autowired
    private final ListMediator listMediator;

    public ListController(ListMediator listMediator) {
        this.listMediator = listMediator;
    }

    @PostMapping(path = "/lists")
    public ResponseEntity<ToDoListInfra> create(@RequestBody ToDoListInfra toDoListInfra) {
        ToDoList toDoListToCreate = ToDoListMapper.toToDoList(toDoListInfra);
        ToDoList toDoListCreated = listMediator.create(toDoListToCreate);
        ToDoListInfra toDoListInfraCreated = ToDoListMapper.toToDoListInfra(toDoListCreated);
        return new ResponseEntity<>(toDoListInfraCreated, HttpStatus.CREATED);
    }
}
