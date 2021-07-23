package com.gdrc.gamehall.server.domain.service;

import com.gdrc.gamehall.server.domain.exceptions.UserAlreadyExistException;
import com.gdrc.gamehall.server.domain.exceptions.UserNotFoundException;
import com.gdrc.gamehall.server.domain.model.User;
import com.gdrc.gamehall.server.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

//    public List<User> getAllUsers() {
//        return repository.get;
//    }
//
//    public boolean createUser(User user) {
//        SecurityProperties.User user1 = new SecurityProperties.User();
//        user.setPassword(securityService.encode(user.getPassword()));
//        System.out.println(user);
//        return true;
//    }
//
    public Optional<User> getUser(User user) {
        if (!user.getName().isBlank()) {
            return this.repository.getUserByName(user.getName());
        }
        if (!user.getEmail().isBlank()) {
            return this.repository.getUserByEmail(user.getEmail());
        }
        return Optional.empty();
    }

    private User save(User user) {
        return this.repository.save(user);
    }

    public boolean createUser(User user) throws UserAlreadyExistException {
        if (this.getUser(user).isEmpty()) {
            this.save(user);
            return true;
        }
        throw new UserAlreadyExistException();
    }

    public User update(User user) throws UserNotFoundException {
        if (this.getUser(user).isPresent()) {
            return this.repository.save(user);
        }
        throw new UserNotFoundException();
    }

    public String getUserId(User user) {
        if (!user.getName().isBlank()) {
            return this.repository.getUserIdByName(user.getName());
        }
        if (!user.getEmail().isBlank()) {
            return this.repository.getUserIdByEmail(user.getEmail());
        }
        return "";
    }
}
