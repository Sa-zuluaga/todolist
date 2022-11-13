package org.sazuluaga.todolist.infrastructure.persistance;

import org.sazuluaga.todolist.domain.exception.BadRequest;
import org.sazuluaga.todolist.domain.model.ToDoItem;
import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.domain.persistance.UItemRepository;
import org.sazuluaga.todolist.infrastructure.mapper.ToDoItemMapper;
import org.sazuluaga.todolist.infrastructure.mapper.ToDoListMapper;
import org.sazuluaga.todolist.infrastructure.model.ToDoItemInfra;
import org.sazuluaga.todolist.infrastructure.model.ToDoListInfra;
import org.sazuluaga.todolist.infrastructure.persistance.jpa.ItemJpa;
import org.sazuluaga.todolist.infrastructure.persistance.jpa.ListJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository implements UItemRepository {

    public static final String USER_NOT_FOUND = "No se a encontrado el usuario";
    @Autowired
    private ItemJpa itemJpa;
    @Autowired
    private ListJpa listJpa;


    @Override
    public ToDoList create(ToDoItem toDoItem, Long listId) {
        ToDoItemInfra toDoItemInfra = ToDoItemMapper.toToDoItemInfra(toDoItem);
        ToDoListInfra toDoListInfra = listJpa.findById(listId)
                .orElseThrow(() -> new BadRequest(USER_NOT_FOUND));
        toDoListInfra.getItems().add(toDoItemInfra);
        listJpa.save(toDoListInfra);
        return ToDoListMapper.toToDoList(toDoListInfra);
    }
}
