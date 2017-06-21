window.onload = function initPage(){
	
	$.getJSON("/melanoomapp/restservices/moedervlekken/getconsultlijst", function(response) {
	console.log(response);
$.each(response, function(z, y) {
		$("#consult").append(
				"<tr><td>" + y.sessieid + "   </td>" + "<td>" + y.gebruikersnaam
						+ "   </td>" + "<td>" + y.datum + "</td>"
						+ "<td><button value="
						+ z + " id=GET" + z + ">Consult behandelen</button></td></tr>");
		var Buttonselect = document.querySelector(("#GET" + z));
		Buttonselect.addEventListener("click", behandel);

	});
});
}

function openNav() {
    document.getElementById("mySidenav").style.display = "block";
}
function closeNav() {
    document.getElementById("mySidenav").style.display = "none";
}

function behandel() {
	var index = JSON.parse(event.target.value);

	$.getJSON("/melanoomapp/restservices/moedervlekken/getconsultlijst", function(
			data) {
		$.each(data, function(z, y) {
			if (Number(z) == Number(index)) {
				 localStorage.setItem("Bsessieid", y.sessieid);
				 document.location.href = "/melanoomapp/behandeling.html";
			}
		});
	});
}

