package com.sera.todo.common.annotation;

import com.sera.todo.domain.entity.User;
import com.sera.todo.domain.entity.error.AuthenticationException;
import com.sera.todo.domain.enumeration.UserRole;
import com.sera.todo.domain.repository.UserRepository;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.AllArgsConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Constraint(validatedBy = UserPermission.Validator.class)
public @interface UserPermission {

    String message() default "User is not logged in";
    Class<?>[] groups() default {};
    String propName() default "token";
    boolean strict() default false;
    Class<? extends Payload>[] payload() default {};

    @AllArgsConstructor
    class Validator implements ConstraintValidator<UserPermission, String> {
        private final UserRepository userRepository;

        @Override
        public boolean isValid(String token, ConstraintValidatorContext context) {
            final Optional<User> user = this.userRepository.findByToken(token);
            if (user.isEmpty()) {
                throw new AuthenticationException("User is not logged in");
            }
            return true;
        }
    }
}
