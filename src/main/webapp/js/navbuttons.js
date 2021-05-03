$(async function () {
    const current_user = JSON.parse(sessionStorage.getItem("current_user"));

    const btnDeleteFlight = document.getElementById("btn-delete-flight");
    const btnPlanFlight = document.getElementById("btn-plan-flight");
    const btnModifyFlight = document.getElementById("btn-modify-flight");
    const btnBecomePilote = document.getElementById("btn-become-pilot");
    const btnProfile = document.getElementById("btn-profile");
    const btnModifyPiloteProfile = document.getElementById("btn-modify-pilot-profile");
    const btnSignup = document.getElementById("btn-signup");
    const btnLogin = document.getElementById("btn-login");
    const btnLogout = document.getElementById("btn-logout");
    let pilot = null;
    
	if (current_user == null || current_user == undefined){ // user is not logged in
        hideButton(btnLogout);
        hideButton(btnDeleteFlight);
        hideButton(btnPlanFlight);
        hideButton(btnModifyFlight);
        hideButton(btnBecomePilote);
        hideButton(btnProfile);
        hideButton(btnModifyPiloteProfile);
		
	} else { // user is logged in
		hideButton(btnSignup);
        hideButton(btnLogin);

        pilot = await getPilot(current_user.userID);
        if(!pilot.userID){
            // if user is not pilot
            hideButton(btnPlanFlight);
            hideButton(btnModifyFlight);
            hideButton(btnDeleteFlight);
            hideButton(btnModifyPiloteProfile);
        }else{
            // if user is pilot
            hideButton(btnBecomePilote);
        }        
	}

    if (btnLogout){
        btnLogout.addEventListener("click", ()=>{
            sessionStorage.clear();
            window.location.href = "home.html"
        });
    }

    if (btnProfile){
        btnProfile.addEventListener("click", () => {
            if(!pilot.userID){
                // if user is not pilot
                window.location.href = "userProfile.html";
            }else{
                // if user is pilot
                window.location.href = "pilotInformations.html?pilotID=" + pilot.userID;
            } 
        })
    }
})

function hideButton(button) {
    if (button){ 
        button.style.display="none"; 
    }
}

async function getPilot(userID) {
    let result;

    try {
        result = await $.ajax({
            url: "/ws/coavionnage/pilots/" + userID,
            type: 'GET',
        }).done((response)=>{
			// console.log(response);
		}).catch((error)=>{
			console.error(error);
		});

        return result;
    } catch (error) {
        console.error(error);
    }
}