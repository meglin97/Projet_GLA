$(function () {

	const current_user = JSON.parse(sessionStorage.getItem("current_user"));
	document.getElementById("firstname").value = current_user.firstname;
	document.getElementById("lastname").value = current_user.lastname;
	document.getElementById("email").value = current_user.email;

    $('#modify-user-form').on('submit', function(event){
		event.preventDefault();
		
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
			sessionStorage.setItem("current_user", JSON.stringify(response));
			window.location.href = "/userProfile.html";
		}).catch((error)=>{
			$.notify(error.responseText, { className: "error"});
		});
	});
})