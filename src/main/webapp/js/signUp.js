$(function () {
    $('#register-form').on('submit', function(event){
		event.preventDefault();
		
		let data = {
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
			type: 'PUT',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(data),
			url: "/ws/coavionnage/users/add"
		}).done((response)=>{
			console.log(response);
			$.notify("Account successfully created, please sign in", { className: "success"});
			// window.location.href = "/login.html";
		});
	});
})