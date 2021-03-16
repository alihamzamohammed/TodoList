'use strict';

let navToHome = () => {
    window.location.href = "index.html";
}

let postRequest = async (titleInput, contentInput, categoryInput) => {
    const response = await fetch(`${getBackendLink()}/todo`, {
        method: "POST",
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
    let category = parseInt(document.querySelector("#category-selection").value);
    let p = document.querySelector("#response");
    postRequest(title, content, category).then((response) => {
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

let getCategories = async () => {
    const response = await fetch(`${getBackendLink()}/category`);
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
