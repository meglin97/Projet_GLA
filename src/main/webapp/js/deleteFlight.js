$(function () {
    $('#delete_flight_form').on('submit', function(event){
		event.preventDefault();

        const currentUser = JSON.parse(sessionStorage.getItem("current_user"));
        console.log(currentUser);
		
		const flightID = document.getElementById("flight_id").value;

		$.ajax({
			type: 'DELETE',
			url: "/ws/coavionnage/flights/delete/" + flightID
		}).done((response)=>{
			console.log(response);
			window.location.href = "/home.html";
		});
	});
})