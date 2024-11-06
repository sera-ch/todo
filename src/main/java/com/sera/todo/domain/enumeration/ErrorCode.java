package com.sera.todo.domain.enumeration;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Authentication
    AUTHENTICATION_FAILED("AUT000", "Authentication failed"),
    INVALID_LOGIN("AUT002", "Invalid username or password"),

    // Users
    USER_NOT_FOUND("USR000", "User not found"),
    USER_ALREADY_EXISTS("USR001", "User already exists"),

    // Tasks
    TASK_NOT_FOUND("TSK000", "Task not found"),

    // Validation
    INVALID_PASSWORD("PWD000", "Invalid password"),

    // Miscellaneous
    ENCODING_FAILED("MSC000", "Encoding failed"),

    ;

    private String errorCode;
    private String errorMessage;

    ErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
