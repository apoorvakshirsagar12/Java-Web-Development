function w3_open() 
{
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() 
{
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}

function validate_login()
{
    var uname=document.getElementById("txtUname").value;
    var passwd=document.getElementById("txtPasswd").value;
    
    if (uname.equals("") || passwd.equals(""))
        
        alert("Please fill all the fields !");
        return false;
}