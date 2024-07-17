package com.example.todo.controller.user;

import javax.servlet.annotation.WebServlet;

import com.example.todo.controller.Errors;
import com.example.todo.dal.user.UserManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.example.todo.dal.user.UserManager.sessionPassword;
import static com.example.todo.dal.user.UserManager.sessionUsername;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManager userManager = new UserManager();
        User user = userManager.getUser(sessionUsername, sessionPassword);
        HttpSession session = request.getSession();
        String currentPassword = request.getParameter("cPassword");
        String password = user.getPassword();
        String message = null;
        if(!currentPassword.equals(password)) {
            Errors.passwordError(response, request);
        }else {
            User userToDelete = userManager.userDelete(user);
            if (userToDelete != null) {
                message = "delete success";
                session.setAttribute("message", message);
                response.sendRedirect("settings");
            } else {
                Errors.fail(response, request);
                response.getWriter().println("Error deleting user");
                System.out.println("Error deleting user");
                System.out.println(sessionUsername);
                System.out.println(UserManager.sessionEmail);
            }
        }
    }
}
