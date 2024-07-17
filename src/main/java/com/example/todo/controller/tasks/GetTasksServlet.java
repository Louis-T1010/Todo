package com.example.todo.controller.tasks;

import com.example.todo.controller.user.User;
import com.example.todo.dal.user.UserManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

import static com.example.todo.dal.user.UserManager.sessionPassword;
import static com.example.todo.dal.user.UserManager.sessionUsername;

@WebServlet("/get-tasks")
public class GetTasksServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getParameter("usernameOrEmail"));
        HttpSession session = request.getSession();
        UserManager userManager = new UserManager();
        User user = userManager.getUser(sessionUsername, sessionPassword);
        int userId = user.getUserId();
        List<String> categories = Task.getCategories(userId);
        List<Task> incompleteTasks = getTasksByCompletion(false, userId);
        List<Task> completedTasks = getTasksByCompletion(true, userId);
        setAttributes(session, request, categories, incompleteTasks, completedTasks);
        response.sendRedirect("main");
    }


    List<Task> getTasksByCompletion(boolean getComplete, int userId){
        if(getComplete){
            return Task.getTasksByCompletion(true, userId);
        }else {
            return Task.getTasksByCompletion(false, userId);
        }
    }

    void setAttributes(HttpSession session, HttpServletRequest request, List<String> categories, List<Task> incompleteTasks, List<Task> completedTasks)  {
        request.setAttribute("categories", categories);
        request.setAttribute("tasks", incompleteTasks);
        request.setAttribute("completedTasks", completedTasks);

        session.setAttribute("categories", categories);
        session.setAttribute("tasks", incompleteTasks);
        session.setAttribute("completedTasks", completedTasks);
    }
}

