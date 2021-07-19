package net.minimgun.quizframework.exceptions.handling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.minimgun.quizframework.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {

    private final Logger LOG = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    ExceptionEntity entityNotFoundHandler(EntityNotFoundException e) {
        LOG.warn("A EntityNotFoundException was thrown and handled: " + e.getMessage());
        return new ExceptionEntity(e);
    }
}
