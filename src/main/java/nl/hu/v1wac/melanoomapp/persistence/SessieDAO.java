package nl.hu.v1wac.melanoomapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.melanoomapp.model.Gebruiker;
import nl.hu.v1wac.melanoomapp.model.Moedervlek;
import nl.hu.v1wac.melanoomapp.model.Sessie;

public class SessieDAO extends BaseDAO {

	private int sessieid;
	private Moedervlek moedervlek;
	private boolean isBehandelt;
	private boolean isEerste;

	public List<Sessie> selectSessie(String query) {
		List<Sessie> results = new ArrayList<Sessie>();
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int sessieID = dbResultSet.getInt("sessieid");
				int moedervlekID = dbResultSet.getInt("moedervlekid");

				boolean isBehandelt = dbResultSet.getBoolean("isbehandelt");
				boolean isEerste = dbResultSet.getBoolean("iseerste");

				MoedervlekDAO mdao = new MoedervlekDAO();
				Moedervlek mv = mdao.getMoedervlekmetID(moedervlekID);
				Sessie newSessie = new Sessie(sessieID, mv, isBehandelt, isEerste);
				results.add(newSessie);
				con.close();
				System.out.println(sessieID + " " + moedervlekID + " " + isBehandelt + " " + isEerste);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<Sessie> getAllSessies() {
		String query = "SELECT * FROM sessie";
		return selectSessie(query);
	}

	public Sessie getSessiemetID(int id) {
		String query = "SELECT * FROM sessie where sessieid = " + id + "";
		return (selectSessie(query).get(0));
	}

	public Sessie insertSessie(int moedervlekid, boolean isbehandelt, boolean iseerst) {
		String query = "insert into sessie (moedervlekid, isbehandelt, iseerste) values ('" + moedervlekid + "', "
				+ isbehandelt + ", " + iseerst + ")";
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		query = "SELECT * FROM sessie order by sessieid DESC limit 1";
		return (selectSessie(query).get(0));
	}

	public void updateSessie(int sessieid) {
		String query = "UPDATE Sessie" + " SET isbehandelt = true " + "WHERE sessieid = " + sessieid;
		System.out.println(query);

		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.close();


		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public List<Sessie> geefCompleteSessieGebruiker(String gebruikersnaam) {
		String query = "select s.sessieid, s.moedervlekid, s.isbehandelt, s.iseerste from Sessie s"
				+ " inner join sessiegebruiker gs on gs.sessieid = s.sessieid and gs.gebruikersnaam = '"
				+ gebruikersnaam
				+ "' inner join behandelinggebruiker b on s.sessieid = b.sessieid WHERE s.isbehandelt = true";
		return (selectSessie(query));
	}
}
