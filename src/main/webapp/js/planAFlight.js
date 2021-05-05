$(function () {
    $('#plan_flight_form').on('submit', function(event){
		event.preventDefault();

        const current_user = JSON.parse(sessionStorage.getItem("current_user"));
        console.log(current_user);
		
		let data = {
            departureAirfield: document.getElementById("departure_airfield").value,
            arrivalAirfield: document.getElementById("arrival_airfield").value,
            departureDate:  document.getElementById("departure_date").value,
			departureTime:  document.getElementById("departure_time").value,
            arrivalDate:  document.getElementById("arrival_date").value,
            arrivalTime:  document.getElementById("arrival_time").value,
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