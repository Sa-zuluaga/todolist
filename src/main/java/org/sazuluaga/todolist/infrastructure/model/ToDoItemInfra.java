package org.sazuluaga.todolist.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class ToDoItemInfra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String description;
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ToDoListInfra list;
}
