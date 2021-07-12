package com.gdrc.gamehall.server.domain.service;

import com.gdrc.gamehall.server.domain.model.User;
import com.gdrc.gamehall.server.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("user1", "123", "e@mail.com"));
        users.add(new User("user2", "321", "e2@mail.com"));
        return users;
    }

    public boolean createUser(User user) {
        SecurityProperties.User user1 = new SecurityProperties.User();
        user.setPassword(securityService.encode(user.getPassword()));
        System.out.println(user);
        return true;
    }
}
