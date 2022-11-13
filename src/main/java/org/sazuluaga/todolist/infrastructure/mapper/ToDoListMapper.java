package org.sazuluaga.todolist.infrastructure.mapper;

import org.sazuluaga.todolist.domain.model.ToDoItem;
import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.infrastructure.model.ToDoItemInfra;
import org.sazuluaga.todolist.infrastructure.model.ToDoListInfra;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class ToDoListMapper {

    private ToDoListMapper() {
    }

    public static ToDoList toToDoList(ToDoListInfra toDoListInfra) {
        ToDoList toDoList =  ToDoList.builder()
                .listId(toDoListInfra.getListId())
                .name(toDoListInfra.getName())
                .description(toDoListInfra.getDescription())
                .email(toDoListInfra.getEmail())
                .build();
        return toDoList;
    }

    public static ToDoListInfra toToDoListInfra(ToDoList toDoList) {

        return ToDoListInfra.builder()
                .listId(toDoList.getListId())
                .name(toDoList.getName())
                .description(toDoList.getDescription())
                .email(toDoList.getEmail())
                .build();
    }
}