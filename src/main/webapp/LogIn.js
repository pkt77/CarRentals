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
    if(!customerName || !customerPassword)
    {
        document.getElementById("msg").innerHTML="Please fill out all the fields";
    }
    else
    {
            let customerObject={
            username : customerName,
            password :customerPassword
        };
        console.log("Customers username is "+customerObject.userName);
        console.log("Customers password is "+customerObject.password);
        
        let xhttp= new XMLHttpRequest();
        let jsonString="";
        let url="http://localhost:8080/CarRentals/api/login"
        //let url="https://pokeapi.co/api/v2/pokemon/"

        xhttp.open("POST",url);
        //console.log("Check");
        
        
    
        console.log("Changing ready state "+xhttp.readyState);
        xhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        //xhttp.setRequestHeader("username",customerName);
        //xhttp.setRequestHeader("password",customerPassword);


        xhttp.onreadystatechange=function()
        {
            console.log("Changing ready state "+xhttp.readyState);
            if(xhttp.readyState==4 && xhttp.status==200)
            {
    
    
                jsonString= JSON.stringify(customerObject);
                console.log("Done");
                
                
            
            }
            
           
        }
        xhttp.send(customerObject);
        

    }
    
   

}



