package com.gdrc.gamehall.server.domain.repository;

import com.gdrc.gamehall.server.domain.model.User;

public interface IUserRepository {
    User save(User user);
}
