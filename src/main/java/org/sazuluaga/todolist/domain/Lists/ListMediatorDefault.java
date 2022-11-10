package org.sazuluaga.todolist.domain.Lists;

import org.sazuluaga.todolist.domain.model.ToDoList;
import org.sazuluaga.todolist.domain.persistance.IListRepository;


public class ListMediatorDefault implements ListMediator {
    private final IListRepository IListRepository;

    public ListMediatorDefault(IListRepository IListRepository) {
        this.IListRepository = IListRepository;
    }

    public ToDoList create(ToDoList toDoList) {
        return IListRepository.save(toDoList);
    }
}
