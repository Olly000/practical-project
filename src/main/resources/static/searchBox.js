
function buildEndpoint(input) {
    let entityType = document.getElementById('entity-choice').value;
    let encode = input.replace(/' '/, '%20');
    if(entityType == 'band') {
        return `/getOneBand?bandName=${encode}`;
    }else if(entityType == 'musician') {
        return `/getOneMusician?fullName=${encode}`;
    } else if(entityType == 'recording') {
        return `/getOneRecording?title=${encode}`;
    } else {
        return "shits not working";
    }
}

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
    let endpoint = buildEndpoint(input);
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

