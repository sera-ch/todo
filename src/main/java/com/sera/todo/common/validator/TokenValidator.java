package com.sera.todo.common.validator;

import com.sera.todo.domain.entity.User;
import com.sera.todo.domain.enumeration.UserRole;
import com.sera.todo.domain.repository.UserRepository;

public class TokenValidator {
    public static UserRole validateToken(final String token, final User user){
        return UserRole.ADMIN; // TODO: Validate token here
    }
}
