package nl.hu.v1wac.melanoomapp.model;

public class GebruikerSessie {
	private Gebruiker degebruiker;
	private Sessie desessie;
	private String date;
	
	public GebruikerSessie (Sessie desessie, Gebruiker degebruiker, String date){
		this.degebruiker = degebruiker;
		this.desessie = desessie;
		this.date = date;
}
	public Gebruiker getGebruiker(){
		return degebruiker;
	}
	
	public Sessie getSessie(){
		return desessie;
	}
	
	public String getDatum(){
		return date;
	}
	
}