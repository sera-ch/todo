package com.sera.todo.controller.dto.error.handler;

import com.sera.todo.controller.dto.error.*;
import com.sera.todo.domain.entity.error.*;
import com.sera.todo.domain.enumeration.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<AuthenticationExceptionResponse> handleAuthenticationException(final AuthenticationException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new AuthenticationExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(EncodingException.class)
    public ResponseEntity<EncodingExceptionResponse> handleEncodingException(final EncodingExceptionResponse exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EncodingExceptionResponse(exception.getCause()));
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<InvalidLoginExceptionResponse> handleInvalidLoginException(final InvalidLoginException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new InvalidLoginExceptionResponse(exception.getUsername()));
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<InvalidPasswordExceptionResponse> handleInvalidPasswordException(final InvalidPasswordException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new InvalidPasswordExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<TaskNotFoundExceptionResponse> handleTaskNotFoundException(final TaskNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new TaskNotFoundExceptionResponse(exception.getTaskId()));
    }

    @ExceptionHandler(ChecklistNotFoundException.class)
    public ResponseEntity<ChecklistNotFoundExceptionResponse> handleChecklistNotFoundException(final ChecklistNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ChecklistNotFoundExceptionResponse(exception.getChecklistId()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<UserAlreadyExistsExceptionResponse> handleUserAlreadyExistsException(final UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new UserAlreadyExistsExceptionResponse(exception.getUsername())
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundExceptionResponse> handleUserNotFoundException(final UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new UserNotFoundExceptionResponse(exception.getUsernameOrToken())
        );
    }
}
