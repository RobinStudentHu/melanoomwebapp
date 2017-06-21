package nl.hu.v1wac.melanoomapp.persistence;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.melanoomapp.model.AfbeeldingMoedervlek;
import nl.hu.v1wac.melanoomapp.model.Moedervlek;
import nl.hu.v1wac.melanoomapp.model.Sessie;
import nl.hu.v1wac.melanoomapp.model.VragenLijst;

public class VragenLijstDAO extends BaseDAO {

	public int vragenlijstid;
	public Sessie sessie;
	public String vragen;
	public String antwoorden;
	
	public List<VragenLijst> selectVragenLijst(String query) {
		List<VragenLijst> results = new ArrayList<VragenLijst>();
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				int vragenlijstid = dbResultSet.getInt("vragenlijstid");
				int sessieid = dbResultSet.getInt("sessieid");
				String vragen = dbResultSet.getString("vragen");
				String antwoorden = dbResultSet.getString("antwoorden");
				
				SessieDAO sdao = new SessieDAO();
				Sessie s = sdao.getSessiemetID(sessieid);
		
				VragenLijst newVragenLijst = new VragenLijst(vragenlijstid,s,vragen,antwoorden);
				results.add(newVragenLijst);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}
	
	public List<VragenLijst> getAllVragenLijst(){
		String query = "SELECT * FROM vragenlijst";
		return selectVragenLijst(query);
	}
	public VragenLijst getVragenLijstID(int id){
		String query = "SELECT * FROM vragenlijst where vragenlijstid = " + id + "";
		return (selectVragenLijst(query).get(0));
	}
	public VragenLijst getSessieID(int id){
		String query = "SELECT * FROM vragenlijst where sessieid = " + id + "";
		return (selectVragenLijst(query).get(0));
	}
	
	
	public VragenLijst insertVragenLijst(int sessieid, String vragen, String antwoorden){
		String query = "insert into vragenlijst (sessieid, vragen, antwoorden) values ("
				+ sessieid + ", '" + vragen + "', '" + antwoorden + "')";
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		query = "SELECT * FROM vragenlijst order by vragenlijstid DESC limit 1";
		return(selectVragenLijst(query).get(0));
	}
}
	

