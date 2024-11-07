package com.sera.todo.controller;

import com.sera.todo.common.annotation.AdminPermission;
import com.sera.todo.controller.dto.request.UserChangePasswordRequest;
import com.sera.todo.controller.dto.request.UserLoginRequest;
import com.sera.todo.controller.dto.request.UserRegisterRequest;
import com.sera.todo.controller.dto.response.UserChangePasswordResponse;
import com.sera.todo.controller.dto.response.UserResponse;
import com.sera.todo.domain.entity.User;
import com.sera.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin
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

    @PutMapping("/change_password")
    public ResponseEntity<UserChangePasswordResponse> changePassword(@AdminPermission @RequestHeader(value = "token") String token,
                                                       @RequestBody UserChangePasswordRequest request
                                                       ) {
        return ResponseEntity.ok(new UserChangePasswordResponse(this.userService.changePassword(request).getUsername()));
    }
}
