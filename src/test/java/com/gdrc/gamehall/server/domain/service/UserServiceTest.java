package com.gdrc.gamehall.server.domain.service;

import com.gdrc.gamehall.server.domain.exceptions.UserAlreadyExistException;
import com.gdrc.gamehall.server.domain.exceptions.UserNotFoundException;
import com.gdrc.gamehall.server.domain.model.User;
import com.gdrc.gamehall.server.domain.repository.UserRepository;
import com.gdrc.gamehall.server.util.UserTestUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserService.class)
class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService underTest;

    @Test
    public void it_should_get_a_user_by_name_and_return_the_user() {
        //given
        User user = UserTestUtil.customUser();
        when(userRepository.getUserByName(any())).thenReturn(Optional.of(user));
        //when
        Optional<User> result = underTest.getUser(new User(user.getName(), "", ""));
        //then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get())
                .hasFieldOrPropertyWithValue("name", user.getName())
                .hasFieldOrPropertyWithValue("email", user.getEmail());
    }

    @Test
    public void it_should_get_a_user_by_email_and_return_the_user() {
        //given
        User user = UserTestUtil.customUser();
        when(userRepository.getUserByName(any())).thenReturn(Optional.empty());
        when(userRepository.getUserByEmail(any())).thenReturn(Optional.of(user));
        //when
        Optional<User> result = underTest.getUser(new User("", "", user.getEmail()));
        //then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get())
                .hasFieldOrPropertyWithValue("name", user.getName())
                .hasFieldOrPropertyWithValue("email", user.getEmail());
    }

    @Test
    public void it_should_return_an_empty_optional_when_try_to_get_a_user_which_not_exist() {
        //given
        User user = UserTestUtil.customUser();
        when(userRepository.getUserByName(any())).thenReturn(Optional.empty());
        when(userRepository.getUserByEmail(any())).thenReturn(Optional.empty());
        //when
        Optional<User> result1 = underTest.getUser(new User(user.getName(), "", ""));
        Optional<User> result2 = underTest.getUser(new User("", "", user.getEmail()));
        //then
        assertThat(result1.isEmpty()).isTrue();
        assertThat(result2.isEmpty()).isTrue();
    }

    @Test
    public void it_should_return_an_empty_optional_when_dont_recieve_user_with_username_or_email() {
        //given
        when(userRepository.getUserByName(any())).thenReturn(Optional.empty());
        when(userRepository.getUserByEmail(any())).thenReturn(Optional.empty());
        //when
        Optional<User> result = underTest.getUser(new User("", "", ""));
        //then
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    public void it_should_create_a_user() throws UserAlreadyExistException {
        //given
        User user = UserTestUtil.customUser();
        when(userRepository.save(any())).thenReturn(user);
        //when
        boolean result = underTest.createUser(user);
        //then
        assertThat(result).isTrue();
    }

    @Test
    public void it_should_throw_an_exception_if_the_user_already_exists() {
        //given
        User user = UserTestUtil.customUser();
        when(userRepository.save(any())).thenThrow(new Error());
        when(userRepository.getUserByEmail(any())).thenReturn(Optional.of(user));
        when(userRepository.getUserByName(any())).thenReturn(Optional.of(user));
        //when
        assertThatThrownBy(() -> {
            underTest.createUser(user);
        })
        //then
        .isInstanceOf(UserAlreadyExistException.class)
        .hasMessageContaining("User already exists");
    }

    @Test
    public void it_should_update_a_user_and_return_the_user() throws UserNotFoundException {
        //given
        User user = UserTestUtil.customUser();
        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.getUserByEmail(any())).thenReturn(Optional.of(user));
        when(userRepository.getUserByName(any())).thenReturn(Optional.of(user));
        //when
        User result = underTest.update(user);
        //then
        assertThat(result)
                .hasFieldOrPropertyWithValue("name", user.getName())
                .hasFieldOrPropertyWithValue("email", user.getEmail())
                .hasFieldOrPropertyWithValue("password", user.getPassword());
    }

    @Test
    public void it_should_throw_an_exception_if_user_does_not_exist() {
        //given
        User user = UserTestUtil.customUser();
        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.getUserByEmail(any())).thenReturn(Optional.empty());
        when(userRepository.getUserByName(any())).thenReturn(Optional.empty());
        //when
        assertThatThrownBy(() -> {
            underTest.update(user);
        })
        //then
        .isInstanceOf(UserNotFoundException.class)
        .hasMessageContaining("User was not found");
    }
}