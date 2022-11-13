package org.sazuluaga.todolist.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lists")
public class ToDoListInfra {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long listId;

    private String name;
    private String description;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "listId",referencedColumnName = "listId")
    List<ToDoItemInfra> items = new ArrayList<>();



}
