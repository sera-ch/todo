package com.sera.todo.domain.entity.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidLoginException extends RuntimeException {
    private String username;
}
