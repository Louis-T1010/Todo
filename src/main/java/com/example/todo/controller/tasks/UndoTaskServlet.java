package com.example.todo.controller.tasks;

import com.example.todo.controller.user.User;
import com.example.todo.dal.tasks.TaskManager;
import com.example.todo.dal.user.UserManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;


import static com.example.todo.dal.user.UserManager.sessionPassword;
import static com.example.todo.dal.user.UserManager.sessionUsername;

@WebServlet("undo")
public class UndoTaskServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManager userManager = new UserManager();
        TaskManager taskManager = new TaskManager();
        String taskName = request.getParameter("taskName");
        User user = userManager.getUser(sessionUsername, sessionPassword);
        int userId = user.getUserId();
        Task undoneTask = taskManager.undoTask(taskName, userId);

        if (undoneTask != null) {
            System.out.println("task undone");
        }
        response.sendRedirect("/Todo/get-tasks");

    }



}
