var display = "";
var flag = 0;

LoadFirstPage();

function LoadFirstPage() {

    flag = 1;
    console.log("1st page");
    console.log("The value of Flag is " + flag);


    display = '<form id="formID">' +
        '<h1 id="headerID"> ' + "Personal Information" + '</h1>' +
        '<p id="msg">' + "</p>" +
        '<label id="FirstNameLabel">' + "First Name" + " </label>" +
        '<input type="text" id="FirstName" placeholder=" First Name">' + '</input>' +

        '<label id="LastNameLabel">' + "Last Name" + " </label>" +
        '<input type="text" id="LastName" placeholder=" Last Name">' + '</input>' +

        '<label id="DOBLabel">' + ' Date of Birth' + '</label>' +
        '<input type="text" id="DOB" placeholder=" Date of Birth(mm/dd/yyyy)">' + '</input>' +

        '<label id="phoneLabel">' + 'Phone Number' + '</label>' +
        '<input type="text" id="phone" placeholder=" Phone Number">' + '</input>' +

        '<label id="emailLabel">' + 'Email Address' + '</label>' +
        '<input type="text" id="email" placeholder=" Email Address">' + '</input>' +

        '<button id="nextButton" type="submit">' + 'Next' + '</button>' +
        '</form>'

    ;


    document.getElementById("test").innerHTML = display;

    formID.addEventListener('submit', (Event) => {
            Event.preventDefault();
            //console.log("check");
        }
    );


    document.getElementById("nextButton").addEventListener("click", loadSecondPage);


}


function loadSecondPage() {
    if (flag == 1) {
        console.log("in second Page");

        console.log("The value of Flag is " + flag);
        let customerFirstName = document.getElementById("FirstName").value;
        let customerLastName = document.getElementById("LastName").value;
        let customerDOB = document.getElementById("DOB").value;
        let customerPhone = document.getElementById("phone").value;
        let customerEmail = document.getElementById("email").value;

        if (!customerFirstName || !customerLastName || !customerDOB || !customerEmail || !customerPhone) {
            document.getElementById("msg").innerHTML = "Please fill out all the fields";


        } else {
            flag++;
            console.log("The value of Flag is " + flag);
            display = '<form id="formID">' +
                '<h1 id="headerID"> ' + "Address Information" + '</h1>' +

                '<p id="msg">' + "</p>" +

                '<label id="streetLabel">' + "Street Name" + " </label>" +
                '<input type="text" id="street" placeholder=" Street Name">' + '</input>' +

                '<label id="cityLabel">' + " Name of the City" + " </label>" +
                '<input type="text" id="city" placeholder=" City Name">' + '</input>' +

                '<label id="stateLabel">' + ' Name of the State' + '</label>' +
                '<input type="text" id="state" placeholder=" State">' + '</input>' +

                '<label id="postalLabel">' + 'Postal Number' + '</label>' +
                '<input type="text" id="postal" placeholder=" Postal Number">' + '</input>' +

                '<button id="nextButton2" type="submit">' + 'Next' + '</button>' +
                '<button id="previousButton" type="submit">' + 'Previous' + '</button>' +
                '</form>'

            ;
            document.getElementById("test").innerHTML = display;

            formID.addEventListener('submit', (Event) => {
                    Event.preventDefault();
                    //console.log("check");
                }
            );

            document.getElementById("previousButton").addEventListener("click", LoadFirstPage);
            document.getElementById("nextButton2").addEventListener("click", loadThirdPage);


        }
    } else {
        flag = 2;
        console.log("The value of Flag is " + flag);
        display = '<form id="formID">' +
            '<h1 id="headerID"> ' + "Address Information" + '</h1>' +

            '<p id="msg">' + "</p>" +

            '<label id="streetLabel">' + "Street Name" + " </label>" +
            '<input type="text" id="street" placeholder=" Street Name">' + '</input>' +

            '<label id="cityLabel">' + " Name of the City" + " </label>" +
            '<input type="text" id="city" placeholder=" City Name">' + '</input>' +

            '<label id="stateLabel">' + ' Name of the State' + '</label>' +
            '<input type="text" id="state" placeholder=" State">' + '</input>' +

            '<label id="postalLabel">' + 'Postal Number' + '</label>' +
            '<input type="text" id="postal" placeholder=" Postal Number">' + '</input>' +

            '<button id="nextButton2" type="submit">' + 'Next' + '</button>' +
            '<button id="previousButton" type="submit">' + 'Previous' + '</button>' +
            '</form>'

        ;
        document.getElementById("test").innerHTML = display;

        formID.addEventListener('submit', (Event) => {
                Event.preventDefault();
                //console.log("check");
            }
        );
        document.getElementById("previousButton").addEventListener("click", LoadFirstPage);
        document.getElementById("nextButton2").addEventListener("click", loadThirdPage);


    }


}


