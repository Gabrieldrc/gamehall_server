package com.gdrc.gamehall.server.api.controller;

import com.gdrc.gamehall.server.api.security.JWTUtil;
import com.gdrc.gamehall.server.domain.dto.DefaultResponse;
import com.gdrc.gamehall.server.domain.dto.ErrorResponse;
import com.gdrc.gamehall.server.domain.model.User;
import com.gdrc.gamehall.server.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoderUtil encoderUtil;

    @Autowired
    private JWTUtil jwt;

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
        return new ResponseEntity<>(
                new ErrorResponse("User not created"),
                HttpStatus.BAD_REQUEST
        );

    }

    @PostMapping("/login")
    public ResponseEntity<DefaultResponse> login(@RequestBody User user) {
        Optional<User> result = userService.getUser(user);
        return (ResponseEntity<DefaultResponse>) result.map(userDto -> {
            if (encoderUtil.matches(user.getPassword(), userDto.getPassword())) {
                DefaultResponse response = new DefaultResponse();
                response.setOk(true);
                response.setDataProperty("user_token", jwt.generateToken(userDto));
                return new ResponseEntity<>(
                    response,
                    HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                new ErrorResponse("Error: Incorrect password"),
                HttpStatus.BAD_REQUEST
            );
        })
        .orElse(new ResponseEntity<>(new ErrorResponse("Error: User not founded"), HttpStatus.NOT_FOUND));
    }
}
