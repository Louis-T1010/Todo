package com.example.todo.controller.user;

import com.example.todo.dal.user.UserManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public static String username;
    public static String email;
    public static String password;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User();
        UserManager userManager = new UserManager();
        HttpSession session = request.getSession();
        String usernameOrEmail = request.getParameter("usernameOrEmail");
        if(usernameOrEmail.contains("@")) {
            user.setEmail(usernameOrEmail);
        }else{
            user.setUsername(usernameOrEmail);
        }
        String password = request.getParameter("password");
        user = userManager.getUser(usernameOrEmail, password);
        String error = "";
        try {
            if (user != null && user.getActive() == 1) {
                session.setAttribute("username", user.getUsername());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("UserId", user.getUserId());
                session.setAttribute("user", user);
                response.sendRedirect("/Todo/");
            }else{
                error = "error";
                session.setAttribute("error", "invalid username or password");
                response.sendRedirect("/Todo/signin");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
