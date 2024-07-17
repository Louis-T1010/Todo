package com.example.todo.controller.user;


public class User {
    private String username;
    private String email;

    private String password;

    private int id;


    private int active;


    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    public int getUserId() {
        return id;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(String username, String email, String password, int id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = id;
    }
    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
