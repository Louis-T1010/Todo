var i = 0;

    var todaysTasks = document.getElementById("todays-tasks");


document.getElementById("add-task").addEventListener("click", function(){
    var form = document.getElementById("todays-tasks-form");
    var refreshForm = document.getElementById("refresh-form");
    refreshForm.action = "get-tasks";
    refreshForm.method = "GET";
    var block = document.createElement("form");  
    block.action = "create";
    block.method = "POST";

    block.style.backgroundColor = "rgb(236, 253, 253)";
    block.classList.add("pop-up");
    block.style.position = "fixed";
    block.style.height = "30%";
    block.style.width = "30%";
    block.style.left = "35%";
    block.style.top = "20%";
    block.style.borderRadius = "10px";
    block.style.border = "3px solid black";
    block.style.boxShadow = "2px 2px 5px rgba(0, 0, 0, 0.3)";

    var taskName = document.createElement("input");
    taskName.type = "text";
    taskName.placeholder = "Task Name";
    taskName.id = "task-name-input";
    taskName.name = "task-name-input";
    taskName.style.position = "absolute";
    taskName.style.left = "50%";
    taskName.style.top = "20%";
    taskName.style.width = "60%";
    taskName.style.transform = "translate(-50%, -50%)";


    var originalTaskCategory = document.getElementById("create-task-category");
    var taskCategory = originalTaskCategory.cloneNode(true);
    taskCategory.style.display = "block";
    taskCategory.style.position = "absolute";
    taskCategory.style.left = "50%";
    taskCategory.style.top = "35%";
    taskCategory.style.width = "60%";
    taskCategory.style.transform = "translate(-50%, -50%)";




    
    var taskDescription = document.createElement("input");
    taskDescription.type = "text";
    taskDescription.placeholder = "Task Description";
    taskDescription.id = "task-description-input";
    taskDescription.name = "task-description-input";
    taskDescription.style.position = "absolute";
    taskDescription.style.left = "50%";
    taskDescription.style.top = "55%";
    taskDescription.style.height = "25%";
    taskDescription.style.width = "60%";
    taskDescription.style.transform = "translate(-50%, -50%)";
    
    var cancel = document.createElement("input");
    cancel.type="button";
    cancel.value="Cancel";
    cancel.style.position = "absolute";
    cancel.style.left = "25%";
    cancel.style.top = "75%";
    cancel.style.width = "20%";
    cancel.style.height = "10%";
    cancel.borderRadius = "5px";
    cancel.style.border = "none";
    cancel.style.backgroundColor = "rgb(255, 255, 188)";


    var submit = document.createElement("input");
    submit.type="submit";
    submit.value="Submit";
    submit.style.position = "absolute";
    submit.style.left = "55%";
    submit.style.top = "75%";
    submit.style.width = "20%";
    submit.style.height = "10%";
    submit.borderRadius = "5px";
    submit.style.border = "none";
    submit.style.backgroundColor = "rgb(255, 255, 188)";

    block.appendChild(cancel);
    block.appendChild(submit);
    block.appendChild(taskName);
    block.appendChild(taskCategory);

    form.appendChild(block);

    cancel.addEventListener('click', function() {
        form.removeChild(block);
    })

    submit.addEventListener('click', function() {
        console.log("category input to submit: ", taskCategory.value);
        refreshForm.submit();
    })

    document.getElementById("create-new-category-option").addEventListener("click", addNewCategory);



});

