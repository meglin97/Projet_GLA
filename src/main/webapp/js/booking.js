$(function () {
    $('#book_flight_form').on('submit', function(event){
		event.preventDefault();
		
		
        const placeNumber = document.getElementById("Number").value;
		
		$.ajax({
			type: 'PUT',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(data),
			url: "/ws/coavionnage/bookings/add?placeNumber=" + placeNumber
		}).done((response)=>{
			console.log(response);
			window.location.href = "/bookFlight.html";
		});
	});
})