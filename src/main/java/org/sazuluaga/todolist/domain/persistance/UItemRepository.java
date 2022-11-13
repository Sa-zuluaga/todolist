package org.sazuluaga.todolist.domain.persistance;

import org.sazuluaga.todolist.domain.model.ToDoItem;
import org.sazuluaga.todolist.domain.model.ToDoList;

public interface UItemRepository {
    ToDoList create(ToDoItem toDoItem, Long listId);
}
