// document.getElementById('form-box').setAttribute('src', './search.html');

function displaySearchPage() {
   window.location.assign("./search.html");
}

function displayAddPage() {
    window.location.assign("./add.html");
 }

 function displayEditPage() {
    window.location.assign("./edit.html");
 }

 function displayDeletePage() {
    window.location.assign("./delete.html");
 }

let searchButton = document.getElementById('search-for').addEventListener('click', displaySearchPage);
let addButton = document.getElementById('add-item').addEventListener('click', displayAddPage);
let editButton = document.getElementById('edit-item').addEventListener('click', displayEditPage);
let deleteButton = document.getElementById('delete-item').addEventListener('click', displayDeletePage);


// const displayEditForm = () => {

// }

// const displayDeleteForm = () => {

// }

// const displayAddForm = () => {

// }