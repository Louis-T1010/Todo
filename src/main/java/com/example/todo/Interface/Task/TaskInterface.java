package com.example.todo.Interface.Task;

import com.example.todo.controller.tasks.Task;

import java.util.List;

public interface TaskInterface {

    Task addTask(String taskName, String category, int userId);

    Task duplicateTask(String taskName, String category, int userId);

    boolean deleteTask(String taskIdStr, int userId);


    List<Task> getTasks(int userId);

    Task getTask(String taskName, int userId);

    Task undoTask(String taskName, int userId);

    Task taskComplete(String taskName, int userId);


    Task editTask(String newTaskName, String taskName, String category, int userId);

}
