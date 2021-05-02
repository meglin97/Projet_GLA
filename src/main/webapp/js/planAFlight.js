$(function () {
    $('#plan_flight_form').on('submit', function(event){
		event.preventDefault();

		// 2021-02-16T00:00:00.000+01:00

		const departure_date = document.getElementById("departure_date").value;
		const departure_time = document.getElementById("departure_time").value;
		const arrival_date = document.getElementById("arrival_date").value;
		const arrival_time = document.getElementById("arrival_time").value;

        const current_user = JSON.parse(sessionStorage.getItem("current_user"));
        console.log(current_user);
		
		let data = {
            departureAirfield: document.getElementById("departure_airfield").value,
            arrivalAirfield: document.getElementById("arrival_airfield").value,
            departureDate: departure_date + "T" + departure_time + ":00.000+01:00",
            arrivalDate: arrival_date + "T" + arrival_time + ":00.000+01:00",
            numberPlaces: document.getElementById("nb_places").value,
            pilot: current_user.userID,
            ticketPrice: document.getElementById("ticket_price").value
		};

		$.ajax({
			type: 'PUT',
			contentType: "application/json",
			data: JSON.stringify(data),
			url: "/ws/coavionnage/flights/add"
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