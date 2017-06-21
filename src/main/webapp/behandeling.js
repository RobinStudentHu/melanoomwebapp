window.onload = function initPage(){
	var sessie = localStorage.getItem("Bsessieid")
    $.getJSON("https://ipass-melanoomapp.herokuapp.com/restservices/moedervlekken/getbehandelinginfo/"+sessie, function(response) {
	console.log(response);
	document.getElementById('sessieid').append(" " + response.sessieid);
	document.getElementById('gebruikersnaam').append(" " + response.gebruikersnaam );
	document.getElementById('moedervlekid').append(" " + response.moedervlekid);
	document.getElementById('iseerste').append(" " + response.isEerste);
	
	var Buttonopslaan = document.querySelector("#opslaan");
	Buttonopslaan.addEventListener("click", behandelingopslaan);
	
	var div = document.getElementById('afbeelding');
	var diva = document.getElementById('vragenlijst');
	div.innerHTML = '<img src=' + response.afbeelding + ' id="imagesrc">';
   
	document.getElementById('vragenlijst').append(response.vraag1 + ": " + response.antwoord1); 
	diva.innerHTML = diva.innerHTML + "<br>";
	document.getElementById('vragenlijst').append(response.vraag2 + ": " + response.antwoord2); 
	diva.innerHTML = diva.innerHTML + "<br>";
	document.getElementById('vragenlijst').append(response.vraag3 + ": " + response.antwoord3); 
	diva.innerHTML = diva.innerHTML + "<br>";
	document.getElementById('vragenlijst').append(response.vraag4 + ": " + response.antwoord4); 
	diva.innerHTML = diva.innerHTML + "<br>";
	document.getElementById('vragenlijst').append(response.vraag5 + ": " + response.antwoord5); 
	diva.innerHTML = diva.innerHTML + "<br>";
	document.getElementById('vragenlijst').append(response.vraag6 + ": " + response.antwoord6); 
	diva.innerHTML = diva.innerHTML + "<br>";
	document.getElementById('vragenlijst').append(response.vraag7 + ": " + response.antwoord7); 
	diva.innerHTML = diva.innerHTML + "<br>";
	document.getElementById('vragenlijst').append(response.vraag8 + ": " + response.antwoord8); 
	
	
    });
}

function openNav() {
    document.getElementById("mySidenav").style.display = "block";
}
function closeNav() {
    document.getElementById("mySidenav").style.display = "none";
}
function behandelingopslaan(){
	var sessie = localStorage.getItem("Bsessieid");
	var deskundige = localStorage.getItem("gebruikersnaam");
	var data = $("#behandeling").serialize();
	
	$.post("https://ipass-melanoomapp.herokuapp.com/restservices/moedervlekken/verzendbehandeling/"+sessie+"/"+deskundige, data, function() {
		alert("Aanvraag behandelt");
		document.location.href = "https://ipass-melanoomapp.herokuapp.com/home_deskundige.html";
	});
}
