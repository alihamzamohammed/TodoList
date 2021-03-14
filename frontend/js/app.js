'use strict';

// For dev purposes, fetch link will be hardcoded into functions

let createItem = (categoryId, item) => {

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

//fetchCategories();