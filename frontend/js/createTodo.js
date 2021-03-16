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
    if (response.status != 200) {
        console.error(`Error: Status code ${response.status}\n${response.json}`);
        return;
    }
    let data = await response.json();
    return data;
}

let createTodo = () => {
    let title = document.querySelector("#title-input").textContent;
    let content = document.querySelector("#content-input").textContent;
    postRequest(title, content).then((response) => {
        console.log("done");
        let p = document.querySelector("#response");
        p.textContent = `To-Do Item successfully created! Item ID: ${response.id}`;
        p.style.display = "block";
    })

}