package com.example.todo.controller.tasks;

import com.example.todo.controller.user.User;
import com.example.todo.dal.user.UserManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

import static com.example.todo.dal.user.UserManager.*;

@WebServlet("filter")
public class FilterTasksServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserManager userManager = new UserManager();
        User user = userManager.getUser(sessionUsername, sessionPassword);
        String category = request.getParameter("category");
        System.out.printf("category: %s%n", category);
        int userId = user.getUserId();
        System.out.printf("category as set in FilterTasksServlet: %s%n", category);
        setAttributes(request, category, userId);
        response.sendRedirect("main");
    }



    List<Task> filterTasks(boolean filterCompleted, String category, int userId){
        if(filterCompleted) {
            return Task.filterTasks(true, category, userId);
        }
        else {
            return Task.filterTasks(false, category, userId);
        }
    }


    void setAttributes(HttpServletRequest request, String category, int userid){
        HttpSession session = request.getSession();
        List<Task> completedTasks = filterTasks(true, category, userid);
        List<Task> incompleteTasks = filterTasks(false, category, userid);
        request.setAttribute("completedTasks", completedTasks);
        session.setAttribute("completedTasks", completedTasks);
        request.setAttribute("tasks", incompleteTasks);
        session.setAttribute("tasks", incompleteTasks);
    }

}
