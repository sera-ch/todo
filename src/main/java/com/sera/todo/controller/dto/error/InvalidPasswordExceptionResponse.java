package com.sera.todo.controller.dto.error;

import com.sera.todo.domain.enumeration.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidPasswordExceptionResponse extends ErrorResponse{
    private String cause;
    public InvalidPasswordExceptionResponse(String cause) {
        super(ErrorCode.INVALID_PASSWORD.getErrorCode(), ErrorCode.INVALID_PASSWORD.getErrorMessage());
        this.cause = cause;
    }
}
