package com.example.todo.controller.tasks;

import com.example.todo.dal.tasks.TaskManager;

import java.util.ArrayList;
import java.util.List;

public class Task {
    String taskName;
    String category;
    int complete;
    int id;
    String dateCreated;

    static TaskManager taskManager = new TaskManager();
    public Task(String taskName, String category, int complete, int id, String dateCreated) {
        this.taskName = taskName;
        this.category = category;
        this.complete = complete;
        this.id = id;
        this.dateCreated = dateCreated;
    }

    public Task(){
    }

    static List<Task>  filterTasks(boolean filterCompleted, String category, int userId){
        List<Task> tasks = taskManager.getTasks(userId);
        List<Task> filteredTasks = new ArrayList<>();
        for(Task task: tasks){
            if((filterCompleted && task.getComplete()==1 || !filterCompleted && task.getComplete()==0) && task.getCategory().equals(category)){
                Task filteredTask = new Task();
                filteredTask.setTaskName(task.getTaskName());
                filteredTask.setCategory(task.getCategory());
                filteredTask.setId(task.getId());
                filteredTask.setDateCreated(task.getDateCreated());
                filteredTasks.add(filteredTask);
            }
        }
        return filteredTasks;
    }



    public static List<Task> getTasksByCompletion(boolean getCompleted, int userId){
        List<Task> tasks = taskManager.getTasks(userId);
        List<Task> filteredTasks = new ArrayList<>();
        for(Task task : tasks){
            if((getCompleted && task.getComplete()==1) || (!getCompleted && task.getComplete() == 0)){
                Task filteredTask = new Task();
                filteredTask.setTaskName(task.getTaskName());
                filteredTask.setCategory(task.getCategory());
                filteredTask.setId(task.getId());
                filteredTask.setDateCreated(task.getDateCreated());
                filteredTasks.add(filteredTask);
            }
        }
        return filteredTasks;
    }


    public static List<String> getCategories(int userId){
        List<Task> tasks = taskManager.getTasks(userId);
        List<String> categories = new ArrayList<>();
        for (Task task : tasks) {
            if (!categories.contains(task.getCategory())) {
                categories.add(task.getCategory());
            }
            System.out.println("category: "+task.getCategory());
        }

        return categories;
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
