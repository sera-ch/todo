package com.sera.todo.controller.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.enumeration.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAlreadyExistsExceptionResponse extends ErrorResponse{

    @JsonProperty(value = "username")
    private String username;

    public UserAlreadyExistsExceptionResponse(String username) {
        super(ErrorCode.USER_ALREADY_EXISTS.getErrorCode(), ErrorCode.USER_ALREADY_EXISTS.getErrorMessage());
        this.username = username;
    }
}
