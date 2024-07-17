<!DOCTYPE html>
<html>
<head>
    <title>Signup Page</title>
    <link rel="stylesheet" href="css/signup.css">
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
                    setTimeout(function() {
                        window.location.href = "signin";
                    }, 2000);
                }
            };
        </script>

    </form>
</body>
</html>