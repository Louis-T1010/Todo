package com.example.todo.controller.tasks;

import com.example.todo.controller.user.User;
import com.example.todo.dal.tasks.TaskManager;
import com.example.todo.dal.user.UserManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.todo.dal.user.UserManager.sessionPassword;
import static com.example.todo.dal.user.UserManager.sessionUsername;

@WebServlet("complete")
public class TaskCompleteServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserManager userManager = new UserManager();
        TaskManager taskManager = new TaskManager();
        User user = userManager.getUser(sessionUsername, sessionPassword);
        try {
            int userId = user.getUserId();
            debug(userId, taskManager, request);
            List<String> completedTasks= getTaskByCompletion(true, userId);
            List<String> tasks = getTaskByCompletion(false, userId);
            setAttributes(session,request, tasks, completedTasks);
        } catch (Exception e){
            e.printStackTrace();
        }
        response.sendRedirect("/Todo/get-tasks");
    }

    List<String> getTaskByCompletion(boolean getComplete, int userId){
        if(getComplete){
            List<Task> completedTasks = Task.getTasksByCompletion(true, userId);
            List<String> completedTaskStrings = new ArrayList<>();
            for(Task task : completedTasks){
                completedTaskStrings.add(task.getTaskName());
            }
            return completedTaskStrings;
        }else {
            List<Task> incompleteTasks = Task.getTasksByCompletion(false, userId);
            List<String> incompleteTaskStrings = new ArrayList<>();
            for(Task task : incompleteTasks){
                incompleteTaskStrings.add(task.getTaskName());
            }
            return incompleteTaskStrings;
        }
    }

    public void setAttributes(HttpSession session, HttpServletRequest request, List<String> tasks, List<String> completedTasks){
        request.setAttribute("tasks", tasks);
        request.setAttribute("completedTasks", completedTasks);
        session.setAttribute("tasks", tasks);
        session.setAttribute("completedTasks", completedTasks);
    }

    public void debug(int userId, TaskManager taskManager, HttpServletRequest request) {
        String taskName = request.getParameter("taskName");
        System.out.println("Task name: " + taskName);
        System.out.println("User id: " + userId);
        Task cTask = taskManager.taskComplete(taskName, userId);
        if (cTask != null) {
            System.out.println("Task completed successfully");
        }
    }

}
