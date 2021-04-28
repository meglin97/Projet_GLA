$(document).ready(function(){
    const urlParams = new URLSearchParams(window.location.search);
    const pilotID = urlParams.get('pilotID');
    console.log("pilotID: " + pilotID);

    getPilotInfo(pilotID);
});

function getPilotInfo(pilotID) {
    // fait requête au webservice pour récupérer les infos du pilote et ensuite afficher
}

