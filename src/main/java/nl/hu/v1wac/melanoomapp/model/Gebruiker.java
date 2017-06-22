package nl.hu.v1wac.melanoomapp.model;

public class Gebruiker {

	private String gebruikersnaam;
	private String wachtwoord;
	private String rol;
	private String geboortedatum;
	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;

	public Gebruiker(String gebruikersnaam, String wachtwoord, String rol, String geboortedatum, String voornaam,
			String tussenvoegsel, String achternaam) {
		this.gebruikersnaam = gebruikersnaam;
		this.wachtwoord = wachtwoord;
		this.rol = rol;
		this.geboortedatum = geboortedatum;
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public String getRol() {
		return rol;
	}

	public String getGeboorteDatum() {
		return geboortedatum;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getTussenVoegsel() {
		return tussenvoegsel;
	}

	public String getAchternaam() {
		return achternaam;
	}

}
