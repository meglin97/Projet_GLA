$(function () {
    $('#register-form').on('submit', function(event){
		event.preventDefault();
		
		let data = {
			name: document.getElementById("name").value,
			password: document.getElementById("password").value,
			email: document.getElementById("email").value
		};

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