formID.addEventListener('submit',(Event)=>
        {
               Event.preventDefault();
               //console.log("check");
        }
        );
window.onload=function()
{
                    
    document.getElementById("nextButton").addEventListener("click",redirecNextPage);
                    
}

function redirecNextPage()
{

    let customerFirstName= document.getElementById("FirstName").value;
    let customerLastName= document.getElementById("LastName").value;
    let customerDOB= document.getElementById("DOB").value;
    let customerPhone=document.getElementById("phone").value;
    let customerEmail=document.getElementById("email").value;
    

    

    if(!customerFirstName  || !customerLastName  ||!customerDOB || !customerEmail || !customerPhone )
    {
        document.getElementById("msg").innerHTML="Please fill out all the fields";
        

    }
    else
    {

        var opened = window.open("SecondSignUp.html");

    }
    

}