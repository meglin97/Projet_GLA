$(function () {
    $('#login-form').on('submit', function(event){
		event.preventDefault();

		const password = document.getElementById("password").value;
		const email = document.getElementById("email").value;

		$.ajax({
			type: 'GET',
			url: "/ws/coavionnage/users/login?email=" + email + "&password=" + password
		}).done((response)=>{
			console.log(response);

			sessionStorage.setItem("current_user", JSON.stringify(response));
            window.location.href = "/home.html";
		});
	});
})