function loadThirdPage() {

    if (flag == 2) {
        console.log("in third Page");
        let customerStreetName = document.getElementById("street").value;
        let customerCityName = document.getElementById("city").value;
        let customerStateName = document.getElementById("state").value;
        let customerPostalCode = document.getElementById("postal").value;

        if (!customerStreetName || !customerCityName || !customerStateName || !customerPostalCode) {
            document.getElementById("msg").innerHTML = "Please fill out all the fields";
        } else {
            flag++;
            display = '<form id="formID">' +
                '<h1 id="headerID"> ' + "Licensing Information" + '</h1>' +

                '<p id="msg">' + "</p>" +

                '<label id="LicenseLabel">' + "License Number" + " </label>" +
                '<input type="text" id="License" placeholder=" License Number">' + '</input>' +

                '<label id="authorityLabel">' + " Issuing Authority" + " </label>" +
                '<input type="text" id="authority" placeholder=" Issuing Authority">' + '</input>' +

                '<label id="expireLabel">' + ' Expiration Date' + '</label>' +
                '<input type="text" id="expire" placeholder=" Expiration Date (mm/dd/yyyy)">' + '</input>' +

                '<button id="nextButton3">' + 'Next' + '</button>' +
                '<button id="previousButton3">' + 'Previous' + '</button>' +
                '</form>'

            ;
            document.getElementById("test").innerHTML = display;

            formID.addEventListener('submit', (Event) => {
                    Event.preventDefault();
                    //console.log("check");
                }
            );
            document.getElementById("previousButton3").addEventListener("click", loadSecondPage);
            document.getElementById("nextButton3").addEventListener("click", loadFourthPage);
        }


    } else {
        flag = 3;
        display = '<form id="formID">' +
            '<h1 id="headerID"> ' + "Licensing Information" + '</h1>' +

            '<p id="msg">' + "</p>" +

            '<label id="LicenseLabel">' + "License Number" + " </label>" +
            '<input type="text" id="License" placeholder=" License Number">' + '</input>' +

            '<label id="authorityLabel">' + " Issuing Authority" + " </label>" +
            '<input type="text" id="authority" placeholder=" Issuing Authority">' + '</input>' +

            '<label id="expireLabel">' + ' Expiration Date' + '</label>' +
            '<input type="text" id="expire" placeholder=" Expiration Date (mm/dd/yyyy)">' + '</input>' +


            '<button id="nextButton3">' + 'Next' + '</button>' +
            '<button id="previousButton3">' + 'Previous' + '</button>' +
            '</form>'

        ;
        document.getElementById("test").innerHTML = display;

        formID.addEventListener('submit', (Event) => {
                Event.preventDefault();
                //console.log("check");
            }
        );
        document.getElementById("previousButton3").addEventListener("click", loadSecondPage);
        document.getElementById("nextButton3").addEventListener("click", loadFourthPage);


    }

}


function loadFourthPage() {
    if (flag == 3) {
        console.log("in fourth Page");
        let customerLicensetNumber = document.getElementById("License").value;
        let customerAuthority = document.getElementById("authority").value;
        let customerExpireDate = document.getElementById("expire").value;


        if (!customerLicensetNumber || !customerAuthority || !customerExpireDate) {
            document.getElementById("msg").innerHTML = "Please fill out all the fields";
        } else {
            flag++;
            display = '<form id="formID">' +
                '<h1 id="headerID"> ' + " Login Credentials" + '</h1>' +

                '<p id="msg">' + "</p>" +

                '<label id="userNameLabel">' + "User Name" + " </label>" +
                '<input type="text" id="userName" placeholder=" Create Your User Name">' + '</input>' +

                '<label id="passwordLabel">' + "Password" + " </label>" +
                '<input type="text" id="password" placeholder=" Password">' + '</input>' +

                '<label id="confirmationLabel">' + ' Confirm Password' + '</label>' +
                '<input type="text" id="confirmation" placeholder=" Confirm Your Password">' + '</input>' +


                '<button id="finishButton" type="submit">' + 'Finish' + '</button>' +
                '<button id="previousButton4">' + 'Previous' + '</button>' +
                '</form>'

            ;
            document.getElementById("test").innerHTML = display;

            formID.addEventListener('submit', (Event) => {
                    Event.preventDefault();
                    //console.log("check");
                }
            );
            document.getElementById("previousButton4").addEventListener("click", loadThirdPage);
            document.getElementById("finishButton").addEventListener("click", loadLastPage);


        }


    } else {
        flag = 4;
        display = '<form id="formID">' +
            '<h1 id="headerID"> ' + " Login Credentials" + '</h1>' +

            '<p id="msg">' + "</p>" +

            '<label id="userNameLabel">' + "User Name" + " </label>" +
            '<input type="text" id="userName" placeholder=" Create Your User Name">' + '</input>' +

            '<label id="passwordLabel">' + "Password" + " </label>" +
            '<input type="text" id="password" placeholder=" Password">' + '</input>' +

            '<label id="confirmationLabel">' + ' Confirm Password' + '</label>' +
            '<input type="text" id="confirmation" placeholder=" Confirm Your Password">' + '</input>' +


            '<button id="finishButton" type="submit">' + 'Finish' + '</button>' +
            '<button id="previousButton4">' + 'Previous' + '</button>' +
            '</form>'

        ;
        document.getElementById("test").innerHTML = display;

        formID.addEventListener('submit', (Event) => {
                Event.preventDefault();
                //console.log("check");
            }
        );
        document.getElementById("previousButton4").addEventListener("click", loadThirdPage);
        document.getElementById("finishButton").addEventListener("click", loadLastPage);


    }

}

function loadLastPage() {
    let customerUserName = document.getElementById("userName").value;
    let customerPassword = document.getElementById("password").value;
    let confirmedPassword = document.getElementById("confirmation").value;
    //console.log(customerPassword);
    //console.log(confirmedPassword);


    if (!customerUserName || !customerPassword || !confirmedPassword) {
        document.getElementById("msg").innerHTML = "Please fill out all the fields";


    } else {
        if (customerPassword === confirmedPassword) {
            console.log("Matched");
            var opened = window.open("RegistrationComplete.html");

        } else {
            console.log("Didn't match");
            document.getElementById("msg").innerHTML = "Password didn't match. Please try again !";
        }


    }

}