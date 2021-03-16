'use strict';

let navToHome = () => {
    window.location.href = "/frontend/index.html";
}

let postRequest = async (id, nameInput) => {
    const response = await fetch(`http://localhost:8080/category/${id}`, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: nameInput
        })
    });
    if (response.status != 200) {
        console.error(`Error: Status code ${response.status}\n${response.json}`);
        return response.status;
    }
    let data = await response.json();
    return data;
}

let updateCategory = () => {
    let name = document.querySelector("#name-input").value;
    let id = parseInt(document.querySelector("#id").textContent.replace("ID: ", ""));
    let p = document.querySelector("#response");
    postRequest(id, name).then((response) => {
        if (Number.isInteger(response)) {
            p.textContent = `There was a problem updating your category: Response code ${response}. Check that the server URL is correct, and that the server is running.`;
            p.style.color = "red";
            p.style.display = "block";
        } else {
            console.log(response);
            p.textContent = `Category successfully updated! Item ID: ${response.id}`;
            p.style.color = "green";
            p.style.display = "block";
        }
    }).catch(err => {
        console.error(err);
        p.textContent = `There was a problem updating your category: ${err}. Check that the server URL is correct, and that the server is running.`;
        p.style.color = "red";
        p.style.display = "block";
    });
}

