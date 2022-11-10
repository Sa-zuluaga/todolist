package org.sazuluaga.todolist.infrastructure.mapper;

import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.infrastructure.model.ToDoListInfra;

public class ToDoListMapper {

    private ToDoListMapper() {
    }

    public static ToDoList toToDoList(ToDoListInfra toDoListInfra) {
        ToDoList toDoList = new ToDoList();
        toDoList.setName(toDoListInfra.getName());
        toDoList.setDescription(toDoListInfra.getDescription());
        toDoList.setUser(toDoListInfra.getUser());
        return toDoList;
    }

    public static ToDoListInfra toToDoListInfra(ToDoList toDoList) {
        return ToDoListInfra.builder()
                .name(toDoList.getName())
                .description(toDoList.getDescription())
                .user(toDoList.getUser())
                .build();
    }
}