package nl.hu.v1wac.melanoomapp.model;

public class BehandelingGebruiker {
	public Sessie sessie;
	public Gebruiker gebruikersnaam;
	public String datumafronding;
	
	public BehandelingGebruiker(Sessie sessie, Gebruiker gebruikersnaam, String datumafronding){
		this.sessie = sessie;
		this.gebruikersnaam = gebruikersnaam;
		this.datumafronding = datumafronding;
	}
	
	public Sessie getSessie(){
		return sessie;
	}
	
	public Gebruiker getGebruiker(){
		return gebruikersnaam;
	}
	
	public String getdatumafronding(){
		return datumafronding;
	}

}
