$(async function () {
	const current_user = JSON.parse(sessionStorage.getItem("current_user"));
	const userId = current_user.userID;
	const pilot = await getPilot(current_user.userID);

	document.getElementById("firstname").value = current_user.firstname;
	document.getElementById("lastname").value = current_user.lastname;
	document.getElementById("email").value = current_user.email;
	document.getElementById("number").value = pilot.numberOfHoursFlights;
	document.getElementById("experience").value = pilot.experience;
	document.getElementById("qualifications").value = pilot.qualifications;

    $('#modify_pilot_form').on('submit', function(event){
		event.preventDefault();

		const numberOfHours = document.getElementById("number").value;
		const numberOfYears = document.getElementById("experience").value;
		const qualifications = document.getElementById("qualifications").value;

		let data = {
			userID: current_user.userID,
			firstname: document.getElementById("firstname").value,
			lastname: document.getElementById("lastname").value,
			password: document.getElementById("password").value,
			email: document.getElementById("email").value
		};

		const confirm_password = document.getElementById("re_pass").value;
		if(data.password != confirm_password){
			$.notify("Your passwords do not match", { className: "error"});
			return;
		}

		$.ajax({
			type: 'POST',
			contentType: "application/json",
			data: JSON.stringify(data),
			url: "/ws/coavionnage/users/edit"
		}).done((response)=>{
			console.log(response);

			$.ajax({
				type: 'POST',
				contentType: "application/json",
				url: "/ws/coavionnage/pilots/edit/" + userId + "?numberOfHours=" + numberOfHours + "&numberOfYears=" + numberOfYears + "&qualifications="+ qualifications
			}).done((response)=>{
				console.log(response);
				window.location.href = "/pilotInformations.html?pilotID=" + userId;
			}).catch((error)=>{
				$.notify(error.responseText, { className: "error"});
			});

			sessionStorage.setItem("current_user", JSON.stringify(response));

		}).catch((error)=>{
			$.notify(error.responseText, { className: "error"});
		});
	});
});

async function getPilot(userID) {
    let result;

    try {
        result = await $.ajax({
            url: "/ws/coavionnage/pilots/" + userID,
            type: 'GET',
        }).done((response)=>{
			console.log(response);
		}).catch((error)=>{
			console.error(error);
		});

        return result;
    } catch (error) {
        console.error(error);
    }
};
