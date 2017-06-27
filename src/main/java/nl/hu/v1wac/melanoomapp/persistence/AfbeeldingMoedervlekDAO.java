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

public class AfbeeldingMoedervlekDAO extends BaseDAO {

	private int afbeeldingid;
	private VragenLijst vragenlijst;
	private String afbeelding;

	public List<AfbeeldingMoedervlek> selectAfbeeldingMoedervlek(String query) {
		List<AfbeeldingMoedervlek> results = new ArrayList<AfbeeldingMoedervlek>();
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int afbeeldingid = dbResultSet.getInt("afbeeldingid");
				int sessieid = dbResultSet.getInt("sessieid");
				String afbeelding = dbResultSet.getString("afbeelding");
				SessieDAO sdao = new SessieDAO();
				Sessie s = sdao.getSessiemetID(sessieid);

				AfbeeldingMoedervlek newAfbeeldingMoedervlek = new AfbeeldingMoedervlek(afbeeldingid, afbeelding, s);
				results.add(newAfbeeldingMoedervlek);
				con.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<AfbeeldingMoedervlek> getAllAfbeeldingen() {
		String query = "SELECT * FROM afbeeldingmoedervlek";
		return selectAfbeeldingMoedervlek(query);
	}

	public AfbeeldingMoedervlek getAfbeeldingID(int id) {
		String query = "SELECT * FROM afbeeldingmoedervlek where afbeeldingid = " + id + "";
		return (selectAfbeeldingMoedervlek(query).get(0));
	}

	public AfbeeldingMoedervlek insertAfbeeldingMoedervlek(String afbeelding, int sessieid) {
		String query = "insert into afbeeldingmoedervlek (afbeelding, sessieid) values ('" + afbeelding + "', "
				+ sessieid + ")";
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			con.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		query = "SELECT * FROM afbeeldingmoedervlek order by afbeeldingid DESC limit 1";
		return (selectAfbeeldingMoedervlek(query).get(0));
	}
}
