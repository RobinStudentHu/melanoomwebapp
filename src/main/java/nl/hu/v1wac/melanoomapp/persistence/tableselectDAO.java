package nl.hu.v1wac.melanoomapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.melanoomapp.model.Gebruiker;
import nl.hu.v1wac.melanoomapp.model.Moedervlek;
import nl.hu.v1wac.melanoomapp.model.tableselect;

public class tableselectDAO extends BaseDAO {
	
	private Moedervlek moedervlekid;
	private String laastedatum;

	public List<tableselect> selectTableselect_MoedervlekID_Met_Laastedatum(String query) {
		List<tableselect> results = new ArrayList<tableselect>();
		System.out.println(query);
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while (dbResultSet.next()) {
				int moedervlekid = dbResultSet.getInt("moedervlekid");
				MoedervlekDAO mdao = new MoedervlekDAO();
				Moedervlek m = mdao.getMoedervlekmetID(moedervlekid);
				String laastedatum = dbResultSet.getString("laastedatum");

				tableselect newtableselect = new tableselect(m, laastedatum);
				results.add(newtableselect);
				con.close();

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return results;
	}

	public List<tableselect> getTableselectGebruiker(String gebruiker) {
		String query = "select m.moedervlekid, (select sg.datumaanmaking from moedervlek m, sessie s "
				+ "inner join sessiegebruiker sg on sg.sessieid = s.sessieid where m.moedervlekid = s.moedervlekid and sg.gebruikersnaam = '"
				+ gebruiker + "' order by datumaanmaking desc limit 1) as LaasteDatum"
				+ " from moedervlek m, sessie s inner join sessiegebruiker sg on sg.sessieid = s.sessieid where m.moedervlekid = s.moedervlekid and sg.gebruikersnaam = '"
				+ gebruiker + "'" + " group by m.moedervlekid";
		return selectTableselect_MoedervlekID_Met_Laastedatum(query);
	}

}
