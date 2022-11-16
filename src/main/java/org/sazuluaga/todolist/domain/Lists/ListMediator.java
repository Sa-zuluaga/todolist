package org.sazuluaga.todolist.domain.Lists;

import org.sazuluaga.todolist.domain.model.ToDoList;

public interface ListMediator {
    ToDoList create(ToDoList toDoList);

    ToDoList getListById(Long listId);

    ToDoList updateList(ToDoList toDoList, Long listId);

    void deleteById(Long listId);
}
