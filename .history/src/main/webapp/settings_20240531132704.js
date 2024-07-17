document.getElementById("change-username-btn").addEventListener("click", function(){
    var block = document.createElement("form");
    block.style.backgroundColor = "rgb(235, 235, 235)";
    block.style.position = "absolute";
    block.action = "ch-username";
    block.method = "post";
    block.style.height = "45%";
    block.style.width = "25%";
    block.style.left = "38%";
    block.style.top = "20%";
    block.style.borderRadius = "10px";
    block.style.border = "1px solid black";
    block.style.zIndex = "1000";
    block.style.backgroundColor = "rgb(236, 253, 253)";


    var heading = document.createElement("h2");
    heading.textContent = "Change Username";
    heading.style.position = "absolute";
    heading.style.left = "30%";
    heading.style.top = "5%";

    var currUsernameLabel = document.createElement("label");
    currUsernameLabel.textContent = "Current username: ";
    currUsernameLabel.style.position = "absolute";
    currUsernameLabel.style.left = "10%";
    currUsernameLabel.style.top = "25%";



    let currUsernameValue = document.getElementById('cUsername').value;

    currUsernameLabel.textContent += currUsernameValue;
    currUsernameLabel.style.fontSize = "20px";
    currUsernameLabel.style.fontWeight = "200";
    currUsernameLabel.style.left = "13%";

    var chUserName = document.createElement("input");
    chUserName.type="text";
    chUserName.id="change-username";
    chUserName.placeholder = "Enter new username or leave unchanged";
    chUserName.name="change-username";
    chUserName.style.position = "absolute";
    chUserName.style.left = "13%";
    chUserName.style.top = "35%";
    chUserName.style.width = "72%";
    chUserName.style.height = "15%";
    chUserName.style.textAlign = "center";

    var currPassword = document.createElement("input");
    currPassword.type="password";
    currPassword.id="current-password";
    currPassword.name="current-password";
    currPassword.style.position = "absolute";
    currPassword.style.left = "13%";
    currPassword.style.top = "55%";
    currPassword.style.width = "72%";
    currPassword.style.height = "15%";
    currPassword.placeholder = "Enter current password";
    currPassword.style.textAlign = "center";
    currPassword.type = "password";

    var submit = document.createElement("input");
    submit.type="submit";
    submit.value="Submit";
    submit.id="submit-settings";
    submit.style.position = "absolute";
    submit.style.left = "55%";
    submit.style.top = "75%";
    submit.style.width = "20%";
    submit.style.height = "15%";
    submit.style.backgroundColor = "rgb(255, 255, 188)";

    var cancel = document.createElement("input");
    cancel.type="button";
    cancel.value="Cancel";
    cancel.id="cancel-settings";
    cancel.style.position = "absolute";
    cancel.style.left = "25%";
    cancel.style.top = "75%";
    cancel.style.width = "20%";
    cancel.style.height = "15%";
    cancel.style.backgroundColor = "rgb(255, 255, 188)";

    block.appendChild(heading);
    block.appendChild(currUsernameLabel);
    block.appendChild(currPassword);
    block.appendChild(cancel);
    block.appendChild(submit);
    block.appendChild(chUserName);
    document.body.appendChild(block);

    cancel.addEventListener("click", function() {
        document.body.removeChild(block);
    });

    block.addEventListener("submit", function(event) {
        block.submit();
    });
});


