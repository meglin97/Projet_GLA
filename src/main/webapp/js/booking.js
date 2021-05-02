$(function () {
    $('#book_flight_form').on('submit', function(event){
        event.preventDefault();
        
        const urlParams = new URLSearchParams(window.location.search);
		const current_user = JSON.parse(sessionStorage.getItem("current_user"));
        
        if (current_user == undefined || current_user == null ){ // user not logged in
            window.location.href = "/signUp.html";
        }

		const data = {
			flightID: urlParams.get('flightID'),
			user: current_user.userID,
			status: "pending_response"
		};

        const placeNumber = document.getElementById("number").value;
        
        $.ajax({
            type: 'PUT',
            contentType: "application/json",
            data: JSON.stringify(data),
            url: "/ws/coavionnage/bookings/add/" + placeNumber
        }).done((response)=>{
            console.log(response);
            window.location.href = "/home.html";  
        }).catch((error)=>{
			$.notify(error.responseText, { className: "error"});
		});
    });
})