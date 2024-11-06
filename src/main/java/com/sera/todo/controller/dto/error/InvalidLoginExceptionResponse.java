package com.sera.todo.controller.dto.error;

import com.sera.todo.domain.enumeration.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidLoginExceptionResponse extends ErrorResponse{
    private String username;
    public InvalidLoginExceptionResponse(String username) {
        super(ErrorCode.INVALID_LOGIN.getErrorCode(), ErrorCode.INVALID_LOGIN.getErrorMessage());
        this.username = username;
    }
}
