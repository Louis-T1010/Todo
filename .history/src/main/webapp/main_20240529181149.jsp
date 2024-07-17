<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ page import="java.sql.*" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page isELIgnored="false" %>
    <%@ page import="com.example.todo.controller.tasks.Task" %>
    <link rel="stylesheet" href="css/main.css">
    <title>To-Do List</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
</head>
<body>
    <div class="today">
        <div id="todays-tasks-form">
            <h1 id = "hello">Hello ${username}!</h1>
            <form id = "refresh-form" action="get-tasks" method="get">
                <input type="button" id="refresh" name="refresh" value="Refresh">
            </form>
            <%
                int userId = (int) session.getAttribute("UserId");
                System.out.println("UserId: "+userId);
                List<Task> tasks = (List<Task>) session.getAttribute("tasks");
                List<Task> completedTasks = (List<Task>) session.getAttribute("completedTasks");

                if(completedTasks != null) {
                    System.out.println("Completed task list size:"+completedTasks.size());
                }else{
                    System.out.println("no completed tasks");
                }



            %>
            <% System.out.println("reached line 32"); %>
            <h1 id="today-head">Today's tasks</h1>
            <input type="button" id="add-task" value="+ add task"><br>
            <div class="incomplete">

                <h1>Incomplete</h1>
                <% System.out.println("reached line 38"); %>

                <% if (tasks != null) {
                    for (Task task : tasks) { %>
                        <form id="task-form" action="complete" method="post">
                            <form id="task-p-form" action="complete" method="post">
                                <p id ="task-p"><%= task.getTaskName() %></p>
                                <button id="complete-task" type="submit">Complete</button>
                                <input type="hidden" name="taskName" id="taskName" value="<%= task.getTaskName() %>">
                            </form>
                            <script>
                                var taskValue = "<%= task.getTaskName() %>";
                            </script>
                        </form>
                <%  }
                } else { %>
                    <p>No tasks available</p>
                <% } %>
                <% System.out.println("reached line 54"); %>

                <%
                    userId = (int) session.getAttribute("UserId");
                    tasks = (List<Task>) session.getAttribute("tasks");
                    completedTasks = (List<Task>) session.getAttribute("completedTasks");
                %>
            </div>
            <div id="completed">
                <% System.out.println("reached line 63"); %>
                <h1>Completed</h1>
                <% if (completedTasks != null){
                    for (Task completedTask : completedTasks) { %>
                    <form id="completed-task-form" action="undo" method="post">
                        <p id="task-p"><%= completedTask.getTaskName() %></p>
                        <button id="undo-task" type="submit">Undo</button>
                        <input type="hidden" name="taskName" id="taskName" value="<%= completedTask.getTaskName() %>">
                    </form>
                    <form id = "duplicate-task-form" action="duplicate" method="post">
                        <button id="duplicate" type="submit">Duplicate</button>
                        <input type="hidden" name="taskName" id="taskName" value="<%= completedTask.getTaskName() %>">
                    </form>
            <%    }
            } else { %>
                <p>No completed tasks</p>
            <% } %>
                <%
                    userId = (int) session.getAttribute("UserId");
                    tasks = (List<Task>) session.getAttribute("tasks");
                    completedTasks = (List<Task>) session.getAttribute("completedTasks");
                %>
                <% System.out.println("reached line 87"); %>
            </div>
            <script type="text/javascript">
                window.onload = function() {
                    var error = '<%= session.getAttribute("error") %>';
                    if (error && error !== "null") {
                        alert(error);
                        <% session.removeAttribute("error"); %>
                    }
                };
            </script>

    </div>
    </div>

        <form class="menu">
            <h1 id="menu-heading">Menu</h1>
            <div id="menu-seperator"></div>
            <ul id="menu-items">
                <li><input type="button" id="Today-button" name="Today" value=" ⭒   Tasks"></li>
            </ul>

            <div id="list-section">
                <% List<String> categories = (List<String>) session.getAttribute("categories"); %>
                <h1 id="list-heading">Lists</h1>
                <ul id="lists">
                    <% if (categories != null) {
                        for (String category : categories) { %>
                            <form id="filter-form-<%= category %>">
                            <input type="button" id="list-button" value="<%= category %>">
                            <input type="hidden" name="category" id="category" value="<%= category %>">
                        </form>
                <%  }
                } %>
                <form id="getAllTasks" action="get-tasks" method="get">
                    <input type="submit" id="list-button" value="all">
                    <input type="hidden" name="category" id="category" value="all">
                </form>
            </ul>
        </div>



            <div id="misc">
                <a href=/Todo/settings id="settings">☰ Settings</a>
                <p><a href="logout">⬅ Logout</a></p>
            </div>
        </form>

        <form class="task" id = "edit-task-form" style="display: none;">
            <h1 id="task-head">Task</h1>
            <div id="task-seperator"></div>
            <ul id="task-items">
                <input type="text" id="edit-task-name" name="edit-task-name" placeholder="task">
            </ul>

            <select id="create-task-category" name="create-task-category" style="display: none">
                    <%
                        if(categories != null){
                            for (String category : categories) {
                    %>
                    <option value="<%= category %>"><%= category %></option>
                    <%
                            }
                        }
                    %>
                    <option value="new" id="create-new-category-option">+ New Category</option>
            </select>

            <div class="list-dropdown">
                <select id="category-dropdown" name="category-dropdown" >
                    <%
                        if(categories != null){
                            for (String category : categories) {
                    %>
                    <option value="<%= category %>"><%= category %></option>
                    <%
                            }
                        }
                    %>
                    <option value="new" id="new-category-option">+ New Category</option>
                </select>
            </div>
            <label style="margin-left: 2%;" for="date">Due date</label>
            <input style="margin-left: 2%; margin-top: 7%;" type="date" id="date" name="date">
            
            <h3>Tags</h3>
            <label for="tags" id="ltags">Tags:</label>
            <input type="text" id="tags" name="tags" placeholder="Enter tags separated by commas">
            <div id="save-delete">
                <input type="button" id = "delete-task" name="delete" value="Delete Task">
                <input type="button" id = "save-task" save name="save" value="Save Task">
            </div>
        </form>

    </div>
    <div id = "calender">
        
    </div>
</body>
<script src="main.js"></script>
</html>

