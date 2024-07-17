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

@WebServlet("ch-email")
public class ChangeEmailServlet extends HttpServlet {

    private HttpSession getSession(HttpServletRequest request){
        return request.getSession();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManager userManager = new UserManager();
        User user = userManager.getUser(sessionUsername, sessionPassword);
        String currentEmail = user.getEmail();
        User changedUser = userManager.chEmail(currentEmail, request.getParameter("change-email"));
        String currentPassword = user.getPassword();
        String enteredPassword = request.getParameter("current-password");
        System.out.println("current password: " + currentPassword);
        System.out.println("entered password: " + enteredPassword);
        if (!currentPassword.equals(enteredPassword)) {
            Errors.passwordError( response, request);
        }
        if(changedUser != null) {
            Errors.success(response, request);
        }else{
            Errors.fail(response, request);
        }
    }

}
