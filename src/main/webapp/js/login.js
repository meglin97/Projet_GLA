$(function () {
    $('#login-form').on('submit', function(event){
		event.preventDefault();

		let data = {
			password: document.getElementById("password").value,
			email: document.getElementById("email").value
		};

		$.ajax({
			type: 'PUT',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(data),
			url: "/ws/coavionnage/users/login"
		}).done((response)=>{
			console.log(response);

            sessionStorage.setItem("current_user_id", response.userID);
            window.location.href = "/home.html";
		});
	});
})