$(document).ready(async function(){
    const urlParams = new URLSearchParams(window.location.search);
    const pilotID = urlParams.get('pilotID');

    const currentPilot = await getPilotInfo(pilotID);
    document.getElementById("pilot_first_name").innerHTML = currentPilot.firstname
    document.getElementById("pilot_last_name").innerHTML = currentPilot.lastname
    document.getElementById("pilot_email").innerHTML = currentPilot.email
    document.getElementById("pilot_experience").innerHTML = currentPilot.experience
    document.getElementById("pilot_qualifications").innerHTML = currentPilot.qualifications
    document.getElementById("pilot_nb_flight_hours").innerHTML = currentPilot.numberOfHoursFlights
});

async function getPilotInfo(pilotID) {
    let result;

    try {
        result = await $.ajax({
            url: "ws/coavionnage/pilots/" + pilotID,
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

