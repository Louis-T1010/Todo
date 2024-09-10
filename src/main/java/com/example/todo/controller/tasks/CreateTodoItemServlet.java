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

@WebServlet("/create")
public class CreateTodoItemServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TaskManager taskManager = new TaskManager();
        UserManager userManager = new UserManager();
        User user = userManager.getUser(sessionUsername, sessionPassword);
        int userId = user.getUserId();
        String category = request.getParameter("create-task-category");
        System.out.println("category: "+category);
        if(category == null || category.equals("+ New Category")||category.equals("new")){
            category = "uncategorized";
        }
        try{
            Task task = taskManager.addTask(request.getParameter("task-name-input"), category, userId);
            if(task != null){
                System.out.println("Task added successfully");
            }
        }catch (Exception e){
            response.getWriter().println("Error adding task");
        }

        response.sendRedirect("/Todo/get-tasks");
    }
}