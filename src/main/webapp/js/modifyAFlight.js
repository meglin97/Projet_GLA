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

		const flDepDate = new Date(flight.departureDate);
		const flArrDate = new Date(flight.arrivalDate);


		document.getElementById("departure_airfield").value = flight.departureAirfield;
		document.getElementById("arrival_airfield").value = flight.arrivalAirfield;
		document.getElementById("departure_date").value = flDepDate.getFullYear() + '-' + (flDepDate.getMonth().toString().length == 1 ? "0" : "") + flDepDate.getMonth() + '-' + (flDepDate.getDay().toString().length == 1 ? "0" : "") + flDepDate.getDay();
		document.getElementById("departure_time").value = (flDepDate.getHours().toString().length == 1 ? "0" : "") + flDepDate.getHours() + ':' + (flDepDate.getMinutes().toString().length == 1 ? "0" : "") + flDepDate.getMinutes();
		document.getElementById("arrival_date").value = flArrDate.getFullYear() + '-' + (flArrDate.getMonth().toString().length == 1 ? "0" : "") + flArrDate.getMonth() + '-' + (flArrDate.getDay().toString().length == 1 ? "0" : "") + flArrDate.getDay();
		document.getElementById("arrival_time").value = (flArrDate.getHours().toString().length == 1 ? "0" : "") + flArrDate.getHours() + ':' + (flArrDate.getMinutes().toString().length == 1 ? "0" : "") + flArrDate.getMinutes();
		document.getElementById("nb_places").value = flight.numberPlaces;
		document.getElementById("ticket_price").value = flight.ticketPrice;
	 });

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