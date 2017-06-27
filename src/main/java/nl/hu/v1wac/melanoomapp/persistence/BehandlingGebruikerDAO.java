package nl.hu.v1wac.melanoomapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.melanoomapp.model.Behandeling;
import nl.hu.v1wac.melanoomapp.model.BehandelingGebruiker;
import nl.hu.v1wac.melanoomapp.model.Gebruiker;
import nl.hu.v1wac.melanoomapp.model.Moedervlek;
import nl.hu.v1wac.melanoomapp.model.Sessie;

public class BehandlingGebruikerDAO extends BaseDAO {

	public Sessie sessie;
	public Gebruiker gebruikersnaam;
	public String datumafronding;

	public List<BehandelingGebruiker> selectBehandlingGebruiker(String query) {
		List<BehandelingGebruiker> results = new ArrayList<BehandelingGebruiker>();
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int sessieID = dbResultSet.getInt("sessieid");
				String gebruikersnaam = dbResultSet.getString("gebruikersnaam");
				String datumafronding = dbResultSet.getString("datumafronding");

				SessieDAO sdao = new SessieDAO();
				Sessie s = sdao.getSessiemetID(sessieID);

				GebruikerDAO gdao = new GebruikerDAO();
				Gebruiker g = gdao.getGebruiker(gebruikersnaam);

				BehandelingGebruiker newb = new BehandelingGebruiker(s, g, datumafronding);
				results.add(newb);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<BehandelingGebruiker> getAllBehandlingGebruiker() {
		String query = "SELECT * FROM behandelinggebruiker";
		return selectBehandlingGebruiker(query);
	}

	public BehandelingGebruiker getBehandlingGebruikermetSessieID(int id) {
		String query = "SELECT * FROM behandelinggebruiker where sessieid = " + id + "";
		return (selectBehandlingGebruiker(query).get(0));
	}

	public BehandelingGebruiker insertBehandlingGebruiker(int sessieid, String gebruikersnaam, String datum) {
		String query = "insert into behandelinggebruiker (sessieid, gebruikersnaam, datumafronding) values (" + sessieid
				+ ", '" + gebruikersnaam + "', '" + datum + "')";
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		query = "SELECT * FROM behandelinggebruiker order by sessieid DESC limit 1";
		return (selectBehandlingGebruiker(query).get(0));
	}
}
