document.body.style.backgroundImage = null;

// Init Google Maps
var map;
var googleMaps;

if (googleMaps) {
    initMap();
} else {
    googleMaps = document.createElement('script');

    googleMaps.src = "https://maps.googleapis.com/maps/api/js?key=AIzaSyBBaHm8Z3C0bG3EHOPjpZk-68nItlTqEWY&callback=initMap";
    document.body.appendChild(googleMaps);
}

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: {lat: 35.6134095, lng: -85.7001584},
        zoom: 4
    });

    const pReq = new XMLHttpRequest();

    pReq.onreadystatechange = function () {
        if (pReq.readyState === 4 && pReq.status === 200) {
            providers = JSON.parse(pReq.responseText);

            for (const provider of providers) {
                const mark = new google.maps.Marker({
                    position: {lat: provider['lat'], lng: provider['longitude']},
                    map: map
                });

                google.maps.event.addListener(mark, 'click', function () {
                    providerId = provider['id'];

                    company.innerHTML = "Company: " + provider['company'];
                    address.innerHTML = "Address: " + provider['street'] + " " + provider['city'] + ", " + provider['state'];
                });
            }
        }
    }

    pReq.open("GET", "/api/providers");
    pReq.send();
}

// Init selection elements
var company = document.getElementById('company');
var address = document.getElementById('address');

var providers;
var providerId = -1;

var image = document.getElementById('image');
var vin = document.getElementById('vin');
var style = document.getElementById('style');
var model = document.getElementById('model');
var make = document.getElementById('make');
var mileage = document.getElementById('mileage');
var cost = document.getElementById('cost');

var vehicles;
var vehicleIndex = 0;

// Fetch vehicle data
var vReq = new XMLHttpRequest();

vReq.onreadystatechange = function () {
    if (vReq.readyState === 4 && vReq.status === 200) {
        vehicles = JSON.parse(vReq.responseText);

        displayVehicle(vehicleIndex);
    }
}

vReq.open("GET", "/api/vehicles");
vReq.send();

function displayVehicle(index) {
    if (index >= vehicles.length) {
        vehicleIndex = 0;
    } else if (index < 0) {
        vehicleIndex = vehicles.length - 1;
    } else {
        vehicleIndex = index;
    }

    var vehicle = vehicles[vehicleIndex];

    image.src = vehicle['image'];
    vin.innerHTML = "VIN: " + vehicle['vin'];
    style.innerHTML = "Style: " + vehicle['style'];
    model.innerHTML = "Model: " + vehicle['model'];
    make.innerHTML = "Make: " + vehicle['make'];
    mileage.innerHTML = "Mileage: " + vehicle['mileage'];
    cost.innerHTML = "Cost Per Day: $" + vehicle['dailyCost'];

    updateTotal();
}

// Submission data
var form = document.getElementById('reserve-form');
var total = document.getElementById('total');

var formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD'
});

var tomorrow = new Date();

tomorrow.setDate(tomorrow.getDate() + 1);

form['pickup'].min = tomorrow.getFullYear() + "-"
    + (tomorrow.getMonth() < 9 ? "0" : "") + (tomorrow.getMonth() + 1) + "-"
    + (tomorrow.getDate() < 10 ? "0" : "") + tomorrow.getDate();

form['returned'].min = tomorrow.getFullYear() + "-"
    + (tomorrow.getMonth() < 9 ? "0" : "") + (tomorrow.getMonth() + 1) + "-"
    + (tomorrow.getDate() < 9 ? "0" : "") + (tomorrow.getDate() + 1);

function updateTotal() {
    var amount = vehicles[vehicleIndex]['dailyCost'];
    var pickup = new Date(form['pickup'].value);
    var returned = new Date(form['returned'].value);

    if (!isNaN(pickup) && !isNaN(returned)) {
        var diff = returned.getTime() - pickup.getTime();

        amount *= diff / (1000 * 3600 * 24);
    }

    amount += Number(form['seats'].value) * 9.99;

    if (form['gps'].checked) {
        amount += 13.99;
    }

    total.innerHTML = "Total: " + formatter.format(amount);
}

form.addEventListener('submit', (event) => {
    event.preventDefault();

    if (providerId === -1) {
        alert("Please select a pickup location");
        return;
    }

    var reserve = new XMLHttpRequest();

    reserve.onreadystatechange = function () {
        if (reserve.readyState === 4) {
            console.log(reserve.status);
        }
    }

    reserve.open("POST", "/api/reservation");
    reserve.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    reserve.send([
        encodeURIComponent("location") + '=' + encodeURIComponent(providerId),
        encodeURIComponent("vin") + '=' + encodeURIComponent(vehicles[vehicleIndex]['vin']),
        encodeURIComponent("pickup") + '=' + encodeURIComponent(form['pickup'].value),
        encodeURIComponent("returned") + '=' + encodeURIComponent(form['returned'].value),
        encodeURIComponent("seats") + '=' + encodeURIComponent(form['seats'].value),
        encodeURIComponent("gps") + '=' + encodeURIComponent(form['gps'].checked)
    ].join('&'));
});