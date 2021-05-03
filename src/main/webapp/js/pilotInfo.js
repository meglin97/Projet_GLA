$(document).ready(async function(){
    const urlParams = new URLSearchParams(window.location.search);
    const pilotID = urlParams.get('pilotID');

    const currentPilot = await getPilotInfo(pilotID);
    const user = await getUserInfo(pilotID);

    document.getElementById("pilot_first_name").innerHTML = user.firstname
    document.getElementById("pilot_last_name").innerHTML = user.lastname
    document.getElementById("pilot_email").innerHTML = user.email
    document.getElementById("pilot_experience").innerHTML = currentPilot.experience
    document.getElementById("pilot_qualifications").innerHTML = currentPilot.qualifications
    document.getElementById("pilot_nb_flight_hours").innerHTML = currentPilot.numberOfHoursFlights

    // $('#btn-delete-user').on('click', (event)=>{
    //     event.preventDefault();

    //     if (confirm('Are you sure you want to delete your profile?')) {
            
    //         $.ajax({
    //             type: 'DELETE',
    //             contentType: "application/json",
    //             url: "/ws/coavionnage/users/delete/" + currentPilot.userID
    //         }).done((response)=>{
    //             console.log(response);
    //             sessionStorage.clear();
    //             window.location.href = "/home.html";
    //         }).catch((error)=>{
    //             $.notify(error.responseText, { className: "error"});
    //         });

    //     } else {
    //         return null;
    //     }
    // })
    
});

async function getPilotInfo(pilotID) {
    let result;

    try {
        result = await $.ajax({
            url: "ws/coavionnage/pilots/" + pilotID,
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

async function getUserInfo(userID) {
    let result;

    try {
        result = await $.ajax({
            url: "ws/coavionnage/users/" + userID,
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
