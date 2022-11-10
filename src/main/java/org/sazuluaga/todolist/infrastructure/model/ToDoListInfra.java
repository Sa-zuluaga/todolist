package org.sazuluaga.todolist.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Lists")
public class ToDoListInfra {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long listId;
    private String name;
    private String description;
    private String user;
}
