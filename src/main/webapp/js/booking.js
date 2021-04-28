$(function () {
    $('#book_flight_form').on('submit', function(event){
        event.preventDefault();
        
        const urlParams = new URLSearchParams(window.location.search);
		const currentUser = JSON.parse(sessionStorage.getItem("current_user"));

		const data = {
			flightID: urlParams.get('flightID'),
			user: currentUser.userID,
			status: "pending_response"
		};

        console.log(data);
        
        const placeNumber = document.getElementById("number").value;
        
        $.ajax({
            type: 'PUT',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(data),
            url: "/ws/coavionnage/bookings/add/" + placeNumber
        }).done((response)=>{
            console.log(response);
            $.notify(response.message, { className: "success"});
            // window.location.href = "/home.html";
        }).catch((error)=>{
            console.log(error);
            $.notify(error.message, { className: "error"});
        });
    });
})