package nl.hu.v1wac.melanoomapp.model;

public class VragenLijst {
	public int vragenlijstid;
	public Sessie sessie;
	public String vragen;
	public String antwoorden;

	public VragenLijst(int vragenLijstid, Sessie sessie, String vragen, String antwoorden) {
		this.vragenlijstid = vragenLijstid;
		this.sessie = sessie;
		this.vragen = vragen;
		this.antwoorden = antwoorden;
	}

	public int getVragenLijstID() {
		return vragenlijstid;
	}

	public Sessie getSessie() {
		return sessie;
	}

	public String getVragen() {
		return vragen;
	}

	public String getAntwoorden() {
		return antwoorden;
	}
}
