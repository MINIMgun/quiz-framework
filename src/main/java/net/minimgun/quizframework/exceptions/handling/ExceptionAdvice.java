package net.minimgun.quizframework.exceptions.handling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import net.minimgun.quizframework.exceptions.EntityNotFoundException;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionAdvice {

    private final Logger LOG = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    ExceptionEntity entityNotFoundHandler(EntityNotFoundException e) {
        logException(EntityNotFoundException.class, e);
        return new ExceptionEntity(e);
    }

    @ResponseBody
    @ExceptionHandler(HttpClientErrorException.class)
    ResponseEntity<ExceptionEntity> httpClientErrorExceptionHandler(HttpClientErrorException e) {
        logException(HttpClientErrorException.class, e);
        return new ResponseEntity<ExceptionEntity>(new ExceptionEntity(e), e.getStatusCode());
    }

    private void logException(Class<?> exceptionType, Exception exception) {
        LOG.warn("A " + exceptionType.getSimpleName() + " was thrown and handled: " + exception.getMessage());
    }
}
