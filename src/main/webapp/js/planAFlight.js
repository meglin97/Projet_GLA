$(function () {
    $('#plan_flight_form').on('submit', function(event){
		event.preventDefault();

        const currentUser = JSON.parse(sessionStorage.getItem("current_user"));
        console.log(currentUser);
		
		let data = {
            departureAirfield: document.getElementById("departure_airfield").value,
            arrivalAirfield: document.getElementById("arrival_airfield").value,
            departureDate: document.getElementById("departure_date").value,
            arrivalDate: document.getElementById("arrival_date").value,
            numberPlaces: document.getElementById("nb_places").value,
            pilot: currentUser.pilotID,
            ticketPrice: document.getElementById("ticket_price").value
		};

		$.ajax({
			type: 'PUT',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(data),
			url: "/ws/coavionnage/flights/add"
		}).done((response)=>{
			console.log(response);
			window.location.href = "/home.html";
		});
	});
})