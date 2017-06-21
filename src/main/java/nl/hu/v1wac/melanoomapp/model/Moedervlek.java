package nl.hu.v1wac.melanoomapp.model;

public class Moedervlek {
	
	
	private int moedervlekID;
	private String locatie;
	private int groote;
	private boolean heeftVerkleuring;
	private boolean isVervormd;
	
	public Moedervlek(int moedervlekID, String locatie, int groote, boolean heeftVerkleuring, boolean isVervormd) 
	{
		this.moedervlekID = moedervlekID;
		this.locatie = locatie;
		this.groote = groote;
		this.heeftVerkleuring = heeftVerkleuring;
		this.isVervormd = isVervormd;
	}
	
	public int getMoedervlekID() {
		return moedervlekID;
	}
	
	public String getLocatie() {
		return locatie;
	}
	
	public int getGroote() {
		return groote;
	}
	
	public boolean getHeeftVerkleuring() {
		return heeftVerkleuring;
	}
	
	public boolean getIsVervormd() {
		return isVervormd;
	}
}

