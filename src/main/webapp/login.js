window.onload = function initPage() {
	var Buttonopslaan = document.querySelector("#login");
	Buttonopslaan.addEventListener("click", inloggen);
}

function inloggen() {
	var data = $("#inloggen").serialize();
	console.log(data);
	$.post("https://ipass-melanoomapp.herokuapp.com/restservices/moedervlekken/inloggen", data, function(response) {
		var check = response.check
		if (check) {
	    localStorage.setItem("gebruikersnaam", response.gebruiker);
	    if (response.gebruiker == "Admin"){
		document.location.href = "https://ipass-melanoomapp.herokuapp.com/home_deskundige.html";
		}
	    else{
	     document.location.href = "https://ipass-melanoomapp.herokuapp.com/home.html";
	    }
		}
		else {
			alert (response.message);
		}
		});		
}