<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/index.css">
        <title>Todo Website</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@200..800&display=swap" rel="stylesheet">
        <title>Todo Website</title>

    </head>
    <body>
        <div id="heading">
            <h1 id = "title">My Simple Todo Website</h1>
        </div>

        <form id ="login-form" action="login" method="post">
            <div class="container">
                <label for="usernameOrEmail"><b>Username or Email</b></label>
                <input id="username"  type="text" placeholder="Enter Username" name="usernameOrEmail" required>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>

            </div>
            <div id="signup-options">
                <p class="options">

                    <input id="login-button" style="text-decoration: none; color: black;" type="submit" value="Login">
                    <a id="signup-button" style="text-decoration: none; color: black;" id="signup" href="signup">Signup</a>
                </p>
            </div>
        </form>
        <script type="text/javascript">
            window.onload = function() {
                var error = '<%= session.getAttribute("error") %>';
                if (error && error !== "null") {
                    alert(error);
                    <% session.removeAttribute("error"); %> 
                }
            };
        </script>
    </body>

</html>