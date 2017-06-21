package nl.hu.v1wac.melanoomapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.melanoomapp.model.Gebruiker;
import nl.hu.v1wac.melanoomapp.model.GebruikerSessie;
import nl.hu.v1wac.melanoomapp.model.Moedervlek;
import nl.hu.v1wac.melanoomapp.model.Sessie;

public class GebruikerSessieDAO extends BaseDAO {
	
	private Moedervlek demoedervlek;
	private Sessie desessie;
	private String date;
	
	public List<GebruikerSessie> selectGebruikerSessie(String query) {
		List<GebruikerSessie> results = new ArrayList<GebruikerSessie>();
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				int sessieID = dbResultSet.getInt("sessieid");
				String gebruikersnaam = dbResultSet.getString("gebruikersnaam");
				String date = dbResultSet.getString("datumaanmaking");
				
				GebruikerDAO gdao = new GebruikerDAO();
				Gebruiker g = gdao.getGebruiker(gebruikersnaam);
				SessieDAO sdao = new SessieDAO();
				Sessie si = sdao.getSessiemetID(sessieID);
				
				GebruikerSessie newGebruikerSessie = new GebruikerSessie(si, g, date);
				results.add(newGebruikerSessie);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}
	
	public List<GebruikerSessie> getAllSessies(){
		String query = "SELECT * FROM sessiegebruiker";
		return selectGebruikerSessie(query);
	}
	public GebruikerSessie getGebruikerSessie(int id){
		String query = "SELECT * FROM sessiegebruiker where sessieid = " + id + "";
		return (selectGebruikerSessie(query).get(0));
	}
	
	public GebruikerSessie insertGebruikerSessie(int sessieid, String gebruikersnaam, String datumaanmaking){
		String query = "insert into sessiegebruiker (sessieid, gebruikersnaam, datumaanmaking) values ("
				+ sessieid + ", '" + gebruikersnaam + "', '" + datumaanmaking + "')";
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		query = "SELECT * FROM sessiegebruiker order by sessieid DESC limit 1";
		return(selectGebruikerSessie(query).get(0));
	}
	public List<GebruikerSessie> getGebruikerSessieVoorDeskundige(){
		String query = "select sg.sessieid, sg.gebruikersnaam, sg.datumaanmaking "
				+ "from sessiegebruiker sg "
				+ "inner join sessie s on s.sessieid = sg.sessieid and s.isbehandelt = false";
		return(selectGebruikerSessie(query));
	}
	
	
}
