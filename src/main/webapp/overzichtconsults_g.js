window.onload = function initPage(){
	var gebruiker = localStorage.getItem("gebruikersnaam");
	$.getJSON("http://localhost:8080/melanoomapp/restservices/moedervlekken/getvoltooidesessie/"+gebruiker, function(response) {
	console.log(response);
$.each(response, function(z, y) {
	
		$("#consult").append(
				"<tr><td>" + y.sessieid + "   </td>" + "<td>" + y.moedervlekid
						+ "   </td>" + "<td>" + y.datum + "</td>"
						+ "<td><button value="
						+ z + " id=GET" + z + ">Overzicht</button></td></tr>");
		var Buttonselect = document.querySelector(("#GET" + z));
		Buttonselect.addEventListener("click", overzicht);

	});
});
}

function openNav() {
    document.getElementById("mySidenav").style.display = "block";
}
function closeNav() {
    document.getElementById("mySidenav").style.display = "none";
}
function overzicht() {
	var index = JSON.parse(event.target.value);
	var gebruiker = localStorage.getItem("gebruikersnaam");
			
	$.getJSON("http://localhost:8080/melanoomapp/restservices/moedervlekken/getvoltooidesessie/"+gebruiker, function(
			data) {
		$.each(data, function(z, y) {
			if (Number(z) == Number(index)) {
				 localStorage.setItem("Psessieid", y.sessieid);
				 document.location.href = "http://localhost:8080/melanoomapp/behandeling_g.html";
			}
		});
	});
}