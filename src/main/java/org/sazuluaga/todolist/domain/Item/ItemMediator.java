package org.sazuluaga.todolist.domain.Item;

import org.sazuluaga.todolist.domain.model.ToDoItem;
import org.sazuluaga.todolist.domain.model.ToDoList;

public interface ItemMediator {

    ToDoList create(ToDoItem toDoItem, Long listId);
}
