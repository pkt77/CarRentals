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

    var opened =window.open("SecondSignUp.html");
}

function redirecNextPage()
{

    let customerLicensetNumber= document.getElementById("License").value;
    let customerAuthority= document.getElementById("authority").value;
    let customerExpireDate= document.getElementById("expire").value;
    
    if(!customerLicensetNumber  || !customerAuthority  || !customerExpireDate)
    {
        document.getElementById("msg").innerHTML="Please fill out all the fields";
    }
    else
    {
        var opened = window.open("LastSignUp.html");
    }
    

}