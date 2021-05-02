$(function () {
    $('#become_pilot_form').on('submit', function(event){
		event.preventDefault();

		const current_user = JSON.parse(sessionStorage.getItem("current_user"));
		const userId = current_user.userID;

		const numberOfHours = document.getElementById("number").value;
		const numberOfYears = document.getElementById("experience").value;
		const qualifications = document.getElementById("qualifications").value;

		$.ajax({
			type: 'PUT',
			contentType: "application/json",
			url: "/ws/coavionnage/pilots/add/" + userId + "?numberOfHours=" + numberOfHours + "&numberOfYears=" + numberOfYears + "&qualifications="+ qualifications
		}).done((response)=>{
			console.log(response);
			window.location.href = "/home.html";
		}).catch((error)=>{
			$.notify(error.responseText, { className: "error"});
		});
	});
})