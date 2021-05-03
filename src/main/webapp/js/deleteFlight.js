$(async function () {
	const current_user = JSON.parse(sessionStorage.getItem("current_user"));

    $('#delete_flight_form').on('submit', async function(event){
		event.preventDefault();

		const flightID = document.getElementById("flight_id").value;
		const flight = await getFlightInfo(flightID);

		if (!flight || flight.pilot != current_user.userID){
			$.notify("Cannot delete: The flight does not exist or you are not the pilot of this flight", { className: "error"});
			return null;
		}

		$.ajax({
			type: 'DELETE',
			url: "/ws/coavionnage/flights/delete/" + flightID
		}).done((response)=>{
			console.log(response);
			window.location.href = "/home.html";
		}).catch((error)=>{
			// console.log(error);
			$.notify(error.responseText, { className: "error"});
			window.location.href = "/home.html";
		});
	});
})

async function getFlightInfo(flightID) {
    let result;

    try {
        result = await $.ajax({
            url: "ws/coavionnage/flights/" + flightID,
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