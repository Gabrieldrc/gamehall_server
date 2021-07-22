package com.gdrc.gamehall.server.domain.repository;

import com.gdrc.gamehall.server.domain.model.User;
import com.gdrc.gamehall.server.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByName(String name);

}