document.getElementById("change-email-btn").addEventListener("click", function(){
    var block = document.createElement("form");
    var currEmail = document.createElement("p");
    fetch("/login")
    .then(response => response.json())
    .then(data => {
        currEmail.textContent = "Current email: " + data.currentEmail;
    });
    block.action = "ch-email";
    block.method = "post";
    block.style.backgroundColor = "rgb(235, 235, 235)";
    block.style.position = "absolute";
    block.style.height = "45%";
    block.style.width = "25%";
    block.style.left = "38%";
    block.style.top = "20%";
    block.style.borderRadius = "10px";
    block.style.border = "1px solid black";
    block.style.zIndex = "1000"
    block.style.backgroundColor = "rgb(236, 253, 253)";


    var currEmailLabel = document.createElement("label");
    var currEmailValue = document.getElementById('cEmail').value;
    currEmailLabel.textContent = "Current email: " + currEmailValue;


    var chEmail = document.createElement("input");
    var heading = document.createElement("h2");
    var currEmail = document.createElement("p");

    currEmailLabel.style.position = "absolute";
    currEmailLabel.style.left = "13%";
    currEmailLabel.style.top = "25%";
    currEmailLabel.style.fontSize = "20px";
    currEmailLabel.style.fontWeight = "200";

    
    heading.textContent = "Change Email";
    chEmail.type="text";
    chEmail.id="change-email";
    chEmail.placeholder = "Enter new email or leave unchanged";
    chEmail.name="change-email"
    chEmail.style.position = "absolute";
    chEmail.style.left = "13%";
    chEmail.style.top = "35%";
    chEmail.style.width = "72%";
    chEmail.style.height = "15%";
    chEmail.style.textAlign = "center";

    var currPassword = document.createElement("input");
    currPassword.type="password";
    currPassword.id="current-password";
    currPassword.name="current-password";
    currPassword.style.position = "absolute";
    currPassword.style.left = "13%";
    currPassword.style.top = "55%";
    currPassword.style.width = "72%";
    currPassword.style.height = "15%";
    currPassword.placeholder = "Enter current password";
    currPassword.style.textAlign = "center";
    currPassword.type = "password";


    heading.style.position = "absolute";
    heading.style.left = "30%";
    heading.style.top = "5%";

    var submit = document.createElement("input");
    submit.type="submit";
    submit.value="Submit";
    submit.id="submit-settings";
    submit.style.position = "absolute";
    submit.style.left = "55%";
    submit.style.top = "75%";
    submit.style.width = "20%";
    submit.style.height = "15%";
    submit.style.backgroundColor = "rgb(255, 255, 188)";

    var cancel = document.createElement("input");
    cancel.type="button";
    cancel.value="Cancel";
    cancel.id="cancel-settings";
    cancel.style.position = "absolute";
    cancel.style.left = "25%";
    cancel.style.top = "75%";
    cancel.style.width = "20%";
    cancel.style.height = "15%";
    cancel.style.backgroundColor = "rgb(255, 255, 188)";
    
    block.appendChild(currEmailLabel);
    block.appendChild(cancel);
    block.appendChild(submit);
    block.appendChild(heading);
    block.appendChild(chEmail);
    block.appendChild(currPassword);
    document.body.appendChild(block);

    cancel.addEventListener("click", function() {
        document.body.removeChild(block);
    });

    block.addEventListener("submit", function(event) {
        block.submit();
    })

})

