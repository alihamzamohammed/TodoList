'use strict';

let navToHome = () => {
    window.location.href = "/frontend/index.html";
}

let postRequest = async (titleInput, contentInput) => {
    const response = await fetch("http://localhost:8080/todo", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            title: titleInput,
            content: contentInput
        })
    });
    if (response.status != 201) {
        console.error(`Error: Status code ${response.status}\n${response.json}`);
        return response.status;
    }
    let data = await response.json();
    return data;
}

let createTodo = () => {
    let title = document.querySelector("#title-input").value;
    let content = document.querySelector("#content-input").value;
    let p = document.querySelector("#response");
    postRequest(title, content).then((response) => {
        if (Number.isInteger(response)) {
            p.textContent = `There was a problem creating your item: Response code ${response}. Check that the server URL is correct, and that the server is running.`;
            p.style.color = "red";
            p.style.display = "block";
        } else {
            console.log(response);
            p.textContent = `To-Do Item successfully created! Item ID: ${response.id}`;
            p.style.color = "green";
            p.style.display = "block";
        }
    }).catch(err => {
        console.error(err);
        p.textContent = `There was a problem creating your item: ${err}. Check that the server URL is correct, and that the server is running.`;
        p.style.color = "red";
        p.style.display = "block";
    });

}