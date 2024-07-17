package com.example.todo.controller;

import com.example.todo.controller.tasks.Task;
import com.example.todo.controller.user.User;
import com.example.todo.dal.tasks.TaskManager;
import com.example.todo.dal.user.UserManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.todo.dal.user.UserManager.*;

@WebServlet("/")
public class RootServlet extends HttpServlet {
    TaskManager taskManager = new TaskManager();
    User user = new User();

    int userId;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Root servlet accessed");
        HttpSession session = request.getSession(false);
        UserManager userManager = new UserManager();
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("/Todo/signin");
        }else{
            System.out.println("Session exists");
            User user = userManager.getUser(sessionUsername, sessionPassword);
            userManager.getUser(sessionUsername, sessionPassword);
            userId = user.getUserId();
            System.out.printf("User Id = %d%n", userId);
            setLoginAttributes(request);
            response.sendRedirect("main");
        }
        System.out.printf("Root Servlet: Meg used=%dM%n", (Runtime.getRuntime().totalMemory() -
                Runtime.getRuntime().freeMemory()) / (1000 * 1000));
    }

    public List<String> getCategories(int userId) {
        List<String> categories = new ArrayList<>();
        List<Task> tasks = taskManager.getTasks(userId);
        for(Task task : tasks){
            if(!categories.contains(task.getCategory())){
                categories.add(task.getCategory());
            }
        }
        return categories;
    }



    public void setLoginAttributes(HttpServletRequest request) {
        System.out.println("Set login attributes | ");
        HttpSession session = request.getSession();
        session.setAttribute("username", sessionUsername);
        session.setAttribute("email", sessionEmail);
        session.setAttribute("UserId", userId);
        List<Task> incompleteTaskNames = Task.getTasksByCompletion(false, userId);

        List<String> categories =  getCategories(userId);


        List<Task> completedTasks = Task.getTasksByCompletion(true, userId);


        //request attributes
        request.setAttribute("username", sessionUsername);
        request.setAttribute("email", sessionEmail);
        request.setAttribute("categories", categories );
        request.setAttribute("tasks", incompleteTaskNames);
        request.setAttribute("completedTasks", completedTasks);
        request.setAttribute("UserId", userId);
        request.setAttribute("currentUsername", sessionUsername);
        request.setAttribute("currentEmail", sessionEmail);
        request.setAttribute("currentPassword", user.getPassword());

        //session attributes
        session.setAttribute("categories", categories);
        session.setAttribute("tasks", incompleteTaskNames);
        session.setAttribute("completedTasks", completedTasks);
        session.setAttribute("UserId", userId);
        session.setAttribute("currentUsername", sessionUsername);
        session.setAttribute("currentEmail", sessionEmail);
        session.setAttribute("currentPassword", user.getPassword());

    }

}
