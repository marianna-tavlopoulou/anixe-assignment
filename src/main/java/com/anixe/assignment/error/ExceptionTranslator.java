package com.anixe.assignment.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ExceptionTranslator {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse processBadRequestException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.builder()
            .errorCode(HttpStatus.BAD_REQUEST.value())
            .errorKey(HttpStatus.BAD_REQUEST.name())
            .errorMessage(exception.getMessage())
            .build();
    }
}
