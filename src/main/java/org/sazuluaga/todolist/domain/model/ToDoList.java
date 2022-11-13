package org.sazuluaga.todolist.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sazuluaga.todolist.infrastructure.model.ToDoItemInfra;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToDoList {
    private Long listId;
    private String name;
    private String description;
    private String email;
    List<ToDoItemInfra> items = new ArrayList<>();
}