document.getElementById("change-password-btn").addEventListener("click", function(){
    var block = document.createElement("form");
    var currPassword = document.createElement("input");

    block.action = "ch-password";
    block.method = "post";
    block.classList
    block.style.backgroundColor = "rgb(235, 235, 235)";
    block.style.position = "absolute";
    block.style.height = "25%";
    block.style.width = "25%";
    block.style.left = "38%";
    block.style.top = "20%";
    block.style.borderRadius = "10px";
    block.style.border = "1px solid black";
    block.style.zIndex = "1000"
    block.style.backgroundColor = "rgb(236, 253, 253)";


    var chPassword = document.createElement("input");
    var heading = document.createElement("h2");
    heading.textContent = "Change Password";
    chPassword.type="password";
    chPassword.id="change-password";
    chPassword.placeholder = "Enter new password or leave unchanged";
    chPassword.name="change-password"
    chPassword.style.position = "absolute";
    chPassword.style.left = "14%";
    chPassword.style.top = "50%";
    chPassword.style.width = "70%";
    chPassword.style.height = "10%";
    chPassword.style.textAlign = "center";


    currPassword.type="password";
    currPassword.id="cPassword";
    currPassword.placeholder = "Current Password";
    currPassword.name="cPassword"
    currPassword.style.position = "absolute";
    currPassword.style.left = "14%";
    currPassword.style.top = "30%";
    currPassword.style.width = "70%";
    currPassword.style.height = "10%";
    currPassword.style.textAlign = "center";

    heading.style.position = "absolute";
    heading.style.left = "30%";
    heading.style.top = "5%";

    var submit = document.createElement("input");
    submit.type="submit";
    submit.value="Submit";
    submit.id="submit-settings";
    submit.style.position = "absolute";
    submit.style.left = "55%";
    submit.style.top = "75%";
    submit.style.width = "20%";
    submit.style.height = "15%";
    submit.style.backgroundColor = "rgb(255, 255, 188)";

    var cancel = document.createElement("input");
    cancel.type="button";
    cancel.value="Cancel";
    cancel.id="cancel-settings";
    cancel.style.position = "absolute";
    cancel.style.left = "25%";
    cancel.style.top = "75%";
    cancel.style.width = "20%";
    cancel.style.height = "15%";
    cancel.style.backgroundColor = "rgb(255, 255, 188)";

    block.appendChild(currPassword);
    block.appendChild(cancel);
    block.appendChild(submit);
    block.appendChild(heading);
    block.appendChild(chPassword);
    document.body.appendChild(block);

    fetch("/Login")
    .then(response => response.json())
    .then(data => {
        currentPass =  data.currentPassword;
        if(currentPass != chPassword){
            currPassword.value = currentPass;
        }
    })
    
    cancel.addEventListener("click", function() {
        document.body.removeChild(block);
    });

    block.addEventListener("submit", function(event) {
        block.submit();
    })

})

document.getElementById("delete-user").addEventListener("click", function(){
    var block = document.createElement("form");
    block.action = "delete";
    block.method = "post";
    block.style.backgroundColor = "rgb(235, 235, 235)";
    block.style.position = "absolute";
    block.style.height = "30%";
    block.classList.add("pop-up");
    block.style.width = "25%";
    block.style.left = "38%";
    block.style.top = "20%";
    block.style.borderRadius = "10px";
    block.style.border = "1px solid black";
    block.style.zIndex = "1000"
    block.style.backgroundColor = "rgb(236, 253, 253)";

    var sure = document.createElement("p");
    sure.textContent = "Are you sure you want to delete your account this cannot be undone?";
    sure.style.position = "absolute";
    sure.style.left = "7%";
    sure.style.top = "30%";

    var currPassword = document.createElement("input");
    currPassword.type="password";
    currPassword.id="cPassword";
    currPassword.placeholder = "Current Password";
    currPassword.name="cPassword"
    currPassword.style.position = "absolute";
    currPassword.style.left = "15%";
    currPassword.style.top = "48%";
    currPassword.style.width = "70%";
    currPassword.style.height = "10%";
    currPassword.style.textAlign = "center";

    var yes = document.createElement("input");
    yes.type="submit";
    yes.value="Yes";
    yes.id="yes-delete";
    yes.style.position = "absolute";
    yes.style.left = "55%";
    yes.style.top = "75%";
    yes.style.width = "20%";
    yes.style.height = "15%";
    yes.style.textAlign = "center";
    yes.style.backgroundColor = "#fc837b";


    var no = document.createElement("input");
    no.type="button";
    no.value="No";
    no.id="no-delete";
    no.style.position = "absolute";
    no.style.left = "25%";
    no.style.top = "75%";
    no.style.width = "20%";
    no.style.height = "15%";
    no.style.textAlign = "center";
    no.style.backgroundColor = "rgb(255, 255, 188)";
    no.addEventListener("click", function() {
        document.body.removeChild(block);
    })



    var heading = document.createElement("h2");
    heading.textContent = "Delete User";
    heading.style.position = "absolute";
    heading.style.left = "38%";
    heading.style.top = "5%";


    block.appendChild(sure);
    block.appendChild(heading);
    block.appendChild(currPassword);
    block.appendChild(no);
    block.appendChild(yes);

    document.body.appendChild(block);

})
