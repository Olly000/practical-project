

// function iframeHeight() {
//     document.getElementById('form-box').frame.contentWindow.document.body.scrollHeight + "vw";
// }   

function displaySearchForm() {
    document.getElementById('form-box').setAttribute('src', './search.html');
}

let searchButton = document.getElementById('search-for');
searchButton.addEventListener('click', displaySearchForm);



// const addEditForm = () => {

// }

// const addDeleteForm = () => {

// }

// const choiceBoxReaction = () => {

// }