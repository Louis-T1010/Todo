package com.example.todo.dal.user;

import com.example.todo.Interface.User.UserInterface;
import com.example.todo.controller.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements UserInterface {
    public static String sessionUsername;

    public static String sessionEmail;
    public static String sessionPassword;
    public static int sessionUserID;

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/UserDB", "root", "hyena");
    }


    @Override
    public User userRegister(String username, String email, String password) {
        if(username == null || email == null || password == null ){
            throw new RuntimeException("All fields should be populated");
        }
        List<User> users = getUsers();
        for(User userEntry : users){
            if(userEntry.getUsername().equals(username) || userEntry.getEmail().equals(email)){
                System.out.println("User already exists");
                return null;
            }
        }
        try(Statement s = getConnection().createStatement()){
            int executeSuccess = s.executeUpdate("INSERT INTO users (username, email, password) VALUES ('%s', '%s', '%s')".formatted(username, email, password));
            if (executeSuccess > 0) {
                return new User(username, email, password);
            }
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        try(
                Statement statement = getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE active = 1")

        ){
            while(resultSet.next()){
                User user = extractUserFromResultSet(resultSet);
                users.add(user);
            }
            return users;
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public User getUser(String usernameOrEmail, String password) {
        try (
             Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE (username = '%s' OR email = '%s') AND password = '%s'".formatted(usernameOrEmail, usernameOrEmail,password))
        ) {
            if (resultSet.next()) {
                sessionUsername = resultSet.getString("username");
                sessionEmail = resultSet.getString("email");
                sessionPassword = resultSet.getString("password");
                sessionUserID = resultSet.getInt("id");
                System.out.printf("TaskManager.getUser | username: %s%n", sessionUsername);
                System.out.printf("TaskManager.getUser | email: %s%n", sessionEmail);
                System.out.printf("TaskManager.getUser | password: %s%n", sessionPassword);
                System.out.printf("TaskManager.getUser | userId: %d%n", sessionUserID);
                return extractUserFromResultSet(resultSet);
            }
            return null;
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User userDelete(User user){
        try (Statement statement = getConnection().createStatement()){
            Class.forName("com.mysql.jdbc.Driver");
            user = getUser(sessionUsername, sessionPassword);
            int updateRows = statement.executeUpdate("UPDATE users SET active=0 WHERE username='%s' AND active=1".formatted(user.getUsername()));
            if(updateRows>0){
                try(ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username = '%s' AND active = 0".formatted(user.getUsername()))
                ) {
                    if (resultSet.next()) {
                        return extractUserFromResultSet(resultSet);
                    }
                }
            }
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User chUserName(String  currentUsername, String newUsername){
        if (currentUsername == null || newUsername == null){
            throw new RuntimeException("Username and new username should be populated");
        }
        try(Statement statement = getConnection().createStatement()){
            Class.forName("com.mysql.jdbc.Driver");
            int updateRows = statement.executeUpdate("UPDATE users SET username='%s' WHERE username='%s' AND active=1".formatted(newUsername, currentUsername));
            if(updateRows>0){
                try(ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username = '%s' AND active = 1".formatted(newUsername))
                ) {
                    if (resultSet.next()) {
                        return extractUserFromResultSet(resultSet);
                    }
                }
            }
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public User chEmail(String currentEmail, String newEmail) {
        if (currentEmail == null || newEmail == null){
            throw new RuntimeException("Email and new email should be populated");
        }
        try (Statement statement = getConnection().createStatement()) {
            Class.forName("com.mysql.jdbc.Driver");
            int updateRows = statement.executeUpdate("UPDATE users SET email='%s' WHERE email='%s' AND active=1".formatted(newEmail, currentEmail));
            if (updateRows > 0 ) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE email = '%s' AND active = 1".formatted(newEmail))) {
                    if(resultSet.next()) {
                        return extractUserFromResultSet(resultSet);
                    }
                }
            }
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User chPassword(String currentPassword, String newPassword, String username){
        if (currentPassword == null || newPassword == null || username == null) {
            throw new RuntimeException("All fields should be populated");
        }
        if (!currentPassword.equals(sessionPassword)) {
            throw new RuntimeException("Incorrect current password");
        }
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ) {
            int updateRows = statement.executeUpdate("UPDATE users SET password='%s' WHERE username='%s' AND password='%s' AND active=1".formatted(newPassword, username, currentPassword));
            if (updateRows > 0) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username = '%s' AND password = '%s' AND active = 1".formatted(username, newPassword))) {
                    if (resultSet.next()) {
                        return extractUserFromResultSet(resultSet);
                    }
                }
            }
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUsername(resultSet.getString("username"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setUserId(resultSet.getInt("id"));
        user.setActive(resultSet.getInt("active"));
        return user;
    }
}
