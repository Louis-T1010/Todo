<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<html>
<head>
    <title>Signup Page</title>
    <link rel="stylesheet" href="css/signup.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@200..800&display=swap" rel="stylesheet">
</head>
<body>
    <h2>Signup</h2>
    <form action="register" method="post">
        <div class="container">
            <label for="email"><b>Email</b></label>
            <input type="email" id ="email" name ="email" placeholder="Enter Email" name="email" required>

            <label for="username"><b>Username</b></label>
            <input type="text" id ="username" name ="username" placeholder="Enter Username" name="username" required>

            <label for="password"><b>Password</b></label>
            <input type="password" id ="password" name ="password" placeholder="Enter Password" name="password" required>

            <input id="button" type="submit" value="Signup">
        </div>
        <script type="text/javascript">
            window.onload = function() {
                var success = '<%= session.getAttribute("success") %>';
                if (success && success  == "false") {
                    alert("username or email already exists");
                    <% session.removeAttribute("success"); %>
                }else if (success && success  == "true") {
                    alert("signup successful, you will be redirected to login page");
                    <% session.removeAttribute("success"); %>
                    window.location.href = "signin";
                }
            };
        </script>

    </form>
    <a href=signin id="back" style="text-decoration: none; position: relative; top: 300px; left: 2%;">↪ Back</a>
</body>
</html>