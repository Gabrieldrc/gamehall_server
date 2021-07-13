package com.gdrc.gamehall.server.domain.repository;

import com.gdrc.gamehall.server.domain.model.User;

import java.util.Optional;

public interface IUserRepository {
    User save(User user);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByName(String name);

}
