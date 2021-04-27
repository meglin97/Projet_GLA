
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

	$("#button2").click(function () {

		putServerData("ws/coavionnage/flights/add",JSON.stringify(data), callDone);
	});


	$("Users").click(function () {
		getServerData("ws/coavionnage/users", callDone);
	});

	$("#button2").click(function () {
		firstName = $('input[name="firstName"]').val();
        lastName = $('input[name="lastName"]').val();
        mail = $('input[name="email"]').val();
        password = $('input[name="password"]').val();
        experience = $('input[name="experience"]').val();
        qualification = $('input[name="qualifications"]').val();
        number_of_flight_hours = $('input[name="nbFlightHours"]').val();
		data = {
            "firstName":firstName,
            "lastName":lastName,
            "mail":mail,
            "password":password,
            "experience":experience,
            "qualification":qualification,
            "number_of_flight_hours":number_of_flight_hours
        }
		putServerData("ws/coavionnage/users/add",JSON.stringify(data), callDone);
	});

	$("#Search").click(function () {
		getServerData("ws/coavionnage/flights/bookings", callDone);
	});

	$("#button").click(function () {
		putServerData("ws/coavionnage/flights/bookings/add",JSON.stringify(data), callDone);
	});

	console.log(JSON.parse(sessionStorage.getItem("current_user")));
});

function getAllFlights() {
    $.ajax({
		type: 'GET',
		dataType: "json",
		url: "ws/coavionnage/flights"
	}).done((response)=>{
        console.log(response);
        updateFlightsList(response);
    });
}

function searchFlights() {
	const departure = document.getElementById("dep").value;
	const arrival = document.getElementById("arrival").value;
	const date = document.getElementById("date").value;
	const url = "ws/coavionnage/flights/search?departure=" + departure + "&arrival=" + arrival + "&departure_time=" + date;
	console.log(url);

	$.ajax({
		type: 'GET',
		dataType: "json",
		url: url
	}).done((response)=>{
        console.log(response);
        updateFlightsList(response);
    });
}

function updateFlightsList(flights) {
	const flightsDiv = document.querySelector("#flights-list");

	flightsDiv.innerHTML = "";

	if (!flights)
		return;

	flights.forEach(flight => {
		const flightRow = document.createElement("tr");
		const flDepDate = new Date(flight.departureDate);
		const flArrDate = new Date(flight.arrivalDate);

		let flightCol = document.createElement("td");
		flightCol.innerText = flight.flightID;
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		flightCol.innerText = flight.departureAirfield + "-" + flight.arrivalAirfield;
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		flightCol.innerText = flDepDate.getHours() + "h" + (flDepDate.getMinutes().toString().length == 1 ? 0 : "") + flDepDate.getMinutes();
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		flightCol.innerText = flight.departureAirfield;
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		flightCol.innerText = flArrDate.getHours() + "h" + (flArrDate.getMinutes().toString().length == 1 ? 0 : "") + flArrDate.getMinutes();
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		flightCol.innerText = flight.arrivalAirfield;
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		flightCol.innerText = flight.ticketPrice;
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		flightCol.innerText = flight.numberPlaces + " seats available";
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		btnPilot = document.createElement("a");
		btnPilot.href = "/pilotInformations.html?pilotID=" + flight.pilot;
		btnPilot.innerHTML = "Pilot";
		flightCol.append(btnPilot);
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		btnBook = document.createElement("a");
		btnBook.href = "/bookFlight.html?flightID=" + flight.flightID;
		btnBook.innerHTML = "Book";
		flightCol.append(btnBook);
		flightRow.append(flightCol);

		flightsDiv.append(flightRow);
	});
}

$(document).ready(function(){
    getAllFlights();
});

$("#btn-search").click(()=>{
	searchFlights();
})