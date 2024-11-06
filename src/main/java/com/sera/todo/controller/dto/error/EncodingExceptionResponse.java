package com.sera.todo.controller.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.enumeration.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncodingExceptionResponse extends ErrorResponse {

    @JsonProperty(value = "cause")
    private String cause;

    public EncodingExceptionResponse(String cause) {
        super(ErrorCode.ENCODING_FAILED.getErrorCode(), ErrorCode.ENCODING_FAILED.getErrorMessage());
        this.cause = cause;
    }
}
