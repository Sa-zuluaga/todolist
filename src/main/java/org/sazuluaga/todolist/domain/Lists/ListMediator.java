package org.sazuluaga.todolist.domain.Lists;

import org.sazuluaga.todolist.domain.model.ToDoList;

public interface ListMediator {
    ToDoList create(ToDoList toDoList);
}
