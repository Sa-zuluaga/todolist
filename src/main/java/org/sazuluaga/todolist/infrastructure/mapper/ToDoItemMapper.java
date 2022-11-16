package org.sazuluaga.todolist.infrastructure.mapper;

import org.sazuluaga.todolist.domain.model.ToDoItem;
import org.sazuluaga.todolist.infrastructure.model.ToDoItemInfra;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToDoItemMapper {

    private ToDoItemMapper() {
    }

    public static List<ToDoItem> toToDoItem(List<ToDoItemInfra> items) {
        if (items == null) {
            return new ArrayList<>();
        }
        return items.stream().map(i -> toToDoItem(i)).collect(Collectors.toList());
    }

    public static List<ToDoItemInfra> toToDoItemInfra(List<ToDoItem> items) {
        if (items == null) {
            return new ArrayList<>();
        }
        return items.stream().map(i -> toToDoItemInfra(i)).collect(Collectors.toList());
    }

    public static ToDoItem toToDoItem(ToDoItemInfra toDoItemInfra) {
        if (toDoItemInfra == null) {
            return null;
        }
        ToDoItem toDoItem = ToDoItem.builder()
                .itemId(toDoItemInfra.getItemId())
                .description(toDoItemInfra.getDescription())
                .done(toDoItemInfra.isDone())
                .list(ToDoListMapper.toToDoList(toDoItemInfra.getList()))
                .build();
        return toDoItem;
    }

    public static ToDoItemInfra toToDoItemInfra(ToDoItem toDoItem) {
        if (toDoItem == null) {
            return null;
        }
        ToDoItemInfra toDoItemInfra = ToDoItemInfra.builder()
                .itemId(toDoItem.getItemId())
                .description(toDoItem.getDescription())
                .done(toDoItem.getDone())
                .list(ToDoListMapper.toToDoListInfra(toDoItem.getList()))
                .build();
        return toDoItemInfra;
    }
}
