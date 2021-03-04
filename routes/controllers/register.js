//To do: Connect this to register page and make sure everything works
// this function validates the form calls all other functions to check for validation 
function formValidation(){
var uid = document.registration.userid;
var passid = document.registration.passid;
var uname = document.registration.username;
var uadd = document.registration.address;
var ucountry = document.registration.country;
var uzip = document.registration.zip;
var uemail = document.registration.email;
var umsex = document.registration.msex;
var ufsex = document.registration.fsex; 
    if(length_validation(uid,5,12)){
        if(passid_validation(passid){
            if(email_validation(uemail)){
                if (submitForm()){

                }
            } 
        }
    }
    return false; 
}

//length_validationchecks if username is empty and will return an error if so
function length_validation(uid)
{
    var uid_len = uid.value.length;
    if (uid_len == 0){ //check if zero
        alert("User Id cannot be empty");
        uid.focus();
        return false;
    }
    return true;
}

//this function validates the password requirements
function passid_validation(passid)
{
    var passid_len = passid.value.length;
    //check if password is empty
    if (passid_len == 0 ){
        alert("Password cannot be empty");
        passid.focus();
        return false;
    }
    return true;
}

//this function validates email format
function email_validation(uemail)
{
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if(uemail.value.match(mailformat)){
        return true;
    }
    else{
        alert("Email address is invalid");
        uemail.focus();
        return false;
    }
}
//Submits form if everything is true (idk if needed?)
function submitForm(){
    alert('Form Submitted');
    window.location.reload()
    return true;
}