package com.sera.todo.domain.entity.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EncodingException extends RuntimeException {

    final String message;

}
