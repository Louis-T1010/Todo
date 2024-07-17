package com.example.todo.controller.tasks;

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
import java.util.List;

import static com.example.todo.dal.user.UserManager.sessionPassword;
import static com.example.todo.dal.user.UserManager.sessionUsername;

@WebServlet("duplicate")
public class DuplicateTaskServlet extends HttpServlet {
    User user = new User();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("reached duplicate servlet");
        TaskManager taskManager = new TaskManager();
        UserManager userManager = new UserManager();
        HttpSession session = request.getSession();
        user = userManager.getUser(sessionUsername, sessionPassword);
        List<Task> tasks = taskManager.getTasks(user.getUserId());
        String taskName = request.getParameter("taskName");
        int userId = user.getUserId();
        Task task = taskManager.getTask(taskName, userId);
        String category = task.getCategory();
        String error = null;
        for (Task taskObject : tasks) {
            String currTaskName = taskObject.getTaskName();
            if (currTaskName.equals(taskName + " (copy)")) {
                error = "This task has been already duplicated";
                session.setAttribute("error", error);
            }
        }
        session.setAttribute("error", error);
        if (error == null) {
            try {
                task = taskManager.duplicateTask(taskName, category, userId);
                if (task != null) {
                    System.out.println("Task duplicated successfully");

                } else {
                    System.out.println("Error duplicating task: " + taskName);
                }
            } catch (Exception e) {
                System.out.println("Error duplicating task: " + taskName);
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("task already duplicated");
        }
        response.sendRedirect("get-tasks");
    }
}
