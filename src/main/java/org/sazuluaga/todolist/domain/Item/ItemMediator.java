package org.sazuluaga.todolist.domain.Item;

import org.sazuluaga.todolist.domain.model.ToDoItem;

import java.util.List;

public interface ItemMediator {
    ToDoItem create(ToDoItem toDoItem, Long listId);

    List<ToDoItem> getByList(Long listId);

    ToDoItem getItemById(Long itemId);


    ToDoItem updateItem(ToDoItem toDoItem, Long itemId, Long listId);

    void deleteItemById(Long listId);

}
