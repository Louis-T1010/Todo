<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
  <title>JSP - Hello World</title>
</head>

<body>
    <%
        String message = "hello there!!!";
        out.println("<p>" + message + "</p>");
    %>
    <%
        for(int i=1;i<=5;i++){
            out.println("<p>Loop: "+i+"</p>");
        }

    %>


    <a href="hello-servlet">Click here to invoke servlet</a>

    <form action="Register" method="post">
        <input type="text" name="username" placeholder="username">
        <input type="text" name="email" placeholder="email">
        <input type="password" name="password" placeholder="password">
        <input type="submit" value="Submit">
    </form>
    <br>

    <form action="Login" method="post">
        <h1>login</hq>
        <input type="text" name="username or email" placeholder="username">
        <input type="password" name="password" placeholder="password">
        <input type="submit" value="Submit">
    </form>


    <%
        String username = request.getParameter("username");
    %>

    <p>Username: <%= username %></p>

</body>


</html>
