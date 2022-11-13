package org.sazuluaga.todolist.domain.Lists;

import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.domain.persistance.IListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListMediatorDefault implements ListMediator {
    @Autowired
    private final IListRepository IListRepository;

    public ListMediatorDefault(IListRepository IListRepository) {
        this.IListRepository = IListRepository;
    }

    public ToDoList create(ToDoList toDoList) {
        return IListRepository.create(toDoList);
    }
    public ToDoList getListById(Long listId) {
        return  IListRepository.getListById(listId);
    }
    public ToDoList updateList(ToDoList toDoList,Long listId) {
        return IListRepository.updateList(toDoList, listId);
    }
    public boolean deleteById(Long listId) {
        IListRepository.deleteById(listId);
        return true;
    }
}
