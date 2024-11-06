package com.sera.todo.controller.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.enumeration.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundExceptionResponse extends ErrorResponse{

    @JsonProperty("username_or_token")
    private String usernameOrToken;

    public UserNotFoundExceptionResponse(String usernameOrToken) {
        super(ErrorCode.USER_NOT_FOUND.getErrorCode(), ErrorCode.USER_NOT_FOUND.getErrorMessage());
        this.usernameOrToken = usernameOrToken;
    }
}
