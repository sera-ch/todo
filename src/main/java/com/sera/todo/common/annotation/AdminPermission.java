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
@Constraint(validatedBy = AdminPermission.Validator.class)
public @interface AdminPermission {

    String message() default "User is not admin";
    Class<?>[] groups() default {};
    String propName() default "token";
    Class<? extends Payload>[] payload() default {};

    @AllArgsConstructor
    class Validator implements ConstraintValidator<AdminPermission, String> {
        private final UserRepository userRepository;

        @Override
        public boolean isValid(String token, ConstraintValidatorContext context) {
            final Optional<User> user = this.userRepository.findByToken(token);
            if (user.isEmpty() || user.get().getRole() != UserRole.ADMIN) {
                throw new AuthenticationException("User is not admin");
            }
            return true;
        }
    }
}
