package com.gdrc.gamehall.server.domain.service;

import com.gdrc.gamehall.server.domain.model.User;
import com.gdrc.gamehall.server.domain.repository.UserRepository;
import com.gdrc.gamehall.server.util.UserTestUtil;
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
    public void it_should_save_a_user_and_return_it() {
        //given
        User user = UserTestUtil.customUser();
        when(userRepository.save(any())).thenReturn(user);
        //when
        User result = underTest.save(user);
        //then
        assertThat(result)
            .hasFieldOrPropertyWithValue("name", user.getName())
            .hasFieldOrPropertyWithValue("email", user.getEmail());
        assertThat(result.getPassword().length()).isGreaterThan(0);
    }
}