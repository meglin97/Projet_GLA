
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
		putServerData("ws/coavionnage/users/add",JSON.stringify(data), callDone);
	});

	$("#Search").click(function () {
		getServerData("ws/coavionnage/flights/bookings", callDone);
	});

	$("#button").click(function () {
		putServerData("ws/coavionnage/flights/bookings/add",JSON.stringify(data), callDone);
	});

	console.log(sessionStorage.getItem("current_user_id"));
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
        
	flights.forEach(flight => {
		const flightRow = document.createElement("tr");

		let flightCol = document.createElement("td");
		flightCol.innerText = flight.flightID;
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		flightCol.innerText = flight.departureAirfield + "-" + flight.arrivalAirfield;
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		flightCol.innerText = flight.departureTime.hour + "h" + flight.departureTime.minute;
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		flightCol.innerText = flight.departureAirfield;
		flightRow.append(flightCol);

		flightCol = document.createElement("td");
		flightCol.innerText = flight.arrivalTime.hour + "h" + flight.arrivalTime.minute;
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

		flightsDiv.append(flightRow);
	});
}

$(document).ready(function(){
    getAllFlights();
});

$("#btn-search").click(()=>{
	searchFlights();
})