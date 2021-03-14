'use strict';

// For dev purposes, fetch link will be hardcoded into functions

let createItem = (items) => {
    let rows = [];
    for (let i = 0; i < items.length; i += 3) {
        let group = items.slice(i, i + 3);
        let row = document.createElement("div");
        row.className = "row";
        console.log(`Group: ${group}`)

        for (let item in group) {
            let col = document.createElement("div");
            col.classList.add("col");
            col.classList.add("todo-container");

            let card = document.createElement("div");
            card.classList.add("card");
            card.classList.add("todo-item");

            let cardBody = document.createElement("div");
            cardBody.className = "card-body";

            let h4 = document.createElement("h4");
            h4.className = "card-title";
            h4.textContent = `${group[item].title}`

            let p = document.createElement("p");
            p.className = "card-text";
            p.textContent = `${group[item].content}`;

            cardBody.appendChild(h4);
            cardBody.appendChild(p);
            card.appendChild(cardBody);
            col.appendChild(card);
            row.appendChild(col);
        }
        rows.push(row);
    }
    return rows;
};


let createCategory = (category) => {
    let categoryDiv = document.createElement("div");
    categoryDiv.classList.add("category");
    categoryDiv.id = `category-${category.id}`;

    let categoryHeader = document.createElement("div");
    categoryHeader.classList.add("row");
    categoryHeader.classList.add("category-header");

    let categoryId = document.createElement("div");
    categoryId.classList.add("col-1");
    categoryId.classList.add("category-id");

    let h4 = document.createElement("h4");
    h4.textContent = `${category.id}`;
    categoryId.appendChild(h4);

    let categoryName = document.createElement("div");
    categoryName.classList.add("col-11");
    categoryName.classList.add("category-name");

    let h3 = document.createElement("h3");
    h3.textContent = `${category.name}`;
    categoryName.appendChild(h3);

    categoryHeader.appendChild(categoryId);
    categoryHeader.appendChild(categoryName);
    categoryDiv.appendChild(categoryHeader);

    console.log(category.todos);
    createItem(category.todos).forEach(row => categoryDiv.appendChild(row));


    document.querySelector("#container").appendChild(categoryDiv);
}

let fetchCategories = () => {
    let request = fetch("http://localhost:8080/category")
        .then(response => {
            if (response.status != 200) {
                console.error(`Error: Status code ${response.status}\n${response.json}`);
                return;
            }
            response.json().then(data => {
                data.forEach(category => { createCategory(category) });
            })
        })
};

fetchCategories();