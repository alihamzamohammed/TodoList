'use strict';

let navToHome = () => {
    window.location.href = "/frontend/index.html";
}

let postRequest = async (titleInput) => {
    const response = await fetch("http://localhost:8080/category", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: titleInput
        })
    });
    if (response.status == 409) {
        console.warn("Category already exists");
        return response.status;
    }
    if (response.status != 201) {
        console.error(`Error: Status code ${response.status}\n${response.json}`);
        return response.status;
    }
    let data = await response.json();
    return data;
}

let createTodo = () => {
    let title = document.querySelector("#title-input").value;
    let p = document.querySelector("#response");
    postRequest(title).then((response) => {
        if (Number.isInteger(response)) {
            if (response == 409) {
                p.textContent = `This category already exists.`;
            } else {
                p.textContent = `There was a problem creating your category: Response code ${response}. Check that the server URL is correct, and that the server is running.`;
            }
            p.style.color = "red";
            p.style.display = "block";
        } else {
            console.log(response);
            p.textContent = `Category successfully created! Category ID: ${response.id}`;
            p.style.color = "green";
            p.style.display = "block";
        }
    }).catch(err => {
        console.error(err);
        p.textContent = `There was a problem creating your category: ${err}. Check that the server URL is correct, and that the server is running.`;
        p.style.color = "red";
        p.style.display = "block";
    });
}


