package com.gdrc.gamehall.server.api.controller;

import com.gdrc.gamehall.server.api.security.JWTUtil;
import com.gdrc.gamehall.server.domain.dto.DefaultResponse;
import com.gdrc.gamehall.server.domain.model.User;
import com.gdrc.gamehall.server.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UsersRestTest {

    public static final String ROUTE = "/users";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private PasswordEncoderUtil encoder;

    @MockBean
    private JWTUtil tokenizer;

    @Test
    public void registerShouldReturnOkTrueWithStatusCreatedAndTheUsernameOfTheUser() throws Exception {
        User user = createUser(0);
        when(service.createUser(any())).thenReturn(true);

        this.mockMvc.perform(
                post(ROUTE+"/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(user.toString())
            ) .andDo(print())

            .andExpect(status().isCreated())
            .andExpect(content().json("{\"ok\":true,\"data\":{\"username\":\""+user.getName()+"\"}}"));
    }

    @Test
    public void registerShouldReturnOkFalseAndStatusBadRequestWithAMessageOfTheError() throws Exception {
        User user = createUser(0);
        when(service.createUser(any())).thenReturn(false);

        this.mockMvc.perform(
                post(ROUTE+"/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user.toString())
        ) .andDo(print())

                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"ok\":false,\"data\":{\"message\":\"User not created\"}}", false));
    }

    @Test
    public void loginShouldReturnOkTrueAndStatusOkWithAUserToken() throws Exception {
        User user = createUser(0);
        when(service.getUser(any())).thenReturn(Optional.of(user));
        when(encoder.matches(any(), any())).thenReturn(true);
        when(tokenizer.generateToken(any())).thenReturn("token_ecrypted");

        this.mockMvc.perform(
                post(ROUTE+"/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user.toString())
        ) .andDo(print())

                .andExpect(status().isOk())
                .andExpect(content().json("{\"ok\":true,\"data\":{\"user_token\":\"token_ecrypted\"}}"));
    }

    @Test
    public void loginShouldReturnOkFalseAndStatusBadRequestAndAMessageWhenThePasswordIsIncorrect() throws Exception {
        User user = createUser(0);
        when(service.getUser(any())).thenReturn(Optional.of(user));
        when(encoder.matches(any(), any())).thenReturn(false);

        this.mockMvc.perform(
                post(ROUTE+"/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user.toString())
        ) .andDo(print())

                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"ok\":false,\"data\":{\"message\":\"Error: Incorrect password\"}}"));
    }

    @Test
    public void loginShouldReturnOkFalseAndStatusNotFoundAndAMessageWhenTheUserIsNotFound() throws Exception {
        User user = createUser(0);
        when(service.getUser(any())).thenReturn(Optional.of(user));
        when(encoder.matches(any(), any())).thenReturn(false);

        this.mockMvc.perform(
                post(ROUTE+"/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user.toString())
        ) .andDo(print())

                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"ok\":false,\"data\":{\"message\":\"Error: Incorrect password\"}}"));
    }

    private User createUser(int id) {
        User user = new User();
        user.setName("user"+id);
        user.setEmail("e"+id+"@mail.com");
        user.setPassword("superSecureP4ssw0rd");
        return user;
    }

    private List<User> createListOfUsers(int quantity) {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            users.add(createUser(i));
        }
        return users;
    }
}
