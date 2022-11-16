package org.sazuluaga.todolist.infrastructure.mapper;

import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.infrastructure.model.ToDoListInfra;

public class ToDoListMapper {

    private ToDoListMapper() {
    }

    public static ToDoList toToDoList(ToDoListInfra toDoListInfra) {
        if (toDoListInfra == null) {
            return null;
        }
        ToDoList toDoList = ToDoList.builder()
                .listId(toDoListInfra.getListId())
                .name(toDoListInfra.getName())
                .description(toDoListInfra.getDescription())
                .email(toDoListInfra.getEmail())
                .build();
        return toDoList;
    }

    public static ToDoListInfra toToDoListInfra(ToDoList toDoList) {
        if (toDoList == null) {
            return null;
        }
        return ToDoListInfra.builder()
                .listId(toDoList.getListId())
                .name(toDoList.getName())
                .description(toDoList.getDescription())
                .email(toDoList.getEmail())
                .build();
    }
}