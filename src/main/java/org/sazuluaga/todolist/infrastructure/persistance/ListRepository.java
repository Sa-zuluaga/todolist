package org.sazuluaga.todolist.infrastructure.persistance;

import org.sazuluaga.todolist.domain.exception.BadRequest;
import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.domain.persistance.IListRepository;
import org.sazuluaga.todolist.infrastructure.mapper.ToDoListMapper;
import org.sazuluaga.todolist.infrastructure.model.ToDoListInfra;
import org.sazuluaga.todolist.infrastructure.persistance.jpa.ListJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ListRepository implements IListRepository {
    public static final String USER_NOT_FOUND = "No se a encontrado el usuario";
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
        ToDoListInfra toDoListInfra = listJpa.findById(listId)
                .orElseThrow(() -> new BadRequest(USER_NOT_FOUND));
        return ToDoListMapper.toToDoList(toDoListInfra);
    }
    @Override
    public ToDoList updateList(ToDoList toDoList, Long listId) {
        ToDoListInfra toDoListInfra = ToDoListMapper.toToDoListInfra(toDoList);
        toDoListInfra.setListId(listId);
        toDoListInfra = listJpa.save(toDoListInfra);
        return ToDoListMapper.toToDoList(toDoListInfra);
    }
    @Override
    public boolean deleteById(Long listId){
        listJpa.deleteById(listId);
        return true;
    }

}
