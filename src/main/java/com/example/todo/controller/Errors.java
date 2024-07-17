package com.example.todo.controller;

import com.example.todo.controller.user.ChangeEmailServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class Errors {
    public static HttpSession getSession(HttpServletRequest request){
        return request.getSession();
    }

    public static void passwordError(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String message = "password error";
        HttpSession session  = getSession(request);
        session.setAttribute("message", message);
        response.sendRedirect("settings");
    }

    public static void success(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String message = "success";
        HttpSession session  = getSession(request);
        session.setAttribute("message", message);
        response.sendRedirect("settings");
    }

    public static void fail(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String message = "fail";
        getSession(request).setAttribute("message", message);
        response.sendRedirect("settings");
    }
}
