package nl.hu.v1wac.melanoomapp.model;

import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.melanoomapp.persistence.MoedervlekDAO;
import nl.hu.v1wac.melanoomapp.persistence.SessieDAO;
import nl.hu.v1wac.melanoomapp.persistence.tableselectDAO;

public class MelanoomService{
	public static List<Moedervlek> allmoedervlekken = new ArrayList<Moedervlek>();
	public static MoedervlekDAO moedervlekDAO = new MoedervlekDAO();
	
	public static List<Sessie> allsessies = new ArrayList<Sessie>();
	public static SessieDAO sessieDAO = new SessieDAO();
	
	public static List<tableselect> alltableselects = new ArrayList<tableselect>();
	public static tableselectDAO tableselectDAO = new tableselectDAO();

	public List<Moedervlek> getAllMoedervlekken() {
		allmoedervlekken = moedervlekDAO.getAllMoedervleken();
		return allmoedervlekken;
	}
	
	public List<Sessie> getAllSessies() {
		allsessies = sessieDAO.getAllSessies();
		return allsessies;
	}
	
	public List<tableselect> getAlltableselect(){
		return alltableselects;
	}
}
