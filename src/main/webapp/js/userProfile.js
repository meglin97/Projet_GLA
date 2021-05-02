$(function () {
    const current_user = JSON.parse(sessionStorage.getItem("current_user"));
    
    document.getElementById("user_first_name").innerHTML = current_user.firstname
    document.getElementById("user_last_name").innerHTML = current_user.lastname
    document.getElementById("user_email").innerHTML = current_user.email

})