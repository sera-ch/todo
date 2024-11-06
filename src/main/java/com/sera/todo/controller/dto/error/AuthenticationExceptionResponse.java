package com.sera.todo.controller.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.enumeration.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationExceptionResponse extends ErrorResponse {

    @JsonProperty(value = "cause")
    private String cause;

    public AuthenticationExceptionResponse(String cause) {
        super(ErrorCode.AUTHENTICATION_FAILED.getErrorCode(), ErrorCode.AUTHENTICATION_FAILED.getErrorMessage());
        this.cause = cause;
    }
}
