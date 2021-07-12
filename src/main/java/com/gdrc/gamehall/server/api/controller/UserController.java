package com.gdrc.gamehall.server.api.controller;

import com.gdrc.gamehall.server.api.util.UsersUtil;
import com.gdrc.gamehall.server.domain.dto.DefaultResponse;
import com.gdrc.gamehall.server.domain.model.User;
import com.gdrc.gamehall.server.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<DefaultResponse> register(@RequestBody User user) {
        DefaultResponse response = new DefaultResponse();
        if (userService.createUser(user)) {
            response.setOk(true);
            response.setDataProperty("username", user.getName());
            response.setDataProperty("message", "User created successfully");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.CREATED
            );
        }
        response.setOk(false);
        response.setDataProperty("message", "User not created");
        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );

    }
}
