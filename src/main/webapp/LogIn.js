registerForm.addEventListener('submit',(Event)=>
        {
               Event.preventDefault();
               //console.log("check");
        }
        );



window.onload=function()
{
    
    document.getElementById("buttonId").addEventListener("click",getValue);
    
}

function getValue()
{
    
    let customerName=document.getElementById("username").value;
    let customerPassword=document.getElementById("password").value;
    //console.log("user name is "+customerName);
    //console.log("user password is "+customerPassword);
    let xhttp= new XMLHttpRequest();
    let basePokeUrl="https://pokeapi.co/api/v2/pokemon/"
    xhttp.open("GET",basePokeUrl+customerName);
    xhttp.send();
    

    
    xhttp.onreadystatechange=function()
    {
        console.log("Changing ready state "+xhttp.readyState);
        if(xhttp.readyState==4 && xhttp.status==200)
        {


            let customerObject= JSON.parse(xhttp.responseText);
            randomFunction(customerObject,customerName,customerPassword);
        
        }
    }
   

}
function randomFunction(cusObject,userName,password)

{
    //console.log("Order is "+cusObject.order);
    //console.log("Name is "+cusObject.name);
    if(cusObject.name===userName && cusObject.order==password)
    {
        console.log("Milse");
        var opened = window.open("WelcomePage.html");
        //opened.document.write("Your HTML here");
    }
    else
    {
        console.log("Mile Nai");

        var opened = window.open("FailedLogIn.html");
        

    }
}



