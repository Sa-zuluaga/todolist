package org.sazuluaga.todolist.infrastructure.persistance.jpa;

import org.sazuluaga.todolist.infrastructure.model.ToDoListInfra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListJpa extends JpaRepository<ToDoListInfra, Long> {
}
