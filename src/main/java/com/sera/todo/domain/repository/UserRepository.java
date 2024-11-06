package com.sera.todo.domain.repository;

import com.sera.todo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(final String username, final String password);

    Optional<User> findByUsername(String username);

    Optional<User> findByToken(String token);
}
