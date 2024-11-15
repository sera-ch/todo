package com.sera.todo.controller.dto.response;

import com.sera.todo.domain.enumeration.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private String token;
    private String username;
    private UserRole role;
}
