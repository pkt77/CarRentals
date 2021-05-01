let currentPage = '/';

window.onload = window.onpopstate = function () {
    if (window.location.pathname === '/') {
        loadPage('/welcome');
    } else {
        loadPage(window.location.pathname);
    }
}

function loadPage(path) {
    if (currentPage === path) {
        return;
    }

    currentPage = path;

    const http = new XMLHttpRequest();

    http.onreadystatechange = function () {
        if (http.readyState === 4 && http.status === 200) {
            const cont = document.getElementById('container');

            cont.innerHTML = http.responseText;
            window.history.pushState('', '', path);

            let script = document.createElement('script');

            script.src = '/js' + path + '-controller.js';

            cont.appendChild(script);
        }
    }

    http.open('GET', path);
    http.setRequestHeader("loaded", '');
    http.send();
}