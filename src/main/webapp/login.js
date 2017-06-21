window.onload = function initPage() {
	var Buttonopslaan = document.querySelector("#login");
	Buttonopslaan.addEventListener("click", inloggen);
}

function inloggen() {
	var data = $("#inloggen").serialize();
	console.log(data);
	$.post("/melanoomapp/restservices/moedervlekken/inloggen", data, function(response) {
		var check = response.check
		if (check) {
	    localStorage.setItem("gebruikersnaam", response.gebruiker);
	    if (response.gebruiker == "Admin"){
		document.location.href = "/melanoomapp/home_deskundige.html";
		}
	    else{
	     document.location.href = "/melanoomapp/home.html";
	    }
		}
		else {
			alert (response.message);
		}
		});		
}
		
//<div id="footer"><center><a href="http://localhost:8080/melanoomapp/home.html"><button id="login" name="login" >Login</button></a></center></div>