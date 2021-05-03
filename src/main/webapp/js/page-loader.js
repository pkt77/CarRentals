let currentPage = '/';
let user;

window.onload = window.onpopstate = function () {
    if (window.location.pathname === '/') {
        loadPage('/welcome');
    } else {
        loadPage(window.location.pathname);
    }

    displayUser();
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

function displayUser() {
    const http = new XMLHttpRequest();

    http.onreadystatechange = function () {
        if (http.readyState === 4 && http.status === 200) {
            user = JSON.parse(http.responseText);

            let nav = document.getElementById('account');

            nav.innerHTML = `<a class="nav-link nav-text" onclick="loadPage('/account')">${user['username']}</a>
                             <a class="nav-link nav-text" onclick="logout()">Logout</a>`;
        }
    }

    http.open('GET', "/api/login");
    http.send();
}

function logout() {
    const http = new XMLHttpRequest();

    http.onreadystatechange = function () {
        if (http.readyState === 4 && http.status === 200) {
            let nav = document.getElementById('account');

            nav.innerHTML = `<a class="nav-link nav-text" onclick="loadPage('/login')">Log In</a>
                             <a class="nav-link nav-text" onclick="loadPage('/register')">Sign Up</a>`;
        }
    }

    http.open('POST', "/api/logout");
    http.send();
}