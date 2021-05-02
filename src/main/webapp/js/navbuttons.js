$(function () {
    const current_user = JSON.parse(sessionStorage.getItem("current_user"));
	if(current_user == null || current_user == undefined){ // user is not logged in
		document.getElementById("btn-logout").style.display="none";
        document.getElementById("btn-delete-flight").style.display="none";
        document.getElementById("btn-plan-flight").style.display="none";
        document.getElementById("btn-become-pilot").style.display="none";
        document.getElementById("btn-user-profile").style.display="none";
	} else { // user is logged in
		document.getElementById("btn-signup").style.display="none";  
		document.getElementById("btn-login").style.display="none";

        // if user is not pilot
        // document.getElementById("btn-delete-flight").style.display="none";
        // document.getElementById("btn-plan-flight").style.display="none";

        // if user is pilot
        // document.getElementById("btn-become-pilot").style.display="none";
	}

    document.getElementById("btn-logout").addEventListener("click", ()=>{
        sessionStorage.clear();
        window.location.href = "home.html"
    });
})