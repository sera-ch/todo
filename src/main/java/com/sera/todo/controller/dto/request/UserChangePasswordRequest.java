package com.sera.todo.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserChangePasswordRequest {

    @JsonProperty(value = "username", required = true)
    private String username;

    @JsonProperty(value = "old_password", required = true)
    private String oldPassword;

    @JsonProperty(value = "new_password", required = true)
    private String newPassword;

    @JsonProperty(value = "repeat_new_password", required = true)
    private String repeatNewPassword;

}
