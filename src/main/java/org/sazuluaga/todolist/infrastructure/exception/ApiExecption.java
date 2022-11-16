package org.sazuluaga.todolist.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiExecption {
    private String exception;
    private String message;
}
