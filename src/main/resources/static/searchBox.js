
async function getAll() {
    let entityType = document.getElementById('entity-choice').value;
    fetch(`/getAll${entityType}s`)
        .then(async response => {
            let content = await response.json();
            let output = JSON.stringify(content);
            document.getElementById('response-body').append(output);
        })
        .catch(error => console.log(error));
}

// async function getOne() {

// }

let allBtn = document.getElementById('all');
allBtn.addEventListener('click', getAll);

