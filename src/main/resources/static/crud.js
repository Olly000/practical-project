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

 function getBandEntity() {
    let bandName = document.getElementById('recording-band').value;
    
    fetch(`/checkBand?bandName=${bandName}`, {
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        }
    }).then(response => {
        response.json().then(body => {
            console.log(body, "still valid here?");
        }).then(body => {return body})   
    }).catch(error => {console.log(error);
        alert(`${error.message}`)});
}
       
 

function prepareBandPostRequest() {
    let fetchOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    }
    let data = {}; 
    data.bandName = document.getElementById('bandName').value;
    data.genre = document.getElementById('genre').value;
    data.yearFormed = document.getElementById('yearFormed').value;

    fetchOptions.body = JSON.stringify(data);
    return fetchOptions;
}

function prepareMusicianPostRequest() {
    let fetchOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    }

    let data = {};
    data.fullName = document.getElementById('fullName').value;
    data.instrument = document.getElementById('instrument').value;

    fetchOptions.body = JSON.stringify(data);
    return fetchOptions;
}

function prepareRecordingPostRequest(fullBand) {
    let fetchOptions = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    }

    let data = {}; 
    data.title = document.getElementById('title').value;
    data.band = fullBand;
    data.label = document.getElementById('label').value;
    data.releaseYear = document.getElementById('release-year').value;

    fetchOptions.body = JSON.stringify(data);
    return fetchOptions;
}

function selectPostEntity(entityType) {
    if(entityType == 'Band') {
        return prepareBandPostRequest();
    }else if(entityType == 'Musician') {
        return prepareMusicianPostRequest();
    } else if(entityType == 'Recording') {
        return prepareRecordingPostRequest(document.getElementById('recording-band'));
    } else {
        return "error in entity selection";
    }
}

// Create/Update Function - note despite its name this function is used to send data to both the add and update endpoints
function createEntity(entityType, operation) {
    let endpoint = buildEndpointNoQString(operation, entityType);
    let options = selectPostEntity(entityType);
    console.log(options.band);
    fetch(endpoint, options)
        .then(response => {
            response.json().then(body => {
            document.getElementById('response-body').append(JSON.stringify(body));
            });    
        }).catch(error => {console.log(error);
            alert(`${error.message}`)});
}

// Recording needs its own create/update due the fact that it has a band entity
// as one of its properties, this requires additional processing
function createOrUpdateRecording(operation) {
    let endPoint = `/${operation}Recording`
    let bandName = document.getElementById('recording-band').value;
    fetch(`/checkBand?bandName=${bandName}`, {
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        }
    }).then(response => response.json().then(body => 
        fetch(endPoint, prepareRecordingPostRequest(body))
        .then(response => {
            response.json().then(body => {
            document.getElementById('response-body').append(`recording ${operation} successful for ${JSON.stringify(body)}<br>`);
            });    
        }).catch(error => {console.log(error);
            alert(`${error.message}`)})
    ))
    .catch(error => {console.log(error);
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
            alert(`Not present in database ${error.message}`)});
}

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
            document.getElementById('response-body').append(JSON.stringify(body), `${input} has been deleted from the database`);
            });    
        }).catch(error => {console.log(error);
            alert(`${error.message} not present in database`)});
}



