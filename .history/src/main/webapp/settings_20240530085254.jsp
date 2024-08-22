<!DOCTYPE html>
<html>
<head>
    <title>Settings page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="css/settings.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dosis:wght@200..800&display=swap" rel="stylesheet">
</head>
<body>
    <h1 id="heading">Settings</h1>
<form action="delete" method="post">
    <input style="background-color: red;" type="submit" id="delete-user" value="Delete Account">
</form>    
<form id="change-username-form" action="ChUsername" method="post">
    <input type="button" id="change-username-btn" value="Update Username">
</form>
<form id="change-email-form" action="ChEmail" method="post">
    <input type="button" id="change-email-btn" value="Update Email">
</form>
<form id="change-password-form" action="ChPassword" method="post">
    <input type="button" id="change-password-btn" value="Change Password">
</form>
<%
    String currentUsername = (String) session.getAttribute("currentUsername");
    String currentEmail = (String) session.getAttribute("currentEmail");
%>


<input type="hidden" id="cUsername" value="<%= currentUsername %>" />
<input type="hidden" id="cEmail" value="<%= currentEmail %>" />

<div id="misc">
    <a href=main.jsp id="back">☰ Back</a>
    <p><a href="logout">⬅ Logout</a></p>
</div>

</body>
<script src="settings.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    window.onload = function() {
        var message = '<%= session.getAttribute("message") %>';
        if (message == "password error"){
            alert("entered password does not match the users password");
            setTimeout(function(){
                window.location.href = "settings"
            }, 2000)
        }else if (message == "success"){
            alert("success, please log in again");
            setTimeout(function(){
                window.location.href = "signin"
            }, 2000)
        }else if (message == "fail"){
            alert("An error has occurred");
            setTimeout(function(){
                window.location.href = "settings"
            }, 2000)
        }
    }
</script>
</html>