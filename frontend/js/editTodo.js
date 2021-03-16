'use strict';

let navToHome = () => {
    window.location.href = "/frontend/index.html";
}

let postRequest = async (id, titleInput, contentInput, categoryInput) => {
    const response = await fetch(`http://localhost:8080/todo/${id}`, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            title: titleInput,
            content: contentInput,
            category: {
                id: categoryInput
            }
        })
    });
    if (response.status != 200) {
        console.error(`Error: Status code ${response.status}\n${response.json}`);
        return response.status;
    }
    let data = await response.json();
    return data;
}

let updateTodo = () => {
    let title = document.querySelector("#title-input").value;
    let content = document.querySelector("#content-input").value;
    let category = parseInt(document.querySelector("#category-selection").value);
    let id = parseInt(document.querySelector("#id").textContent.replace("ID: ", ""));
    let p = document.querySelector("#response");
    postRequest(id, title, content, category).then((response) => {
        if (Number.isInteger(response)) {
            p.textContent = `There was a problem updating your item: Response code ${response}. Check that the server URL is correct, and that the server is running.`;
            p.style.color = "red";
            p.style.display = "block";
        } else {
            console.log(response);
            p.textContent = `To-Do Item successfully updated! Item ID: ${response.id}`;
            p.style.color = "green";
            p.style.display = "block";
        }
    }).catch(err => {
        console.error(err);
        p.textContent = `There was a problem updating your item: ${err}. Check that the server URL is correct, and that the server is running.`;
        p.style.color = "red";
        p.style.display = "block";
    });
}

let deleteRequest = async (id) => {
    const response = await fetch(`http://localhost:8080/todo/${id}`, {
        method: "DELETE"
    });
    if (response.status != 200) {
        console.error(`Error: Status code ${response.status}\n${response.json}`);
        return response.status;
    }
    let data = await response.json();
    return data;
}

let deleteTodo = () => {
    let id = parseInt(document.querySelector("#id").textContent.replace("ID: ", ""));
    let p = document.querySelector("#response");
    deleteRequest(id).then((response) => {
        if (Number.isInteger(response)) {
            p.textContent = `There was a problem deleting your item: Response code ${response}. Check that the server URL is correct, and that the server is running.`;
            p.style.color = "red";
            p.style.display = "block";
        } else {
            console.log(response);
            p.textContent = `To-Do Item successfully deleted! Item ID: ${response.id}`;
            p.style.color = "green";
            p.style.display = "block";
            document.querySelector("#title-input").setAttribute("disabled", "true");
            document.querySelector("#content-input").setAttribute("disabled", "true");
            document.querySelector("#category-selection").setAttribute("disabled", "true");
        }
    }).catch(err => {
        console.error(err);
        p.textContent = `There was a problem deleting your item: ${err}. Check that the server URL is correct, and that the server is running.`;
        p.style.color = "red";
        p.style.display = "block";
    });
}

let getCategories = async () => {
    const response = await fetch("http://localhost:8080/category");
    if (response.status != 200) {
        console.error(`Error: Status code ${response.status}\n${response.json}`);
    }
    let data = await response.json();
    return data;
}

getCategories().then(data => {
    data.forEach(category => {
        let option = document.createElement("option");
        option.value = category.id;
        option.text = category.name;
        document.querySelector("#category-selection").appendChild(option);
    })
})

let resetCounter = () => {
    document.querySelector("#count_message").textContent = "250 of 250 characters left";
}

let trackChange = (e) => {
    const target = e.currentTarget;
    const maxLength = target.getAttribute("maxlength");
    const currentLength = target.value.length;
    let text = document.querySelector("#count_message");

    if (currentLength >= maxLength) {
        console.log("You have reached the maximum number of characters.");
        text.textContent = "Max characters reached";
    }

    text.textContent = `${maxLength - currentLength} of ${maxLength} characters left`;
};

const textarea = document.querySelector("#content-input");

textarea.addEventListener("change", event => trackChange(event));
textarea.addEventListener("input", event => trackChange(event));

let readTodo = async (id) => {
    const response = await fetch(`http://localhost:8080/todo/${id}`);
    if (response.status != 200) {
        console.error(`Error: Status code ${response.status}\n${response.json}`);
        return;
    }
    let data = await response.json();
    return data;
};

let queryString = window.location.search;
let urlParams = new URLSearchParams(queryString);

readTodo(urlParams.get("id")).then(data => {
    document.querySelector("#id").textContent = `ID: ${data.id}`;
    document.querySelector("#title-input").value = data.title;
    document.querySelector("#content-input").value = data.content;
    document.querySelector("#category-selection").value = `${data.category}`
    document.querySelector("#spinner").style.display = "none";
    document.querySelector("#edit-form").style.display = "block";
}).catch(err => {
    document.querySelector("#spinner-actual").style.display = "none";
    document.querySelector("#load-status").innerHTML = "This item doesn't exist, or none was specified<br>Please select a to-do item to edit from the home page";
    console.error(err)
});