// Utility Functions
function buildEndpoint(input, operation) {
    let entityType = document.getElementById('entity-choice').value;
    console.log(entityType);
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

// Create Functions

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
    let endpoint = buildEndpoint(input, 'getOne');
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
    let endpoint = buildEndpoint(input, 'delete');
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
