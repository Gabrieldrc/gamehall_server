package com.gdrc.gamehall.server.domain.service;


import com.gdrc.gamehall.server.domain.repository.IUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

class UserServiceTest {

    @Mock private IUserRepository userRepository;
    private AutoCloseable autoCloseable;

    @Autowired
    private UserService underTest;

//    @Test
//    public void should

}