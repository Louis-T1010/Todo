package com.example.todo.controller.tasks;

import com.example.todo.controller.user.User;
import com.example.todo.dal.tasks.TaskManager;
import com.example.todo.dal.user.UserManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;

import java.io.IOException;

import static com.example.todo.dal.user.UserManager.sessionPassword;
import static com.example.todo.dal.user.UserManager.sessionUsername;

@WebServlet("/delete-task")
public class DeleteTaskServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TaskManager taskManager = new TaskManager();
        UserManager userManager = new UserManager();
        User user = userManager.getUser(sessionUsername, sessionPassword);
        boolean success = taskManager.deleteTask(request.getParameter("task-name-hidden-input"), user.getUserId());
        response.sendRedirect("/Todo/get-tasks");
    }
}
