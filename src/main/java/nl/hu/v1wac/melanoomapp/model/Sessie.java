package nl.hu.v1wac.melanoomapp.model;

public class Sessie {
	private int sessieid;
	private Moedervlek moedervlek;
	private boolean isBehandelt;
	private boolean isEerste;

public Sessie(int sessieid, Moedervlek moedervlek, boolean isBehandelt, boolean isEerste) {
	this.sessieid = sessieid;
	this.moedervlek = moedervlek;
	this.isBehandelt = isBehandelt;
	this.isEerste = isEerste;
}

public int getSessieid(){
	return sessieid;
}

public Moedervlek getMoedervlek(){
	return moedervlek;
}

public boolean getIsBehandelt(){
	return isBehandelt;
}

public boolean getIsEerste(){
	return isEerste;
}
}
