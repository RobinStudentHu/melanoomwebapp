package nl.hu.v1wac.melanoomapp.model;

public class Moedervlek_AfbeeldingMoedervlek {
	private Moedervlek moedervlek;
	private AfbeeldingMoedervlek afbeelding;
	private String datumaanmaking;

	public Moedervlek_AfbeeldingMoedervlek(Moedervlek demoedervlek, AfbeeldingMoedervlek amk, String dedatum){
		this.moedervlek = demoedervlek;
		this.afbeelding = amk;
		this.datumaanmaking = dedatum;
	}
	
	public Moedervlek getMoedervlek(){
		return moedervlek;
	}
	
	public AfbeeldingMoedervlek getAfbeeldingMoedervlek(){
		return afbeelding;
	}
	
	public String getDatum(){
		return datumaanmaking;
	}
}
