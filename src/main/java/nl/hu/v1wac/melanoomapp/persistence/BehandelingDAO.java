
package nl.hu.v1wac.melanoomapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.melanoomapp.model.Behandeling;
import nl.hu.v1wac.melanoomapp.model.Gebruiker;
import nl.hu.v1wac.melanoomapp.model.Moedervlek;
import nl.hu.v1wac.melanoomapp.model.Sessie;

public class BehandelingDAO extends BaseDAO {

	private Sessie sessie;
	public String medischeAdvies;
	public String medischeVerwijzing;

	public List<Behandeling> selectBehandeling(String query) {
		List<Behandeling> results = new ArrayList<Behandeling>();
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			

			while (dbResultSet.next()) {
				int sessieID = dbResultSet.getInt("sessieid");
				String medischeAdvies = dbResultSet.getString("medischadvies");
				String medischeVerwijzing = dbResultSet.getString("medischverwijzing");

				SessieDAO sdao = new SessieDAO();
				Sessie s = sdao.getSessiemetID(sessieID);
				Behandeling newb = new Behandeling(s, medischeAdvies, medischeVerwijzing);
				results.add(newb);
			}
		con.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public List<Behandeling> getAllBehandling() {
		String query = "SELECT * FROM behandeling";
		return selectBehandeling(query);
	}

	public Behandeling getBehandlingmetSessieID(int id) {
		String query = "SELECT * FROM behandeling where sessieid = " + id + "";
		return (selectBehandeling(query).get(0));
	}

	public Behandeling insertBehandling(int sessieid, String medischadvies, String medischverwijzing) {
		String query = "insert into behandeling (sessieid, medischadvies, medischverwijzing) values (" + sessieid
				+ ", '" + medischadvies + "', '" + medischverwijzing + "')";
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		SessieDAO upsessie = new SessieDAO();
		upsessie.updateSessie(sessieid);

		query = "SELECT * FROM behandeling order by sessieid DESC limit 1";
		return (selectBehandeling(query).get(0));
	}
}
