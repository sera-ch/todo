package com.sera.todo.common.validator;

import com.sera.todo.domain.entity.error.InvalidPasswordException;

public class PasswordValidator {
    private static final int MINIMUM_PASSWORD_LENGTH = 6;

    public static void validatePassword(final String password, final String repeatPassword) {
        validatePasswordLength(password);
        validatePasswordMatching(password, repeatPassword);
    }

    private static void validatePasswordLength(final String password){
        if (password.length() < MINIMUM_PASSWORD_LENGTH) {
            throw new InvalidPasswordException(String.format("Password must be at least %s characters", MINIMUM_PASSWORD_LENGTH));
        }
    }

    private static void validatePasswordMatching(final String password, final String repeatPassword) {
        if (!password.equals(repeatPassword)) {
            throw new InvalidPasswordException("Password does not match");
        }
    }
}
