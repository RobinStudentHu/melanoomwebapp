package nl.hu.v1wac.melanoomapp.model;

import java.sql.Blob;

public class AfbeeldingMoedervlek {

	private int afbeeldingid;
	private Sessie desessie;
	private String afbeelding;

	public AfbeeldingMoedervlek(int afbeeldingid, String afbeelding, Sessie desessie) {
		this.afbeeldingid = afbeeldingid;
		this.afbeelding = afbeelding;
		this.desessie = desessie;
	}

	public int getAfbeeldingMoedervlek() {
		return afbeeldingid;
	}

	public Sessie getSessie() {
		return desessie;
	}

	public String getAfbeelding() {
		return afbeelding;
	}

}
