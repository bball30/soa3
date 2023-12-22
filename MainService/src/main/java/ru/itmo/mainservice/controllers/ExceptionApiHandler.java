package ru.itmo.mainservice.controllers;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itmo.mainservice.DTO.ErrorData;
import ru.itmo.mainservice.exceptions.StudyGroupDoesNotExistException;
import ru.itmo.mainservice.exceptions.WrongFilterException;

import java.util.ArrayList;
import java.util.List;

import static ru.itmo.mainservice.utils.Constants.STUDY_GROUP_VALIDATION_EXPRESSION;


@RestControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<?> constraintViolationException(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }
        return ResponseEntity.badRequest().body(new ErrorData(errors
                .stream()
                .reduce("Errors: ", (acc, error) -> acc + " - " + error + "\n")));
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    protected ResponseEntity<?> constraintViolationException(HttpServerErrorException.InternalServerError e) {
        return ResponseEntity.badRequest().body(new ErrorData("Сервер недоступен"));
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    protected ResponseEntity<?> constraintViolationException(ChangeSetPersister.NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorData("Not Found"));
    }

    @ExceptionHandler(StudyGroupDoesNotExistException.class)
    protected ResponseEntity<?> constraintViolationException(StudyGroupDoesNotExistException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorData("Not Found"));
    }

    @ExceptionHandler(WrongFilterException.class)
    protected ResponseEntity<?> constraintViolationException(WrongFilterException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorData("`" + e.getMessage() + "`" +
                        " must be match pattern " + "/" + STUDY_GROUP_VALIDATION_EXPRESSION + "/"));
    }
}
