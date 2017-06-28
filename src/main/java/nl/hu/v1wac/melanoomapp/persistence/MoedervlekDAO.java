package nl.hu.v1wac.melanoomapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.melanoomapp.model.Moedervlek;

public class MoedervlekDAO extends BaseDAO {

	private int moedervlekID;
	private int locatie;
	private int groote;
	private boolean heeftVerkleuring;
	private boolean isVervormd;

	public List<Moedervlek> selectMoedervlek(String query) {
		List<Moedervlek> results = new ArrayList<Moedervlek>();
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			con.close();

			while (dbResultSet.next()) {
				int moedervlekID = dbResultSet.getInt("moedervlekid");
				String locatie = dbResultSet.getString("locatie");
				int groote = dbResultSet.getInt("groote");
				boolean verkleuring = dbResultSet.getBoolean("heeftVerkleuring");
				boolean isvervormd = dbResultSet.getBoolean("isVervormd");

				Moedervlek newMoedervlek = new Moedervlek(moedervlekID, locatie, groote, verkleuring, isvervormd);
				results.add(newMoedervlek);
				System.out.println(moedervlekID + " " + locatie + " " + groote + " " + verkleuring + " " + isvervormd);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<Moedervlek> getAllMoedervleken() {
		String query = "SELECT * FROM moedervlek";
		return selectMoedervlek(query);
	}

	public Moedervlek getMoedervlekmetID(int id) {
		String query = "SELECT * FROM moedervlek where moedervlekid = " + id + "";
		return (selectMoedervlek(query).get(0));
	}

	public Moedervlek insertMoedervlek(String locatie, int groote, boolean heeftverkleuring, boolean isvervormd) {
		String query = "insert into moedervlek (locatie, groote, heeftverkleuring, isvervormd) values ('" + locatie
				+ "', " + groote + ", " + heeftverkleuring + ", " + isvervormd + ")";
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
		
			con.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		query = "SELECT * FROM moedervlek order by moedervlekid DESC limit 1";
		return (selectMoedervlek(query).get(0));
	}
}
