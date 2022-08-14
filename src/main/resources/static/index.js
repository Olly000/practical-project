// document.getElementById('form-box').setAttribute('src', './search.html');

function displaySearchPage() {
   window.location.assign("./search.html");
}

function displayAddPage() {
    window.location.assign("./add.html");
 }

 function displayUpdatePage() {
    window.location.assign("./update.html");
 }

 function displayDeletePage() {
    window.location.assign("./delete.html");
 }

let searchButton = document.getElementById('search-for').addEventListener('click', displaySearchPage);
let addButton = document.getElementById('add-item').addEventListener('click', displayAddPage);
let editButton = document.getElementById('update-item').addEventListener('click', displayUpdatePage);
let deleteButton = document.getElementById('delete-item').addEventListener('click', displayDeletePage);
