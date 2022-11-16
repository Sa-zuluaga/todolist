package org.sazuluaga.todolist.domain.common;

public class ArgumentValidator {

    private ArgumentValidator() {
    }

    public static boolean stringIsValid(String s) {
        return (s != null && !s.isBlank());
    }
}
