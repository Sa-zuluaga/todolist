package org.sazuluaga.todolist.infrastructure.persistance;

import org.sazuluaga.todolist.domain.model.ToDoItem;
import org.sazuluaga.todolist.domain.persistance.UItemRepository;
import org.sazuluaga.todolist.infrastructure.mapper.ToDoItemMapper;
import org.sazuluaga.todolist.infrastructure.model.ToDoItemInfra;
import org.sazuluaga.todolist.infrastructure.persistance.jpa.ItemJpa;
import org.sazuluaga.todolist.infrastructure.persistance.jpa.ListJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository implements UItemRepository {

    public static final String LIST_NOT_FOUND = "No se ha encontrado la lista";
    public static final String ITEM_NOT_FOUND = "No se ha encontrado el item";
    @Autowired
    private ItemJpa itemJpa;
    @Autowired
    private ListJpa listJpa;


    @Override
    public ToDoItem create(ToDoItem toDoItem) {
        ToDoItemInfra toDoItemInfra = ToDoItemMapper.toToDoItemInfra(toDoItem);
        toDoItemInfra = itemJpa.save(toDoItemInfra);
        return ToDoItemMapper.toToDoItem(toDoItemInfra);
    }

    @Override
    public List<ToDoItem> getByList(Long listId) {
        List<ToDoItemInfra> itemInfras = itemJpa.findByListListId(listId);
        return ToDoItemMapper.toToDoItem(itemInfras);
    }

    @Override
    public ToDoItem getItemById(Long itemId) {
        var itemInfra = itemJpa.findById(itemId);
        if (itemInfra.isEmpty()) {
            return null;
        }
        return ToDoItemMapper.toToDoItem(itemInfra.get());
    }

    @Override
    public ToDoItem updateItem(ToDoItem toDoItem) {
        ToDoItemInfra toDoItemInfra = ToDoItemMapper.toToDoItemInfra(toDoItem);
        toDoItemInfra = itemJpa.save(toDoItemInfra);
        return ToDoItemMapper.toToDoItem(toDoItemInfra);
    }

    @Override
    public void deleteItemById(Long itemId) {
        itemJpa.deleteById(itemId);
    }
}
