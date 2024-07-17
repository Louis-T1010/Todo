package com.example.todo.controller.user;

import com.example.todo.controller.Errors;
import com.example.todo.dal.user.UserManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

import static com.example.todo.dal.user.UserManager.sessionPassword;
import static com.example.todo.dal.user.UserManager.sessionUsername;

@WebServlet("ch-username")
public class ChangeUsernameServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManager userManager = new UserManager();
        User user = userManager.getUser(sessionUsername, sessionPassword);
        HttpSession session = request.getSession();
        String currentUsername = user.getUsername();
        String newUsername = request.getParameter("change-username");
        String currentPassword = user.getPassword();
        String enteredPassword = request.getParameter("current-password");
        if(!currentPassword.equals(enteredPassword)) {
            Errors.passwordError(response, request);
        }else {
            try {
                user = userManager.chUserName(currentUsername, newUsername);
                if (user != null) {
                    Errors.success(response, request);
                }else{
                    Errors.fail(response, request);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error changing username");
                System.out.println("Current username: " + currentUsername);
                System.out.println("new username: " + request.getParameter("change-username"));
            }
        }
    }

}
