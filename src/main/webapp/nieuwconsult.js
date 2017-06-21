window.onload = function initPage() {
	
	var Buttonopslaan = document.querySelector("#versturen");
	Buttonopslaan.addEventListener("click", consultversturen);
	
	var text = document.querySelector("#srca");
	text.addEventListener("keyup", imageset);
		
}

function imageset() {
	console.log(event.target.value);
    document.getElementById("imagesrc").src = event.target.value;
}

function consultversturen(){	
	gebruikersnaam = localStorage.getItem("gebruikersnaam");
	var data = $("#vragenlijst").serialize();
	$.post("https://ipass-melanoomapp.herokuapp.com/restservices/moedervlekken/dvragenlijst/"+ gebruikersnaam, data, function(response) {
		alert(response.message);
	});
	document.location.href = "https://ipass-melanoomapp.herokuapp.com/home.html";
}

function openNav() {
    document.getElementById("mySidenav").style.display = "block";
}
function closeNav() {
    document.getElementById("mySidenav").style.display = "none";
}
	
	