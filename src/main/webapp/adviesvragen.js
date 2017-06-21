window.onload = function initPage(){
    gebruikersnaam = localStorage.getItem("gebruikersnaam");
    
    var Buttonopslaan = document.querySelector("#nieuwconsult");
	Buttonopslaan.addEventListener("click", nieuwconsult);
	
console.log(gebruikersnaam);
$.post("http://localhost:8080/melanoomapp/restservices/moedervlekken/getableselect/" + gebruikersnaam, function(response) {
$.each(response, function(z, y) {
		$("#moedervlekken").append(
				"<tr><td>" + y.moedervlekid + "   </td>" + "<td>" + y.locatie
						+ "   </td>" + "<td>" + y.groote + "   </td>" + "<td>" + y.laastedatum + "   </td>"
						+ "</tr>");
		//var Buttonselect = document.querySelector(("#GET" + z));
		//Buttonselect.addEventListener("click", getoverzicht);

	});
});
}

function nieuwconsult(){
	document.location.href = "http://localhost:8080/melanoomapp/nieuwconsult.html";
}

function openNav() {
    document.getElementById("mySidenav").style.display = "block";
}
function closeNav() {
    document.getElementById("mySidenav").style.display = "none";
}