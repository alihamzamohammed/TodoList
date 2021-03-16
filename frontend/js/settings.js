'use strict';

let updateServer = () => {
    setBackendLink(document.querySelector("#backend-link").value);
};

document.querySelector("#backend-link").value = getBackendLink();