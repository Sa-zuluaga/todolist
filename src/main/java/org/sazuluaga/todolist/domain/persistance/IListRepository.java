package org.sazuluaga.todolist.domain.persistance;

import org.sazuluaga.todolist.domain.model.ToDoList;

public interface IListRepository {
    ToDoList save(ToDoList toDoList);

    ToDoList create(ToDoList toDoList);
}
