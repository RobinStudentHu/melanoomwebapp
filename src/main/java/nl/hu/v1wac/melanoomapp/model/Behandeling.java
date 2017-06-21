package nl.hu.v1wac.melanoomapp.model;

public class Behandeling {
	private Sessie sessie;
	public String medischeAdvies;
	public String medischeVerwijzing;
	
	public Behandeling(Sessie sessie, String medischeAdvies, String medischeVerwijzing){
		this.sessie = sessie;
		this.medischeAdvies = medischeAdvies;
		this.medischeVerwijzing = medischeVerwijzing;
	}
	
	public Sessie getSessie(){
		return sessie;
	}
	
	public String getMedischeAdvies(){
		return medischeAdvies;
	}
	
	public String getMedischeVerwijzing(){
		return medischeVerwijzing;
	}
	
}
