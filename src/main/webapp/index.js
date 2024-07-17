document.getElementById("login-button").addEventListener("click", function(event) {
    var loginForm = document.getElementById("login-form");
    loginForm.action = "login";
    loginForm.method = "POST";
    console.log("Button clicked");
    validateEmail();
    loginForm.submit();
});

function validateEmail(){
    var email = document.getElementById("username").value;
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if(!emailRegex.test(email)){
        alert("Invalid Email Address");
    }
}
