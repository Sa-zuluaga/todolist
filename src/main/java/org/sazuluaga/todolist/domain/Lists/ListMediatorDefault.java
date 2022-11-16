package org.sazuluaga.todolist.domain.Lists;

import org.sazuluaga.todolist.domain.common.ArgumentValidator;
import org.sazuluaga.todolist.domain.exception.BadRequest;
import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.domain.persistance.IListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListMediatorDefault implements ListMediator {
    public static final String LIST_NOT_FOUND = "Couldn't find the list";
    private static final String NAME_NOT_VALID = "Name not valid";
    private static final String EMAIL_NOT_VALID = "Email not valid";
    @Autowired
    private final IListRepository IListRepository;

    public ListMediatorDefault(IListRepository IListRepository) {
        this.IListRepository = IListRepository;
    }

    public ToDoList create(ToDoList toDoList) {
        if (!ArgumentValidator.stringIsValid(toDoList.getName())) {
            throw new BadRequest(NAME_NOT_VALID);
        }
        if (!ArgumentValidator.stringIsValid(toDoList.getEmail())) {
            throw new BadRequest(EMAIL_NOT_VALID);
        }
        return IListRepository.create(toDoList);
    }

    public ToDoList getListById(Long listId) {
        ToDoList result = IListRepository.getListById(listId);
        if (result == null) {
            throw new BadRequest(LIST_NOT_FOUND);
        }
        return IListRepository.getListById(listId);
    }

    public ToDoList updateList(ToDoList toDoList, Long listId) {
        ToDoList listExist = getListById(listId);
        if (ArgumentValidator.stringIsValid(toDoList.getName())) {
            listExist.setName(toDoList.getName());
        }
        if (ArgumentValidator.stringIsValid(toDoList.getEmail())) {
            listExist.setEmail(toDoList.getEmail());
        }
        if (ArgumentValidator.stringIsValid(toDoList.getDescription())) {
            listExist.setDescription(toDoList.getDescription());
        }
        return IListRepository.updateList(listExist);
    }

    public void deleteById(Long listId) {
        ToDoList result = IListRepository.getListById(listId);
        if (result == null) {
            throw new BadRequest(LIST_NOT_FOUND);
        }
        IListRepository.deleteById(listId);
    }
}
