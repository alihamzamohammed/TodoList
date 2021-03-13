'use strict';

// For dev purposes, fetch link will be hardcoded into functions

let createItem = (categoryId, item) => {

};


let createCategory = (category) => {
    let div = document.createElement("div");
    let p = document.createElement("p");
    p.textContent = `Category ID: ${category.id}, Category Name: ${category.name}`;
    div.appendChild(p);
    //document.body.appendChild(div);
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