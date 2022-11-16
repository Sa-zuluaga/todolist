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
    public static final String LIST_NOT_FOUND = "No se ha encontrado la lista";
    @Autowired
    private ListJpa listJpa;

    @Override
    public ToDoList create(ToDoList toDoList) {
        ToDoListInfra toDoListInfra = ToDoListMapper.toToDoListInfra(toDoList);
        toDoListInfra = listJpa.save(toDoListInfra);
        return ToDoListMapper.toToDoList(toDoListInfra);
    }

    @Override
    public ToDoList getListById(Long listId) {
        var toDoListInfra = listJpa.findById(listId);
        if (toDoListInfra.isEmpty()) {
            return null;
        }
        return ToDoListMapper.toToDoList(toDoListInfra.get());
    }

    @Override
    public ToDoList updateList(ToDoList toDoList) {
        ToDoListInfra toDoListInfra = ToDoListMapper.toToDoListInfra(toDoList);
        toDoListInfra = listJpa.save(toDoListInfra);
        return ToDoListMapper.toToDoList(toDoListInfra);
    }

    @Override
    public void deleteById(Long listId) {
        listJpa.deleteById(listId);
    }

}
