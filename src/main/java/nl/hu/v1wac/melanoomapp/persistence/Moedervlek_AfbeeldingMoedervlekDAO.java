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
import nl.hu.v1wac.melanoomapp.model.Moedervlek_AfbeeldingMoedervlek;

public class Moedervlek_AfbeeldingMoedervlekDAO extends BaseDAO {

	private Moedervlek moedervlek;
	private AfbeeldingMoedervlek afbeelding;
	private String datumaanmaking;

	public List<Moedervlek_AfbeeldingMoedervlek> selectMoedervlek_AfbeeldingMoedervlek(String query) {
		List<Moedervlek_AfbeeldingMoedervlek> results = new ArrayList<Moedervlek_AfbeeldingMoedervlek>();
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			con.close();

			while (dbResultSet.next()) {
				int moedervlekid = dbResultSet.getInt("moedervlekid");
				int afbeeldingid = dbResultSet.getInt("afbeeldingid");
				String datumaanmaking = dbResultSet.getString("datumaanmaking");

				MoedervlekDAO sdao = new MoedervlekDAO();
				Moedervlek s = sdao.getMoedervlekmetID(moedervlekid);

				AfbeeldingMoedervlekDAO adao = new AfbeeldingMoedervlekDAO();
				AfbeeldingMoedervlek a = adao.getAfbeeldingID(afbeeldingid);

				Moedervlek_AfbeeldingMoedervlek newMoedervlek_AfbeeldingMoedervlek = new Moedervlek_AfbeeldingMoedervlek(
						s, a, datumaanmaking);
				results.add(newMoedervlek_AfbeeldingMoedervlek);
			
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<Moedervlek_AfbeeldingMoedervlek> getAllAfbeeldingen() {
		String query = "SELECT * FROM moedervlekafbeeldingmoedervlek";
		return selectMoedervlek_AfbeeldingMoedervlek(query);
	}

	public Moedervlek_AfbeeldingMoedervlek getAfbeeldingID(int id) {
		String query = "SELECT * FROM moedervlekafbeeldingmoedervlek where afbeeldingid = " + id + "";
		return (selectMoedervlek_AfbeeldingMoedervlek(query).get(0));
	}

	public Moedervlek_AfbeeldingMoedervlek getMoedervlekID(int id) {
		String query = "SELECT * FROM moedervlekafbeeldingmoedervlek where moedervlekid = " + id + "";
		return (selectMoedervlek_AfbeeldingMoedervlek(query).get(0));
	}

	public Moedervlek_AfbeeldingMoedervlek insertMoedervlek_AfbeeldingMoedervlek(int moedervlekid, int afbeeldingid,
			String datumaanmaking) {
		String query = "insert into moedervlekafbeeldingmoedervlek (moedervlekid, afbeeldingid, datumaanmaking) values ("
				+ moedervlekid + ", " + afbeeldingid + ", '" + datumaanmaking + "')";
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		query = "SELECT * FROM moedervlekafbeeldingmoedervlek order by afbeeldingid DESC limit 1";
		return (selectMoedervlek_AfbeeldingMoedervlek(query).get(0));
	}
}
