package org.sazuluaga.todolist.domain.persistance;

import org.sazuluaga.todolist.domain.model.ToDoList;

public interface IListRepository {

    ToDoList create(ToDoList toDoList);

    ToDoList getListById(Long listId);

    ToDoList updateList(ToDoList toDoList);

    void deleteById(Long listId);
}
