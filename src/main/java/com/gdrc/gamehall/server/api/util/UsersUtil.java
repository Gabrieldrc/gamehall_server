package com.gdrc.gamehall.server.api.util;

import com.gdrc.gamehall.server.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UsersUtil {
    public static HashMap<String, String> usersFilterInformation(User user) {
        HashMap userInformation = new HashMap<String,String>();
        userInformation.put("name", user.getName());
        userInformation.put("email", user.getEmail());
        return userInformation;
    }

    public static List<HashMap<String, Object>> usersFilterInformation(List<User> users) {
        ArrayList<HashMap<String, Object>> usersInformation = new ArrayList<>();
        for (User user : users) {
            HashMap userInformation = new HashMap<String,Object>();
            userInformation.put("name", user.getName());
            userInformation.put("email", user.getEmail());
            usersInformation.add(userInformation);
        }
        return usersInformation;
    }
}
