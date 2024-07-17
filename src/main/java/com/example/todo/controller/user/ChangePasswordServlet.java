package com.example.todo.controller.user;

import com.example.todo.controller.Errors;
import com.example.todo.dal.user.UserManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

import static com.example.todo.controller.Errors.success;
import static com.example.todo.dal.user.UserManager.sessionPassword;
import static com.example.todo.dal.user.UserManager.sessionUsername;


@WebServlet("ch-password")
public class ChangePasswordServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserManager userManager = new UserManager();
        User user = userManager.getUser(sessionUsername, sessionPassword);
        String currentPassword = user.getPassword();
        String currentPasswordInput = request.getParameter("cPassword");
        String newPassword = request.getParameter("change-password");
        if(!currentPassword.equals(currentPasswordInput)) {
            Errors.passwordError(response, request);
        }else {
            User userToChange = userManager.chPassword(currentPassword, newPassword, user.getUsername());
            if(userToChange != null) {
                Errors.success(response, request);
            }else{
                Errors.fail(response, request);
            }
        }
    }


}
