package com.sera.todo.domain.entity.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidPasswordException extends RuntimeException {

    final String message;

}
