package com.sera.todo.service;

import com.sera.todo.common.EncryptionUtil;
import com.sera.todo.common.config.TodoProperties;
import com.sera.todo.common.validator.PasswordValidator;
import com.sera.todo.controller.dto.request.UserChangePasswordRequest;
import com.sera.todo.domain.entity.User;
import com.sera.todo.domain.entity.error.InvalidLoginException;
import com.sera.todo.domain.entity.error.UserAlreadyExistsException;
import com.sera.todo.domain.entity.error.UserNotFoundException;
import com.sera.todo.domain.enumeration.UserRole;
import com.sera.todo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final TodoProperties properties;

    public User register(final String username, final String password, final String repeatPassword) {
        log.info("Received request to register: username: {}, password: {}, repeat_password: {}", username, password, repeatPassword);
        PasswordValidator.validatePassword(password, repeatPassword);
        Optional<User> existingUser = this.userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(username);
        }
        final String token = UUID.randomUUID().toString();
        final String encryptedPassword = EncryptionUtil.encrypt(password, properties.getSecretKey(), properties.getSalt());
        log.info("Password encrypted: {}", encryptedPassword);
        final User user = User.builder()
                .username(username)
                .password(encryptedPassword)
                .token(token)
                .role(UserRole.USER)
                .build();
        return this.userRepository.save(user);
    }

    public User login(final String username, final String password){
        log.info("Received request to login: username: {}, password: {}", username, password);
        final User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        final String decryptedPassword = EncryptionUtil.decrypt(user.getPassword(), properties.getSecretKey(), properties.getSalt());
        if (!decryptedPassword.equals(password)) {
            throw new InvalidLoginException(username);
        }
        user.updateToken(UUID.randomUUID().toString());
        return this.userRepository.save(user);
    }

    public User changePassword(UserChangePasswordRequest request) {
        PasswordValidator.validatePassword(request.getNewPassword(), request.getRepeatNewPassword());
        User user = this.userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UserNotFoundException(request.getUsername()));
        final String decryptedPassword = EncryptionUtil.decrypt(user.getPassword(), properties.getSecretKey(), properties.getSalt());
        if (!decryptedPassword.equals(request.getOldPassword())) {
            throw new InvalidLoginException(request.getUsername());
        }
        if (!decryptedPassword.equals(request.getNewPassword())) {
            user.setPassword(EncryptionUtil.encrypt(request.getNewPassword(), properties.getSecretKey(), properties.getSalt()));
        }
        return user;
    }
}
