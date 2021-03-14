
function getServerData(url, success) {
	$.ajax({
		type: 'GET',
		dataType: "json",
		url: url
	}).done(success);
}

function postServerData(url, data, success) {
	$.ajax({
		type: 'POST',
		dataType: "json",
		data: data,
		contentType:'application/json',
		url: url
	}).done(success);
}

function putServerData(url, data, success) {
	$.ajax({
		type: 'PUT',
		dataType: "json",
		data: data,
		contentType:'application/json',
		url: url
	}).done(success);
}

function deleteServerData(url, success) {
	$.ajax({
		type: 'DELETE',
		dataType: "json",
		url: url
	}).done(success);
}


function callDone(result) {
	var templateExample = _.template($('#templateExample').html());

	var html = templateExample({
		"attribute": JSON.stringify(result)
	});

	$("#result").append(html);
}


$(function () {
	$("#View flights").click(function () {
		getServerData("ws/coavionnage/flights", callDone);
	});

	$("Users").click(function () {
		getServerData("ws/coavionnage/flights/users", callDone);
	});

	$("#button2").click(function () {
		putServerData("ws/coavionnage/flights/users/add",JSON.stringify(data), callDone);
	});

	$("#button2").click(function () {
		postServerData("ws/coavionnage/flights/users/modify",JSON.stringify(data), callDone);
	});

	$("#button2").click(function () {
		deleteServerData("ws/coavionnage/flights/users/delete",JSON.stringify(data), callDone);
	});

	$("#Search").click(function () {
		getServerData("ws/coavionnage/flights/bookings", callDone);
	});

	$("#button").click(function () {
		putServerData("ws/coavionnage/flights/bookings/add",JSON.stringify(data), callDone);
	});

	
	$("#button2").click(function () {
		postServerData("ws/coavionnage/flights/bookings/modify",JSON.stringify(data), callDone);
	});

	$("#button2").click(function () {
		deleteServerData("ws/coavionnage/flights/bookings/delete",JSON.stringify(data), callDone);
	});

	
	
	$('#register-form').on('submit', function(event){
		event.preventDefault();
		
		console.log("form submitted");

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
			url: "/ws/coavionnage/users/signup"
		}).done((response)=>{
			console.log(response);
		});
	});
	
});