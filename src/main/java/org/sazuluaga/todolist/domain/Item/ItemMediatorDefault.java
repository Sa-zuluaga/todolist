package org.sazuluaga.todolist.domain.Item;

import org.sazuluaga.todolist.domain.common.ArgumentValidator;
import org.sazuluaga.todolist.domain.exception.BadRequest;
import org.sazuluaga.todolist.domain.model.ToDoItem;
import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.domain.persistance.IListRepository;
import org.sazuluaga.todolist.domain.persistance.UItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemMediatorDefault implements ItemMediator {
    public static final String LIST_NOT_FOUND = "Couldn't find the list";
    public static final String ITEM_NOT_FOUND = "Couldn't find the item";
    private static final String DESCRIPTION_NOT_VALID = "Description not valid";
    @Autowired
    private final UItemRepository UItemRepository;
    private final IListRepository IListRepository;

    public ItemMediatorDefault(UItemRepository UItemRepository, org.sazuluaga.todolist.domain.persistance.IListRepository iListRepository) {
        this.UItemRepository = UItemRepository;
        IListRepository = iListRepository;
    }

    public ToDoItem create(ToDoItem toDoItem, Long listId) {
        ToDoList result = IListRepository.getListById(listId);
        if (result == null) {
            throw new BadRequest(LIST_NOT_FOUND);
        }
        if (!ArgumentValidator.stringIsValid(toDoItem.getDescription())) {
            throw new BadRequest(DESCRIPTION_NOT_VALID);
        }
        toDoItem.setList(result);
        return UItemRepository.create(toDoItem);
    }

    @Override
    public List<ToDoItem> getByList(Long listId) {
        return UItemRepository.getByList(listId);
    }

    @Override
    public ToDoItem getItemById(Long itemId) {
        ToDoItem result = UItemRepository.getItemById(itemId);
        if (result == null) {
            throw new BadRequest(ITEM_NOT_FOUND);
        }
        return UItemRepository.getItemById(itemId);

    }

    @Override
    public ToDoItem updateItem(ToDoItem toDoItem, Long itemId, Long listId) {
        ToDoItem itemExist = getItemById(itemId);
        if (ArgumentValidator.stringIsValid(toDoItem.getDescription())) {
            itemExist.setDescription(toDoItem.getDescription());
        }
        if (!itemExist.getList().getListId().equals(listId)) {
            throw new BadRequest("the item doesn't match with list");
        }
        if (toDoItem.getDone() != null) {
            itemExist.setDone(toDoItem.getDone());
        }
        return UItemRepository.updateItem(itemExist);
    }

    @Override
    public void deleteItemById(Long itemId) {
        ToDoItem result = UItemRepository.getItemById(itemId);
        if (result == null) {
            throw new BadRequest(ITEM_NOT_FOUND);
        }
        UItemRepository.deleteItemById(itemId);
    }

}
