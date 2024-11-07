package com.sera.todo.controller.dto.response;

import com.sera.todo.domain.enumeration.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserChangePasswordResponse {
    private String username;
}
