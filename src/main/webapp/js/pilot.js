$(function () {
    $('#become_pilot_form').on('submit', function(event){
		event.preventDefault();
        
        const urlParams = new URLSearchParams(window.location.search);
        const pilotID = urlParams.get('pilotID');
        console.log("pilotID: " + pilotID);

        let data = {
			numberOfHours: document.getElementById("experience").value,
			numberOfYears: document.getElementById("qualifications").value,
			qualifications: document.getElementById("number").value
		};

		$.ajax({
			type: 'PUT',
			contentType: "application/json",
			dataType: "json",
			data: JSON.stringify(data),
			url: "/ws/coavionnage/users/add/1"
		}).done((response)=>{
			console.log(response);
			window.location.href = "/becomeAPilot.html";
		});
	});
})