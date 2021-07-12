package com.gdrc.gamehall.server.domain.model;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class User extends SecurityProperties.User {

    private String email;
    private boolean passwordEncrypted = false;
    public User() {}

    public User(String name, String password, String email) {
        this.setName(name);
        this.setPassword(password);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\""+getName()+"\"," +
                "\"email\":\""+getEmail()+"\"" +
                "}";
    }
}
