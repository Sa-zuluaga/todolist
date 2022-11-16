package org.sazuluaga.todolist.infrastructure.persistance.jpa;

import org.sazuluaga.todolist.infrastructure.model.ToDoItemInfra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemJpa extends JpaRepository<ToDoItemInfra, Long> {
    List<ToDoItemInfra> findByListListId(Long listId);
}
