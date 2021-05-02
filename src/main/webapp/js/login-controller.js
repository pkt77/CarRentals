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

        console.log("Changing ready state " + xhttp.readyState);
        xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhttp.onreadystatechange = function () {
            if (xhttp.readyState === 4 && xhttp.status==200) {
                console.log(xhttp.status);
                var opened= window.open("/views/welcome.html");
            }
            else
            {
                document.getElementById("msg").innerHTML="Password didn't match. Please try again !";
            }
        }

        xhttp.send([
            encodeURIComponent("username") + '=' + encodeURIComponent(username),
            encodeURIComponent("password") + '=' + encodeURIComponent(password)
        ].join('&'));
    }
);