'use strict';

let navToHome = () => {
    window.location.href = "index.html";
}

let postRequest = async (id, nameInput) => {
    const response = await fetch(`${getBackendLink()}/category/${id}`, {
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
            p.textContent = `Category successfully updated! Category ID: ${response.id}`;
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

let deleteRequest = async (id) => {
    const response = await fetch(`${getBackendLink()}/category/${id}`, {
        method: "DELETE"
    });
    if (response.status != 200) {
        console.error(`Error: Status code ${response.status}\n${response.json}`);
        return response.status;
    }
    let data = await response.json();
    return data;
}

let deleteCategory = () => {
    let id = parseInt(document.querySelector("#id").textContent.replace("ID: ", ""));
    let p = document.querySelector("#response");
    deleteRequest(id).then((response) => {
        if (Number.isInteger(response)) {
            p.textContent = `There was a problem deleting your category: Response code ${response}. Check that the server URL is correct, and that the server is running.`;
            p.style.color = "red";
            p.style.display = "block";
        } else {
            console.log(response);
            p.textContent = `Category successfully deleted!`;
            p.style.color = "green";
            p.style.display = "block";
            document.querySelector("#name-input").setAttribute("disabled", "true");
        }
    }).catch(err => {
        console.error(err);
        p.textContent = `There was a problem deleting your category: ${err}. Check that the server URL is correct, and that the server is running.`;
        p.style.color = "red";
        p.style.display = "block";
    });
}

let readTodo = async (id) => {
    const response = await fetch(`${getBackendLink()}/category/${id}`);
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
    document.querySelector("#name-input").value = data.name;
    document.querySelector("#spinner").style.display = "none";
    document.querySelector("#edit-form").style.display = "block";
    if (document.querySelector("#name-input").value == "Unsorted") {
        document.querySelector("#delete-button").setAttribute("disabled", "true");
    }
}).catch(err => {
    document.querySelector("#spinner-actual").style.display = "none";
    document.querySelector("#load-status").innerHTML = "This category doesn't exist, or none was specified<br>Please select a category to edit from the home page";
    console.error(err)
});