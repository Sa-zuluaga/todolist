package org.sazuluaga.todolist.domain.Item;

import org.sazuluaga.todolist.domain.model.ToDoItem;
import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.domain.persistance.UItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMediatorDefault implements ItemMediator{
    @Autowired
    private final UItemRepository UItemRepository;

    public ItemMediatorDefault(UItemRepository UItemRepository) { this.UItemRepository = UItemRepository; }

    public ToDoList create(ToDoItem toDoItem, Long listId) {
        return UItemRepository.create(toDoItem, listId);
    }
}
