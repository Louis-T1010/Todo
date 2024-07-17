package com.example.todo.Interface.User;

import com.example.todo.controller.user.User;

import java.util.List;

public interface UserInterface {

    User userRegister(String username, String email, String password);


    User getUser(String usernameOrEmail, String password);

    List<User> getUsers();

    User userDelete(User user);

    User chUserName(String  currentUsername, String newUsername);

    User chEmail(String currentEmail, String newEmail);

    User chPassword(String currentPassword, String newPassword, String username);

}
