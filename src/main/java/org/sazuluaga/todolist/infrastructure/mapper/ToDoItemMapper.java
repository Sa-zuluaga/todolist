package org.sazuluaga.todolist.infrastructure.mapper;

import org.sazuluaga.todolist.domain.model.ToDoItem;
import org.sazuluaga.todolist.infrastructure.model.ToDoItemInfra;

public class ToDoItemMapper {

    private ToDoItemMapper() {
    }
    public static ToDoItem toToDoItem(ToDoItemInfra toDoItemInfra) {
        ToDoItem toDoItem =  ToDoItem.builder()
                .itemId(toDoItemInfra.getItemId())
                .description(toDoItemInfra.getDescription())
                .done(toDoItemInfra.isDone())
                .build();
        return toDoItem;
    }

    public static ToDoItemInfra toToDoItemInfra(ToDoItem toDoItem) {
       ToDoItemInfra toDoItemInfra = ToDoItemInfra.builder()
               .itemId(toDoItem.getItemId())
               .description(toDoItem.getDescription())
               .done(toDoItem.isDone())
               .build();
       return toDoItemInfra;
    }
}
