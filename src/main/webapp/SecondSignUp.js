formID.addEventListener('submit',(Event)=>
        {
               Event.preventDefault();
               //console.log("check");
        }
        );
window.onload=function()
{

    document.getElementById("previousButton").addEventListener("click",redirecPreviousPage);                
    document.getElementById("nextButton").addEventListener("click",redirecNextPage);
                    
}

function redirecPreviousPage ()
{
    var opened=window.open("SignUp.html");
}

function redirecNextPage()
{

    let customerStreetName= document.getElementById("street").value;
    let customerCityName= document.getElementById("city").value;
    let customerStateName= document.getElementById("state").value;
    let customerPostalCode= document.getElementById("postal").value;
    
    

    

    if(!customerStreetName  || !customerCityName  || !customerStateName || !customerPostalCode)
    {
        document.getElementById("msg").innerHTML="Please fill out all the fields";
        

    }
    else
    {

        var opened = window.open("ThirdSignUp.html");

    }
    

}