package org.sazuluaga.todolist.infrastructure.persistance;

import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.domain.persistance.IListRepository;
import org.sazuluaga.todolist.infrastructure.mapper.ToDoListMapper;
import org.sazuluaga.todolist.infrastructure.model.ToDoListInfra;
import org.sazuluaga.todolist.infrastructure.persistance.jpa.ListJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ListRepository implements IListRepository {
    @Autowired
    private ListJpa jpa;

    @Override
    public ToDoList save(ToDoList toDoList) {
        return null;
    }

    @Override
    public ToDoList create(ToDoList toDoList) {
        ToDoListInfra toDoListInfra = ToDoListMapper.toToDoListInfra(toDoList);
        toDoListInfra = jpa.save(toDoListInfra);
        return ToDoListMapper.toToDoList(toDoListInfra);
    }
}
