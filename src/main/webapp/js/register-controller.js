document.body.style.backgroundImage = null;

var form = document.getElementById('register-form');

form.addEventListener('submit', function (event) {
    event.preventDefault();

    if (form['password'].value !== form['passconfirm'].value) {
        alert("Passwords must match!");
        return;
    }

    let xhttp = new XMLHttpRequest();

    xhttp.open("POST", "/api/customer");
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4) {
            if (xhttp.status === 200) {
                alert("Thank you for registering. An employee will be reviewing your account soon.")
                loadPage("/welcome");
            } else if (xhttp.status === 412) {
                alert("That username seems to be taken.");
            }
        }
    }

    xhttp.send([
        encodeURIComponent("username") + '=' + encodeURIComponent(form['username'].value),
        encodeURIComponent("password") + '=' + encodeURIComponent(form['password'].value),
        encodeURIComponent("first_name") + '=' + encodeURIComponent(form['first_name'].value),
        encodeURIComponent("last_name") + '=' + encodeURIComponent(form['last_name'].value),
        encodeURIComponent("dob") + '=' + encodeURIComponent(form['dob'].value),
        encodeURIComponent("phone") + '=' + encodeURIComponent(form['phone'].value),
        encodeURIComponent("email") + '=' + encodeURIComponent(form['email'].value),
        encodeURIComponent("street") + '=' + encodeURIComponent(form['street'].value),
        encodeURIComponent("city") + '=' + encodeURIComponent(form['city'].value),
        encodeURIComponent("state") + '=' + encodeURIComponent(form['state'].value),
        encodeURIComponent("post") + '=' + encodeURIComponent(form['post'].value),
        encodeURIComponent("license") + '=' + encodeURIComponent(form['license'].value),
        encodeURIComponent("issued") + '=' + encodeURIComponent(form['issued'].value),
        encodeURIComponent("expires") + '=' + encodeURIComponent(form['expires'].value)
    ].join('&'));
});