package org.sazuluaga.todolist.domain.persistance;

import org.sazuluaga.todolist.domain.model.ToDoItem;

import java.util.List;

public interface UItemRepository {
    ToDoItem create(ToDoItem toDoItem);

    List<ToDoItem> getByList(Long listId);

    ToDoItem getItemById(Long itemId);

    void deleteItemById(Long listId);

    ToDoItem updateItem(ToDoItem toDoItem);
}
