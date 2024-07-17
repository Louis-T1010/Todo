package com.example.todo.controller.user;


import com.example.todo.dal.user.UserManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserManager userManager = new UserManager();
        User user = userManager.userRegister(request.getParameter("username"),request.getParameter("email"),request.getParameter("password"));
        boolean success = false;
        try(PrintWriter out = response.getWriter()){
            if (user == null) {
                session.setAttribute("success", success);
                response.sendRedirect("signup");
            } else {
                success = true;
                session.setAttribute("success", success);
                response.sendRedirect("signup");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
