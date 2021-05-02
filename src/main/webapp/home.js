$(function () {
	const current_user = JSON.parse(sessionStorage.getItem("current_user"));
	console.log(current_user);
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

async function getFlightBookingsNumber(flightID) {
	let result;

    try {
        result = await $.ajax({
            url: "ws/coavionnage/bookings/flightBookings/" + flightID,
            type: 'GET',
            dataType: "json"
        }).done((response)=>{
			console.log(response);
		}).catch((error)=>{
			console.error(error);
		});

        return result;
    } catch (error) {
        console.error(error);
    }
}

async function calculateSeatsAvailable(flightID, numberPlaces) {
	const bookingsNumber = await getFlightBookingsNumber(flightID);
	return parseInt(numberPlaces) - parseInt(bookingsNumber);
}

function updateFlightsList(flights) {
	const flightsDiv = document.querySelector("#flights-list");

	flightsDiv.innerHTML = "";

	if (!flights)
		return;

	flights.forEach(async flight => {
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
		flightCol.innerText = await calculateSeatsAvailable(flight.flightID, flight.numberPlaces) + " seat(s) available";
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
		btnBook.className = "btn-book";
		flightCol.append(btnBook);
		flightRow.append(flightCol);

		flightsDiv.append(flightRow);
	});
}

// récupère liste de vols à la fin du chargement de la page
$(document).ready(function(){
    getAllFlights();
});

$("#btn-search").click(()=>{
	searchFlights();
})