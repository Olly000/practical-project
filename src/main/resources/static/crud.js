// Utility Functions
function buildEndpoint(input, operation, entityType) {
    let encode = input.replace(/' '/, '%20');
    if(entityType == 'Band') {
        return `/${operation}Band?bandName=${encode}`;
    }else if(entityType == 'Musician') {
        return `/${operation}Musician?fullName=${encode}`;
    } else if(entityType == 'Recording') {
        return `/${operation}Recording?title=${encode}`;
    } else {
        return "shits not working";
    }
}

function buildEndpointNoQString(operation, entityType) {
         return `/${operation}${entityType}`;
 }


function convertFormToData(form) {
    const formEntries = new FormData(form).entries();
    const formData = Object.assign(...Array.from(formEntries, ([name, value]) => ({[name]: value})));
    return formData;

}

// Create Functions
function createEntity(form, entityType) {
    let data = convertFormToData(form);
    let endpoint = buildEndpointNoQString('add', entityType);
    console.log(data);
    fetch(endpoint, {
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:
            `${JSON.stringify(data)}`
        
        }).then(response => {
            response.json().then(body => {
            document.getElementById('add-feedback').append(JSON.stringify(body));
            });    
        }).catch(error => {console.log(error);
            alert(`${error.message}`)});
}

function addBand() {
    let endpoint = buildEndpointNoQString('add', 'Band');
    let bandName = document.getElementById('bandName').value;
    let genre = document.getElementById('genre').value;
    let yearFormed = document.getElementById('genre').value;
    let data = `[{"bandName": "${bandName}", "genre": "${genre}", "yearFormed": ${yearFormed}}]`
    fetch(endpoint, {
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body: `${data}`
        
    }).then(response => {
            response.json().then(body => {
            document.getElementById('add-feedback').append(JSON.stringify(body));
            });    
        }).catch(error => {console.log(error);
            alert(`${error.message}`)});
}



// Read functions
function getAll() {
    let entityType = document.getElementById('entity-choice').value;
    fetch(`/getAll${entityType}s`, {
        method:"GET",
        headers:{
            "Content-Type":"application/json"
        }
        }).then(response => {
            response.json().then(body => {
            document.getElementById('response-body').append(JSON.stringify(body));
            });    
        }).catch(error => {console.log(error);
            alert(`${error.message}`)});
}

function getOne(input) {
    let endpoint = buildEndpoint(input, 'getOne', document.getElementById('entity-choice').value);
    fetch(endpoint, {
        method:"GET",
        headers:{
            "Content-Type":"application/json"
        }
        }).then(response => {
            response.json().then(body => {
            document.getElementById('response-body').append(JSON.stringify(body));
            });    
        }).catch(error => {console.log(error);
            alert(`${error.message}`)});
}

// Update Functions

// Delete Function
function deleteEntity(input) {
    let endpoint = buildEndpoint(input, 'delete', document.getElementById('entity-choice').value);
    fetch(endpoint, {
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        }
        }).then(response => {
            response.json().then(body => {
            document.getElementById('response-body').append(JSON.stringify(body));
            });    
        }).catch(error => {console.log(error);
            alert(`${error.message}`)});
}

//event listeners for CRUD pages


