package org.sazuluaga.todolist.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToDoList {
    private Long listId;
    private String name;
    private String description;
    private String email;
}