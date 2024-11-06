package com.sera.todo.controller;

import com.sera.todo.controller.dto.request.UserLoginRequest;
import com.sera.todo.controller.dto.request.UserRegisterRequest;
import com.sera.todo.controller.dto.response.UserResponse;
import com.sera.todo.domain.entity.User;
import com.sera.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody final UserLoginRequest request) {
        final User user = this.userService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(UserResponse.builder().token(user.getToken()).role(user.getRole()).build());
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody final UserRegisterRequest request) {
        final User user = this.userService.register(request.getUsername(), request.getPassword(), request.getRepeatPassword());
        return ResponseEntity.ok(UserResponse.builder()
                .token(user.getToken())
                .role(user.getRole())
                .build());
    }
}
