package com.gdrc.gamehall.server.persistence;

import com.gdrc.gamehall.server.domain.model.User;
import com.gdrc.gamehall.server.domain.repository.UserRepository;
import com.gdrc.gamehall.server.persistence.crud.UserCrudRepository;
import com.gdrc.gamehall.server.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserCrudRepository crudRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public User save(User user) {
        return mapper.toUser(crudRepository.save(mapper.toUserEntity(user)));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return crudRepository.findByEmail(email)
                .map(userEntity -> mapper.toUser(userEntity));
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return crudRepository.findByUsername(name)
                .map(userEntity -> mapper.toUser(userEntity));
    }
}
