package org.sazuluaga.todolist.domain.exception;

import lombok.Getter;

@Getter
public class BadRequest extends RuntimeException {

    public BadRequest(String message) {
        super(message);
    }
}

