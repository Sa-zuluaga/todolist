package org.sazuluaga.todolist.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.sazuluaga.todolist.domain.exception.BadRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
@Slf4j
public class HandlerException extends ResponseEntityExceptionHandler {

    private static final String ADMIN_ERROR = "An error has ocurred please contact the administrator.";
    private static final ConcurrentHashMap<String, Integer> STATUS_CODE = new ConcurrentHashMap<>();

    public HandlerException() {
        STATUS_CODE.put(BadRequest.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Exception.class)
    protected final ResponseEntity<ApiExecption> handleAllExceptions(Exception exception) {
        ResponseEntity<ApiExecption> result;

        String excepcionNombre = exception.getClass().getSimpleName();
        String mensaje = exception.getMessage();
        Integer codigo = STATUS_CODE.get(excepcionNombre);
        if (codigo != null) {
            ApiExecption error = new ApiExecption(excepcionNombre, mensaje);
            result = new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
        } else {
            ApiExecption error = new ApiExecption(excepcionNombre, ADMIN_ERROR);
            result = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
