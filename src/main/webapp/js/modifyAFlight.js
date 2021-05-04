$(function () {
    $('#plan_flight_form').on('submit', async function(event){
		event.preventDefault();

		const departure_date = document.getElementById("departure_date").value;
		const departure_time = document.getElementById("departure_time").value;
		const arrival_date = document.getElementById("arrival_date").value;
		const arrival_time = document.getElementById("arrival_time").value;

        const current_user = JSON.parse(sessionStorage.getItem("current_user"));
        const flightID = document.getElementById("flight_id").value;
		const flight = await getFlightInfo(flightID);

		if (!flight || flight.pilot != current_user.userID){
			$.notify("Cannot modify: The flight does not exist or you are not the pilot of this flight", { className: "error"});
			return null;
		}
		
		let data = {
			flightID: flightID,
            departureAirfield: document.getElementById("departure_airfield").value,
            arrivalAirfield: document.getElementById("arrival_airfield").value,
            departureDate: departure_date + "T" + departure_time + ":00.000+02:00",
            arrivalDate: arrival_date + "T" + arrival_time + ":00.000+02:00",
            numberPlaces: document.getElementById("nb_places").value,
            ticketPrice: document.getElementById("ticket_price").value
		};

		$.ajax({
			type: 'POST',
			contentType: "application/json",
			data: JSON.stringify(data),
			url: "/ws/coavionnage/flights/edit"
		}).done((response)=>{
			console.log(response);
			window.location.href = "/home.html";
		}).catch((error)=>{
			$.notify(error.responseText, { className: "error"});
		});
	});
})

$('#departure_date').datetimepicker({
	format:'Y-m-d',
	timepicker:false
});

$('#departure_time').datetimepicker({
	format:'H:i',
	datepicker:false
});

$('#arrival_date').datetimepicker({
	format:'Y-m-d',
	timepicker:false
});

$('#arrival_time').datetimepicker({
	format:'H:i',
	datepicker:false
});

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