package nl.hu.v1wac.melanoomapp.model;

public class BehandelingGebruiker {
	private Sessie sessie;
	private Gebruiker gebruiker;
	private String datumafronding;

	public BehandelingGebruiker(Sessie sessie, Gebruiker gebruiker, String datumafronding) {
		this.sessie = sessie;
		this.gebruiker = gebruiker;
		this.datumafronding = datumafronding;
	}

	public Sessie getSessie() {
		return sessie;
	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public String getdatumafronding() {
		return datumafronding;
	}

}
