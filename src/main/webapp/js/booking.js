$(function () {
    $('#book_flight_form').on('submit', function(event){
        event.preventDefault();
        
        
        const placeNumber = document.getElementById("number").value;
        
        $.ajax({
            type: 'PUT',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(data),
            url: "/ws/coavionnage/bookings/add/" + placeNumber
        }).done((response)=>{
            console.log(response);
            window.location.href = "/bookFlight.html";
        });
    });
})