document.getElementById('lists').addEventListener('click', function(event) {
    console.log('Clicked element:', event.target);
    if (event.target.tagName === 'INPUT' && event.target.getAttribute('type') === 'button') {
        var button = event.target;
        var form = button.closest('form');

        console.log('Button clicked');
        if (form) {
            form.action = 'filter';
            form.method = 'POST';
            form.submit();

            var incompleteDiv = document.getElementById('incomplete');
            var completeDiv = document.getElementById('completed');
            incompleteDiv.innerHTML = '';
            completeDiv.innerHTML = '';

            var incompleteH1 = document.createElement('h1');
            incompleteH1.innerText = 'Incomplete';
            incompleteDiv.appendChild(incompleteH1);

            var completeH1 = document.createElement('h1');
            completeH1.innerText = 'Completed';
            completeDiv.appendChild(completeH1);
        }
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const taskNames = document.querySelectorAll('#task-p');

    taskNames.forEach(function(taskName) {
        taskName.addEventListener('click', function() {
            console.log('Task name clicked:', taskName.innerText);
            var form = document.getElementById("edit-task-form");
            form.action = "get-tasks";
            form.method = "GET";
            var taskNameInput = document.getElementById("edit-task-name");


            var textToEdit = taskName.innerText;
            var taskNameP = document.createElement("p");
            taskNameP.id = "task-name-p";
            taskNameP.textContent = textToEdit;
            taskNameInput.value = textToEdit;

            var taskNameHiddenInput = document.createElement("input");
            taskNameHiddenInput.type = "hidden";
            taskNameHiddenInput.id = "task-name-hidden-input";
            taskNameHiddenInput.name = "task-name-hidden-input";
            taskNameHiddenInput.value = textToEdit;
            form.appendChild(taskNameHiddenInput);





            var editTaskForm = document.getElementById('edit-task-form');
            if(editTaskForm.style.display === 'block') {
                editTaskForm.style.transform = "translateX(100%)";
                setTimeout(() => {
                    editTaskForm.style.display = 'none';
                })
            }else{
                editTaskForm.style.display = 'block';
                setTimeout(() => {
                    editTaskForm.style.transform = "translateX(0)";
                },50);
            }

        });
    });
});


document.getElementById("settings").addEventListener("click", function(){
    var formContainer = document.createElement("div");
    formContainer.className = "form-container";

    var formContent = document.createElement("p");
    formContent.textContent = "This is a form.";
    formContainer.appendChild(formContent);
    document.body.appendChild(formContainer);
});

document.getElementById("save-task").addEventListener("click", function(){
    var form = document.getElementById("edit-task-form");
    form.action = "edit";
    form.method = "POST";
    form.submit();
})

document.getElementById("delete-task").addEventListener("click", function(){
    var form = document.getElementById("edit-task-form");
    form.action = "delete-task";
    form.method = "POST";
    form.submit();
})

document.getElementById("new-category-option").addEventListener("click", addNewCategory);


function addNewCategory(){
    var body = document.getElementsByTagName("body")[0];
    var newCategoryInput = document.createElement("div");
    var newCategory = document.createElement("input");
    var heading = document.createElement("h1");

    newCategoryInput.style.display = "block";
    newCategoryInput.classList.add("pop-up");
    newCategoryInput.style.border = "1px solid #ccc";
    newCategoryInput.style.borderRadius = "5px";
    newCategoryInput.style.backgroundColor = "#f9f9f9";
    newCategoryInput.style.position = "absolute";
    newCategoryInput.style.left = "40%";
    newCategoryInput.style.top = "27%";
    newCategoryInput.style.height = "20%";
    newCategoryInput.style.width = "20%";
    newCategoryInput.style.backgroundColor = "rgb(236, 253, 253)";
    newCategoryInput.id = "new-category-input";

    heading.textContent = "Add a new category";
    heading.style.position = "absolute";
    heading.style.left = "34%";
    heading.style.top = "7%";
    heading.style.fontSize = "15px";


    newCategory.id = "new-category";
    newCategory.name = "new-category";
    newCategory.style.position = "absolute";
    newCategory.style.left = "27%";
    newCategory.style.top = "32%";

    var addCategoryButton = document.createElement("button");

    addCategoryButton.id = "add-category-button";
    addCategoryButton.textContent = "Add Category";
    addCategoryButton.style.position = "absolute";
    addCategoryButton.style.left = "55%";
    addCategoryButton.style.top = "60%";
    addCategoryButton.style.width = "26%";
    addCategoryButton.style.height = "20%";
    addCategoryButton.style.backgroundColor = "rgb(255, 255, 188)";
    addCategoryButton.style.border = "none";

    var cancel = document.createElement("input");
    cancel.type = "button";
    cancel.value = "Cancel";
    cancel.style.position = "absolute";
    cancel.style.left = "20%";
    cancel.style.top = "60%";
    cancel.style.width = "26%";
    cancel.style.height = "20%";
    cancel.style.backgroundColor = "rgb(255, 255, 188)";
    cancel.style.border = "none";


    body.appendChild(newCategoryInput);
    newCategoryInput.appendChild(heading);
    newCategoryInput.appendChild(newCategory);
    newCategoryInput.appendChild(addCategoryButton);
    newCategoryInput.appendChild(cancel);

    addCategoryButton.addEventListener("click", function(){

        var form = document.getElementById("edit-task-form");

        var newCategoryText = document.getElementById("new-category").value;
        if (form.style.display === 'none') {
            var categoryDropdown = document.getElementById("create-task-category");
        }else{
            var categoryDropdown = document.getElementById("category-dropdown");
        }
        console.log(categoryDropdown.options);
        var newCategoryOption = document.createElement("option");
        newCategoryOption.value = newCategoryText;
        newCategoryOption.textContent = newCategoryText;
        categoryDropdown.appendChild(newCategoryOption);
        if(categoryDropdown){
            var options = categoryDropdown.options;
            for (var i = 0; i < options.length; i++) {
                if (options[i].value === newCategoryText) {
                    options[i].selected = true;
                    break;
                }
            }
        }

        newCategoryInput.style.display = "none";
    })


    cancel.addEventListener("click", function(){
        newCategoryInput.style.display = "none";
    })
};





document.getElementById("refresh").addEventListener("click", function(){
    var form = document.getElementById("refresh-form");
    form.action = "get-tasks";
    form.method = "GET";
    window.location.href = "GetTasks";
})

function taskDisplay(){
    var form = document.getElementById("todays-tasks-form");
    var incompleteHeading = document.getElementById("incomplete-heading");
    var completeHeading = document.getElementById("complete-heading");
    var tasksSize = document.getElementById("tasksSize");
    var completedTasksSize = document.getElementById("tasksSize");

    if(tasksSize.textContent === "0"){
        incompleteHeading.style.display = "none";
        var label = document.createElement("label");
        label.textContent = "No tasks to display";
        incompleteHeading.appendChild(label);
    }
    if(completedTasksSize.textContent === "0"){
        var label = document.createElement("label");
        label.textContent = "No tasks to display";
        completeHeading.appendChild(label);
        completeHeading.style.display = "none";
    }
    if(tasksSize.textContent === "0" && completedTasksSize.textContent === "0"){
        var label = document.createElement("label");
        label.textContent = "You have no tasks, click the +add tasks button to get started";
        form.appendChild(label);
    }
}

taskDisplay();