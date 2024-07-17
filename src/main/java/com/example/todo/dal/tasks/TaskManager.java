package com.example.todo.dal.tasks;

import com.example.todo.Interface.Task.TaskInterface;
import com.example.todo.controller.tasks.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class TaskManager implements TaskInterface {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/UserDB", "root", "hyena");
    }

    @Override
    public Task getTask(String taskName, int userId){
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks WHERE task_name = '%s' AND user_id = %d".formatted(taskName, userId))
        ){
            if(resultSet.next()) {
                return extractTaskFromResultSet(resultSet);
            }
            return null;
        }catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }



    @Override
    public Task addTask(String taskName, String category, int userId){
        try(Statement statement = getConnection().createStatement();
        ){
            int i = statement.executeUpdate("INSERT INTO tasks (user_id, task_name, category, date_created) VALUES (%d, '%s', '%s', NOW())".formatted(userId, taskName, category));
            if (i > 0) {
                try(ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks WHERE user_id = %d AND task_name = '%s' AND category = '%s'".formatted(userId, taskName, category))){
                    return extractTaskFromResultSet(resultSet);
                }
            }
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Task duplicateTask(String taskName, String category, int userId){
        try(Statement statement = getConnection().createStatement();
        ){
            int i = statement.executeUpdate("INSERT INTO tasks (user_id, task_name, category, date_created) VALUES (%d, '%s', '%s', NOW())".formatted(userId, taskName+" (copy)" , category));
            if (i > 0) {
                try(ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks WHERE user_id = %d AND task_name = '%s' AND category = '%s'".formatted(userId, taskName+" (copy)" , category))
                ) {
                    if (resultSet.next()) {
                        return extractTaskFromResultSet(resultSet);
                    }
                }
            }
            return null;
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteTask(String taskName, int userId) {
        boolean success = false;
        try (Statement statement = getConnection().createStatement()) {
            int i = statement.executeUpdate("DELETE FROM tasks WHERE task_name = '%s' AND user_id = %d".formatted(taskName, userId));
            if (i > 0) {
                success = true;
                System.out.println("Task deleted successfully");
            }
            return success;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> getTasks(int userId){
        List<Task> tasks = new ArrayList<>();
        try(Statement getTaskStatement = getConnection().createStatement();
            ResultSet resultSet = getTaskStatement.executeQuery("SELECT * FROM tasks WHERE user_id = %d".formatted(userId))
        ){
            while(resultSet.next()){
                Task task = extractTaskFromResultSet(resultSet);
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundError in getTasks method");
            throw new RuntimeException(e);
        }
    }



    @Override
    public Task undoTask(String taskName, int userId) {
        try(
            Statement undoTaskStatement = getConnection().createStatement();
        ){
            int i = undoTaskStatement.executeUpdate("UPDATE tasks SET complete = 0 WHERE task_name = '%s' AND user_id = %d AND complete = 1".formatted(taskName, userId));
            if(i>0) {
                try (ResultSet resultSet = undoTaskStatement.executeQuery("SELECT * FROM tasks WHERE task_name = '%s' AND user_id = %d AND complete = 0".formatted(taskName, userId))) {
                    if (resultSet.next()) {
                        return extractTaskFromResultSet(resultSet);
                    }
                }
            }
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error undoing task: "+taskName);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Task taskComplete(String taskName, int userId)  {
        try(
            Statement completeStatement = getConnection().createStatement();
        ){
            int i = completeStatement.executeUpdate("UPDATE tasks SET complete = 1 WHERE task_name = '%s' AND user_id = %d AND complete = 0".formatted(taskName, userId));
            if (i>0) {
                try (ResultSet resultSet = completeStatement.executeQuery("SELECT * FROM tasks WHERE task_name = '%s' AND user_id = %d AND complete = 1".formatted(taskName, userId))
                ) {
                    if (resultSet.next()) {
                        return extractTaskFromResultSet(resultSet);
                    }
                }
            }
            return null;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error completing task: "+taskName);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Task editTask(String newTaskName, String taskName, String category, int userId) {
        try(
            Statement editTaskStatement = getConnection().createStatement();
        ) {
            int i = editTaskStatement.executeUpdate("UPDATE tasks SET task_name = '%s', category = '%s' WHERE task_name = '%s' AND user_id = %d".formatted(newTaskName, category, taskName, userId));
            if(i > 0 ) {
                try(ResultSet resultSet = editTaskStatement.executeQuery("SELECT * FROM tasks WHERE task_name = '%s' AND user_id = %d".formatted(newTaskName, userId))) {
                    if (resultSet.next()) {
                        return extractTaskFromResultSet(resultSet);
                    }
                }
                return null;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Failed to edit task: "+e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    private static Task extractTaskFromResultSet(ResultSet resultSet) throws SQLException {
        Task task = new Task();
        task.setTaskName(resultSet.getString("task_name"));
        task.setCategory(resultSet.getString("category"));
        task.setId(resultSet.getInt("id"));
        task.setDateCreated(resultSet.getDate("date_created").toString());
        task.setComplete(resultSet.getInt("complete"));
        return task;
    }

}
