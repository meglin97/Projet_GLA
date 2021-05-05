$(function () {

	// quand la valeur flight id est modifié, on récupère le vol associé à cet ID et pré-remplit le formulaire
	$("#flight_id").on("input", async function() {
		const flightID = $(this).val();

		if (flightID == ''){
			return null;
		}

		const flight = await getFlightInfo(flightID);

		if (!flight || !flight.flightID){
			$.notify("Flight does not exist", { className: "error"});
			return null;
		}

		document.getElementById("departure_airfield").value = flight.departureAirfield;
		document.getElementById("arrival_airfield").value = flight.arrivalAirfield;
		document.getElementById("departure_date").value = flight.departureDate;
		document.getElementById("departure_time").value = flight.departureTime;
		document.getElementById("arrival_date").value = flight.arrivalDate;
		document.getElementById("arrival_time").value = flight.arrivalTime;
		document.getElementById("nb_places").value = flight.numberPlaces;
		document.getElementById("ticket_price").value = flight.ticketPrice;
	 });

    $('#plan_flight_form').on('submit', async function(event){
		event.preventDefault();

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
            departureDate: document.getElementById("departure_date").value,
			departureTime: document.getElementById("departure_time").value,
            arrivalDate: document.getElementById("arrival_date").value,
            arrivalTime: document.getElementById("arrival_time").value,
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