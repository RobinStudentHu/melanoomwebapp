import nl.hu.v1wac.melanoomapp.persistence.MoedervlekDAO;

public class Main {

	public static void main(String[] args) {
		MoedervlekDAO dao = new MoedervlekDAO();
		String query = "SELECT * FROM MOEDERVLEK";
		dao.selectMoedervlek(query);
		// TODO Auto-generated method stub

	}

}
