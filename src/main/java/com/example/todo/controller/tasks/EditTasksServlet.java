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

@WebServlet("/edit")
public class EditTasksServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserManager userManager = new UserManager();
        User user = userManager.getUser(sessionUsername, sessionPassword);
        TaskManager taskManager = new TaskManager();
        Task task = taskManager.getTask(request.getParameter("task-name-hidden-input"), user.getUserId());
        String taskName = task.getTaskName();
        String newTaskName = request.getParameter("edit-task-name");
        int userId = user.getUserId();
        System.out.println("user Id in edit servlet: "+userId);
        System.out.println("task hidden input on edit servlet: "+taskName);
        System.out.println(newTaskName);
        task = taskManager.editTask(newTaskName, taskName, request.getParameter("category-dropdown"), userId);
        if(task != null){
            System.out.println("Task edited successfully");
        }
        response.sendRedirect("/Todo/get-tasks");
    }
}
