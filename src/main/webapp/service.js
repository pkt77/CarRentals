let display="";
let display2="";
let display3="";
let flag=0;
document.getElementById("nextButton").addEventListener("click",displayNextVehicles);
document.getElementById("previousButton").addEventListener("click",displayPreviousVehicles);


function displayVehicles()
{
    if(flag==0)
    {
        console.log("Checking0");
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
        
        display =  
        '<p> VIN: '+vehicleObject[flag].vin+'</p>'+
        '<p> Style: '+vehicleObject[flag].style+'</p>'+
        '<p> MODEL: '+vehicleObject[flag].model+'</p>'+
        '<p> MAKE: '+vehicleObject[flag].make+'</p>'+
        '<p> MILEAGE: '+vehicleObject[flag].mileage+'</p>'+
        '<p> RENT: '+vehicleObject[flag].dailyCost+'</p>'
        ;

         document.getElementById("myDiv").innerHTML=display;
         }
        
        
      }
    }
    
    
    
    

}
function displayNextVehicles()
{
    if(flag<12)
    {
    flag++;
    console.log("Checking0");
    let xhttp= new XMLHttpRequest();
    let url="http://localhost:8080/CarRentals/api/vehicles";
    xhttp.open("GET",url);
    xhttp.send();
    console.log(xhttp.readyState);
    xhttp.onreadystatechange=function()
    {
        if(xhttp.readyState==4 && xhttp.status==200)
    {
        console.log("value of flag in Next function  is  "+flag);
        let vehicleObject=JSON.parse(xhttp.responseText);
        console.log("Checking1");
        
        console.log("Checking");
        let imageName=document.getElementById("myImg");
        imageName.setAttribute("src",vehicleObject[flag].image);
        
        display2 =  
        '<p> VIN: '+vehicleObject[flag].vin+'</p>'+
        '<p> Style: '+vehicleObject[flag].style+'</p>'+
        '<p> MODEL: '+vehicleObject[flag].model+'</p>'+
        '<p> MAKE: '+vehicleObject[flag].make+'</p>'+
        '<p> MILEAGE: '+vehicleObject[flag].mileage+'</p>'+
        '<p> RENT: '+vehicleObject[flag].dailyCost+'</p>'
        ;

        document.getElementById("myDiv").innerHTML=display2;

        }
        
        
    }
   }
    
    
    

}
function displayPreviousVehicles()
{
    if(flag >0)
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
        '<p> VIN: '+vehicleObject[flag].vin+'</p>'+
        '<p> Style: '+vehicleObject[flag].style+'</p>'+
        '<p> MODEL: '+vehicleObject[flag].model+'</p>'+
        '<p> MAKE: '+vehicleObject[flag].make+'</p>'+
        '<p> MILEAGE: '+vehicleObject[flag].mileage+'</p>'+
        '<p> RENT: '+vehicleObject[flag].dailyCost+'</p>';

        document.getElementById("myDiv").innerHTML=display3;

        }
        
        
    }        
    }
    
    
    

}
displayVehicles();
