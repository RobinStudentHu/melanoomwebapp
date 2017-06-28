package nl.hu.v1wac.melanoomapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.melanoomapp.model.Gebruiker;

public class GebruikerDAO extends BaseDAO {

	private String gebruikersnaam;
	private String wachtwoord;
	private String rol;
	private String geboortedatum;
	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;

	public List<Gebruiker> selectGebruiker(String query) {
		List<Gebruiker> results = new ArrayList<Gebruiker>();
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			con.close();

			while (dbResultSet.next()) {
				String gebruikersnaam = dbResultSet.getString("gebruikersnaam");
				String wachtwoord = dbResultSet.getString("wachtwoord");
				String rol = dbResultSet.getString("rol");
				// String geboortedatum =
				// dbResultSet.getString("geboortedatum");
				String voornaam = dbResultSet.getString("voornaam");
				String tussenvoegsel = dbResultSet.getString("tussenvoegsel");
				String achternaam = dbResultSet.getString("achternaam");

				Gebruiker newGebruiker = new Gebruiker(gebruikersnaam, wachtwoord, rol, null, voornaam, tussenvoegsel,
						achternaam);
				results.add(newGebruiker);
					}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<Gebruiker> getAllGebruikers() {
		String query = "SELECT * FROM gebruiker";
		return selectGebruiker(query);
	}

	public Gebruiker getGebruiker(String naam) {
		String query = "SELECT * FROM gebruiker WHERE gebruikersnaam = '" + naam + "'";
		return selectGebruiker(query).get(0);
	}
}
