$(function () {
    $('#become_pilot_form').on('submit', function(event){
		event.preventDefault();

		const currentUser = JSON.parse(sessionStorage.getItem("current_user"));
		const userId = currentUser.userID;

        let data = {
			numberOfHours: document.getElementById("number").value,
			numberOfYears: document.getElementById("experience").value,
			qualifications: document.getElementById("qualifications").value
		};

		$.ajax({
			type: 'PUT',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(data),
			url: "/ws/coavionnage/users/add/" + userId
		}).done((response)=>{
			console.log(response);
			// window.location.href = "/becomeAPilot.html";
		}).catch((error)=>{
			console.error(error);
		});
	});
})