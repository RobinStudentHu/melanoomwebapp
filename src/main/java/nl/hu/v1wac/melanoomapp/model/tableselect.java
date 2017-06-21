package nl.hu.v1wac.melanoomapp.model;

public class tableselect {
	private Moedervlek moedervlekID;
	private String laasteDatum;
	
	public tableselect(Moedervlek moedervlekID, String laasteDatum){
		this.moedervlekID = moedervlekID;
		this.laasteDatum = laasteDatum;
	}
	
	public Moedervlek getMoedervlekID() {
		return moedervlekID;
	}
	
	public String getLaasteDatum(){
		return laasteDatum;
	}
	

}
