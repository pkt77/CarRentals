console.log("Hello");
let display="";
let display2="";
let display3="";
let flag=0;
document.getElementById("nextButton").addEventListener("click",displayNextProviders);
document.getElementById("previousButton").addEventListener("click",displayPreviousProviders);

function displayProviders()
{
    if(flag==0)
    {
        let xhttp= new XMLHttpRequest();
        let url="http://localhost:8080/CarRentals/api/providers";
        xhttp.open("GET",url);
        xhttp.send();
        xhttp.onreadystatechange=function()
        {
           
        
            if(xhttp.readyState==4 && xhttp.status==200)
            {
            
                let providerObject=JSON.parse(xhttp.responseText);
                 display =  
                '<p> ID: '+providerObject[flag].id+'</p>'+
                '<p> COMPANY: '+providerObject[flag].company+'</p>'+
                '<p> STREET: '+providerObject[flag].street+'</p>'+
                '<p> CITY: '+providerObject[flag].city+'</p>'+
                '<p> STATE: '+providerObject[flag].state+'</p>'+
                '<p> LATITUDE: '+providerObject[flag].lat+'</p>'+
                '<p> LONGITUDE: '+providerObject[flag].longitude+'</p>'
                ;
            
                 document.getElementById("myDiv").innerHTML=display;
             }
            
            
          }

    }
    
    

}
displayProviders();
function displayNextProviders()
{
    if(flag<56)
    {
        flag++;
        //console.log("Checking0");
        let xhttp= new XMLHttpRequest();
        let url="http://localhost:8080/CarRentals/api/providers";
        xhttp.open("GET",url);
        xhttp.send();
        //console.log(xhttp.readyState);
        xhttp.onreadystatechange=function()
        {
            if(xhttp.readyState==4 && xhttp.status==200)
            {
                //console.log("value of flag in Next function  is  "+flag);
                let providerObject=JSON.parse(xhttp.responseText);
                
                
                display2 =  
                '<p> ID: '+providerObject[flag].id+'</p>'+
                '<p> COMPANY: '+providerObject[flag].company+'</p>'+
                '<p> STREET: '+providerObject[flag].street+'</p>'+
                '<p> CITY: '+providerObject[flag].city+'</p>'+
                '<p> STATE: '+providerObject[flag].state+'</p>'+
                '<p> LATITUDE: '+providerObject[flag].lat+'</p>'+
                '<p> LONGITUDE: '+providerObject[flag].longitude+'</p>'
                ;
        
                document.getElementById("myDiv").innerHTML=display2;
        
            }
            
            
        }
    }
    

}
function displayPreviousProviders()
{
    if(flag>0)
    {
        flag--;
        console.log("Flag value inside previous function is "+flag);
        let xhttp= new XMLHttpRequest();
        let url="http://localhost:8080/CarRentals/api/vehicles";
        xhttp.open("GET",url);
        xhttp.send();
        console.log(xhttp.readyState);
        xhttp.onreadystatechange=function()
        {
            if(xhttp.readyState==4 && xhttp.status==200)
            {
                console.log("value of flag in if is  "+flag);
                let vehicleObject=JSON.parse(xhttp.responseText);
                console.log("Checking1");
                
                console.log("Checking");
                let imageName=document.getElementById("myImg");
                imageName.setAttribute("src",vehicleObject[flag].image);
                
                display3=  
                '<p> ID: '+providerObject[flag].id+'</p>'+
                '<p> COMPANY: '+providerObject[flag].company+'</p>'+
                '<p> STREET: '+providerObject[flag].street+'</p>'+
                '<p> CITY: '+providerObject[flag].city+'</p>'+
                '<p> STATE: '+providerObject[flag].state+'</p>'+
                '<p> LATITUDE: '+providerObject[flag].lat+'</p>'+
                '<p> LONGITUDE: '+providerObject[flag].longitude+'</p>'
                ;
                document.getElementById("myDiv").innerHTML=display3;
        
                
            }
        }

    }
} 


document.getElementById('reservationID').addEventListener('submit', (event) => {
    event.preventDefault();

    let customerPickupDate = event.target['PicupDate'].value;
    let customerReturnDate = event.target['returnDate'].value;
    let customerChildSeat= event.target['childSeat'].value;

    if (!customerPickupDate || !customerReturnDate || !customerChildSeat) 
    {
        document.getElementById("msg").innerHTML = "Please fill out all the fields";
        return;
    }
    
    let xhttp = new XMLHttpRequest();

    xhttp.open("POST", "/api/providers");

    console.log("Changing ready state " + xhttp.readyState);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4) {
            console.log(xhttp.status);
        }
    }

    xhttp.send([
        encodeURIComponent("PicupDate") + '=' + encodeURIComponent(customerPickupDate),
        encodeURIComponent("returnDate") + '=' + encodeURIComponent(customerReturnDate),
        encodeURIComponent("childSeat") + '=' + encodeURIComponent(customerChildSeat)
        

    ].join('&'));
}
);

