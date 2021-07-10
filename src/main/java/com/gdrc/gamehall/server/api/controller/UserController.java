package com.gdrc.gamehall.server.api.controller;

import com.gdrc.gamehall.server.domain.dto.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("")
    public ResponseEntity<DefaultResponse> getUsers() {
        DefaultResponse response = new DefaultResponse();
        response.setOk(true);
        response.setDataProperty("funciona", "pues si");
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }
}
