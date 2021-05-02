$(async function () {
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

        const pilot = await getPilot(current_user.userID);
        if(!pilot.userID){
            // if user is not pilot
            document.getElementById("btn-plan-flight").style.display="none";
            document.getElementById("btn-delete-flight").style.display="none";
        }else{
            // if user is pilot
            document.getElementById("btn-become-pilot").style.display="none";
        }        
	}

    document.getElementById("btn-logout").addEventListener("click", ()=>{
        sessionStorage.clear();
        window.location.href = "home.html"
    });
})

async function getPilot(userID) {
    let result;

    try {
        result = await $.ajax({
            url: "/ws/coavionnage/pilots/" + userID,
            type: 'GET',
        }).done((response)=>{
			console.log(response);
		}).catch((error)=>{
			console.error(error);
		});

        return result;
    } catch (error) {
        console.error(error);
    }
}