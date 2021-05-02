$(function () {
    const current_user = JSON.parse(sessionStorage.getItem("current_user"));
    
    document.getElementById("user_first_name").innerHTML = current_user.firstname
    document.getElementById("user_last_name").innerHTML = current_user.lastname
    document.getElementById("user_email").innerHTML = current_user.email

    $('#btn-delete-user').on('click', (event)=>{
        event.preventDefault();

        if (confirm('Are you sure you want to delete your profile?')) {
            
            $.ajax({
                type: 'DELETE',
                contentType: "application/json",
                url: "/ws/coavionnage/users/delete/" + current_user.userID
            }).done((response)=>{
                console.log(response);
                sessionStorage.clear();
                window.location.href = "/home.html";
            }).catch((error)=>{
                $.notify(error.responseText, { className: "error"});
            });

        } else {
            return null;
        }
    })
})