window.onload = function initPage(){
    gebruikersnaam = localStorage.getItem("gebruikersnaam");
    
    var Buttonopslaan = document.querySelector("#nieuwconsult");
	Buttonopslaan.addEventListener("click", nieuwconsult);
	
console.log(gebruikersnaam);
$.post("https://ipass-melanoomapp.herokuapp.com/restservices/moedervlekken/getableselect/" + gebruikersnaam, function(response) {
$.each(response, function(z, y) {
		$("#moedervlekken").append(
				"<tr><td>" + y.moedervlekid + "   </td>" + "<td>" + y.locatie
						+ "   </td>" + "<td>" + y.groote + "   </td>" + "<td>" + y.laastedatum + "   </td>"
						+ "</tr>");

	});
});
}

function nieuwconsult(){
	document.location.href = "https://ipass-melanoomapp.herokuapp.com/nieuwconsult.html";
}

function openNav() {
    document.getElementById("mySidenav").style.display = "block";
}
function closeNav() {
    document.getElementById("mySidenav").style.display = "none";
}