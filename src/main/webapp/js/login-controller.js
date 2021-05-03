document.body.style.backgroundImage = "url('/Pictures/BackgroundPic.jfif')";

document.getElementById('loginForm').addEventListener('submit', (event) => {
        event.preventDefault();

        let username = event.target['username'].value;
        let password = event.target['password'].value;

        if (!username || !password) {
            document.getElementById("msg").innerHTML = "Please fill out all the fields";
            return;
        }

        let xhttp = new XMLHttpRequest();

        xhttp.open("POST", "/api/login");
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4) {
                if (xhttp.status === 200) {
                    displayUser();
                    loadPage("/welcome");
                } else if (xhttp.status === 401) {
                    document.getElementById("msg").innerHTML = "That username or password did not match our records.";
                } else if (xhttp.status === 412) {
                    document.getElementById("msg").innerHTML = "Your account is awaiting approval by an employee. Please come back later.";
                }
            }
        }

        xhttp.send([
            encodeURIComponent("username") + '=' + encodeURIComponent(username),
            encodeURIComponent("password") + '=' + encodeURIComponent(password)
        ].join('&'));
    }
);