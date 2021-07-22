package com.gdrc.gamehall.server.util;

import com.gdrc.gamehall.server.domain.model.User;
import com.gdrc.gamehall.server.persistence.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserTestUtil {
    public static User customUser() {
        return new User("defaultUser", "password", "e@mail.com");
    }

    public static List<User> getListOfUsers(int quantity) {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            userList.add(new User("user"+i, "password", "e"+i+"@mail.com"));
        }
        return userList;
    }
}
