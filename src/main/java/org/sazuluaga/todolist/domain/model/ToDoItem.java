package org.sazuluaga.todolist.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToDoItem {
    private Long itemId;
    private String description;
    private Boolean done;
    private ToDoList list;
}
