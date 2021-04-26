formID.addEventListener('submit',(Event)=>
        {
               Event.preventDefault();
               //console.log("check");
        }
        );





window.onload=function()
{
            
    document.getElementById("previousButton").addEventListener("click",redirectPreviousPage);
    document.getElementById("finishButton").addEventListener("click",passwordCheck);
            
}        

function redirectPreviousPage()
{
    var opened = window.open("ThirdSignUp.html");

}
function passwordCheck()
{

    let customerUserName= document.getElementById("userName").value;
    let customerPassword= document.getElementById("password").value;
    let confirmedPassword= document.getElementById("confirmation").value;
    //console.log(customerPassword);
    //console.log(confirmedPassword);

    

    if(!customerUserName  || !customerPassword  ||!confirmedPassword )
    {
        document.getElementById("msg").innerHTML="Please fill out all the fields";
        

    }
    else
    {
        if(customerPassword===confirmedPassword)
        {
            console.log("Matched");
            var opened=window.open("RegistrationComplete.html");
        
        }
        else
        {
            console.log("Didn't match");
            document.getElementById("msg").innerHTML="Password didn't match. Please try again !";
        }
        

    }

    
    
}