package nl.hu.v1wac.melanoomapp.webservices;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.hu.v1wac.melanoomapp.model.AfbeeldingMoedervlek;
import nl.hu.v1wac.melanoomapp.model.Behandeling;
import nl.hu.v1wac.melanoomapp.model.Gebruiker;
import nl.hu.v1wac.melanoomapp.model.GebruikerSessie;
import nl.hu.v1wac.melanoomapp.model.Moedervlek;
import nl.hu.v1wac.melanoomapp.model.Moedervlek_AfbeeldingMoedervlek;
import nl.hu.v1wac.melanoomapp.model.ServiceProvider;
import nl.hu.v1wac.melanoomapp.model.Sessie;
import nl.hu.v1wac.melanoomapp.model.VragenLijst;
import nl.hu.v1wac.melanoomapp.model.tableselect;
import nl.hu.v1wac.melanoomapp.persistence.AfbeeldingMoedervlekDAO;
import nl.hu.v1wac.melanoomapp.persistence.BehandelingDAO;
import nl.hu.v1wac.melanoomapp.persistence.BehandlingGebruikerDAO;
import nl.hu.v1wac.melanoomapp.persistence.GebruikerDAO;
import nl.hu.v1wac.melanoomapp.persistence.GebruikerSessieDAO;
import nl.hu.v1wac.melanoomapp.persistence.MoedervlekDAO;
import nl.hu.v1wac.melanoomapp.persistence.Moedervlek_AfbeeldingMoedervlekDAO;
import nl.hu.v1wac.melanoomapp.persistence.SessieDAO;
import nl.hu.v1wac.melanoomapp.persistence.VragenLijstDAO;
import nl.hu.v1wac.melanoomapp.persistence.tableselectDAO;

@Path("/moedervlekken")
public class MelanoomResource {

	@GET
	@Path("/getAllmoedervlekken")
	@Produces("application/json")
	public String getMoedervlekken() {
		System.out.println("test");
		List<Moedervlek> demoedervlekken = ServiceProvider.getMelanoomService().getAllMoedervlekken();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Moedervlek c : demoedervlekken) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("moedervlekID", c.getMoedervlekID());
			job.add("locatie", c.getLocatie());
			job.add("groote", c.getGroote());
			job.add("heeftVerkleuring", c.getHeeftVerkleuring());
			job.add("isVervormd", c.getIsVervormd());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@Path("/getVoltooidebehandelinginfo/{id}")
	@GET
	@Produces("application/json")
	// geeft informatie van voltooide behandelingen voor de gebruiker
	public String getVoltooidebehandelinginfo(@PathParam("id") int sessieid) {
		SessieDAO sdao = new SessieDAO();
		Sessie s = sdao.getSessiemetID(sessieid);

		GebruikerSessieDAO gsd = new GebruikerSessieDAO();
		GebruikerSessie gs = gsd.getGebruikerSessie(sessieid);

		Moedervlek_AfbeeldingMoedervlekDAO mdao = new Moedervlek_AfbeeldingMoedervlekDAO();
		Moedervlek_AfbeeldingMoedervlek mam = mdao.getMoedervlekID(s.getMoedervlek().getMoedervlekID());

		VragenLijstDAO vdao = new VragenLijstDAO();
		VragenLijst vl = vdao.getSessieID(sessieid);

		BehandelingDAO bdao = new BehandelingDAO();
		Behandeling b = bdao.getBehandlingmetSessieID(sessieid);

		String vragenlijst = vl.getVragen();
		String antwoordenlijst = vl.getAntwoorden();

		String[] vragenlijstSplit = vragenlijst.split("/");
		String[] antwoordenlijstSplit = antwoordenlijst.split("/");

		int vragenlijstlengte = vragenlijstSplit.length;

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("sessieid", s.getSessieid());
		job.add("isEerste", s.getIsEerste());
		job.add("gebruikersnaam", gs.getGebruiker().getGebruikersnaam());
		job.add("vragenlijst", vl.getVragen());
		job.add("moedervlekid", s.getMoedervlek().getMoedervlekID());
		job.add("afbeelding", mam.getAfbeeldingMoedervlek().getAfbeelding());
		job.add("lengtevragen", vragenlijstlengte);
		job.add("ma", b.getMedischeAdvies());
		job.add("mv", b.getMedischeVerwijzing());
		if (vragenlijstlengte == 9) {
			job.add("vraag1", vragenlijstSplit[0]);
			job.add("vraag2", vragenlijstSplit[1]);
			job.add("vraag3", vragenlijstSplit[2]);
			job.add("vraag4", vragenlijstSplit[3]);
			job.add("vraag5", vragenlijstSplit[4]);
			job.add("vraag6", vragenlijstSplit[5]);
			job.add("vraag7", vragenlijstSplit[6]);
			job.add("vraag8", vragenlijstSplit[7]);
			job.add("vraag9", vragenlijstSplit[8]);
			job.add("antwoord1", antwoordenlijstSplit[0]);
			job.add("antwoord2", antwoordenlijstSplit[1]);
			job.add("antwoord3", antwoordenlijstSplit[2]);
			job.add("antwoord4", antwoordenlijstSplit[3]);
			job.add("antwoord5", antwoordenlijstSplit[4]);
			job.add("antwoord6", antwoordenlijstSplit[5]);
			job.add("antwoord7", antwoordenlijstSplit[6]);
			job.add("antwoord8", antwoordenlijstSplit[7]);
			job.add("antwoord9", antwoordenlijstSplit[8]);
		}
		return job.build().toString();
	}

	@Path("/getbehandelinginfo/{id}")
	@GET
	@Produces("application/json")
	// geeft de uitkomst van het formulier wat de gebruiker heeft ingevuld voor
	// het consult
	public String getbehandelinginfo(@PathParam("id") int sessieid) {
		SessieDAO sdao = new SessieDAO();
		Sessie s = sdao.getSessiemetID(sessieid);

		GebruikerSessieDAO gsd = new GebruikerSessieDAO();
		GebruikerSessie gs = gsd.getGebruikerSessie(sessieid);

		Moedervlek_AfbeeldingMoedervlekDAO mdao = new Moedervlek_AfbeeldingMoedervlekDAO();
		Moedervlek_AfbeeldingMoedervlek mam = mdao.getMoedervlekID(s.getMoedervlek().getMoedervlekID());

		VragenLijstDAO vdao = new VragenLijstDAO();
		VragenLijst vl = vdao.getSessieID(sessieid);

		String vragenlijst = vl.getVragen();
		String antwoordenlijst = vl.getAntwoorden();

		String[] vragenlijstSplit = vragenlijst.split("/");
		String[] antwoordenlijstSplit = antwoordenlijst.split("/");

		int vragenlijstlengte = vragenlijstSplit.length;

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("sessieid", s.getSessieid());
		job.add("isEerste", s.getIsEerste());
		job.add("gebruikersnaam", gs.getGebruiker().getGebruikersnaam());
		job.add("vragenlijst", vl.getVragen());
		job.add("moedervlekid", s.getMoedervlek().getMoedervlekID());
		job.add("afbeelding", mam.getAfbeeldingMoedervlek().getAfbeelding());
		job.add("lengtevragen", vragenlijstlengte);
		if (vragenlijstlengte == 9) {
			job.add("vraag1", vragenlijstSplit[0]);
			job.add("vraag2", vragenlijstSplit[1]);
			job.add("vraag3", vragenlijstSplit[2]);
			job.add("vraag4", vragenlijstSplit[3]);
			job.add("vraag5", vragenlijstSplit[4]);
			job.add("vraag6", vragenlijstSplit[5]);
			job.add("vraag7", vragenlijstSplit[6]);
			job.add("vraag8", vragenlijstSplit[7]);
			job.add("vraag9", vragenlijstSplit[8]);
			job.add("antwoord1", antwoordenlijstSplit[0]);
			job.add("antwoord2", antwoordenlijstSplit[1]);
			job.add("antwoord3", antwoordenlijstSplit[2]);
			job.add("antwoord4", antwoordenlijstSplit[3]);
			job.add("antwoord5", antwoordenlijstSplit[4]);
			job.add("antwoord6", antwoordenlijstSplit[5]);
			job.add("antwoord7", antwoordenlijstSplit[6]);
			job.add("antwoord8", antwoordenlijstSplit[7]);
			job.add("antwoord9", antwoordenlijstSplit[8]);
		}
		return job.build().toString();
	}

	@GET
	@Path("/getAllsessies")
	@Produces("application/json")
	public String getSessies() {
		System.out.println("test");
		List<Sessie> desessies = ServiceProvider.getMelanoomService().getAllSessies();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Sessie c : desessies) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("sessieID", c.getSessieid());
			job.add("moedervlek", c.getMoedervlek().getMoedervlekID());
			job.add("iseerste", c.getIsEerste());
			job.add("isbehandelt", c.getIsBehandelt());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@Path("/inloggen")
	@POST
	@Produces("application/json")
	public String inloggen(@FormParam("gebruikersnaam") String gebruikersnaam,
			@FormParam("wachtwoord") String wachtwoord) {
		GebruikerDAO gdao = new GebruikerDAO();
		Gebruiker g = gdao.getGebruiker(gebruikersnaam);
		if (g.getWachtwoord().equals(wachtwoord)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("message", "Inloggen succesvol");
			job.add("check", true);
			job.add("gebruiker", g.getGebruikersnaam());
			job.add("wachtwoord", g.getWachtwoord());
			return job.build().toString();
		} else {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("message", "Inloggen mislukt: Fout wachtwoord!");
			job.add("check", false);
			job.add("gebruiker", "");
			job.add("wachtwoord", "");
			return job.build().toString();
		}
	}

	@Path("/getconsultlijst")
	@GET
	@Produces("application/json")
	// geeft een lijst van alle consult verzoeken
	public String getConsultlijst() {
		GebruikerSessieDAO dao = new GebruikerSessieDAO();
		List<GebruikerSessie> lijst = dao.getGebruikerSessieVoorDeskundige();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (GebruikerSessie s : lijst) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("sessieid", s.getSessie().getSessieid());
			job.add("gebruikersnaam", s.getGebruiker().getGebruikersnaam());
			job.add("datum", s.getDatum());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@Path("/getvoltooidesessie/{id}")
	@GET
	@Produces("application/json")
	public String getvoltooideSessielijst(@PathParam("id") String gebruikersnaam) {

		SessieDAO dao = new SessieDAO();
		List<Sessie> lijst = dao.geefCompleteSessieGebruiker(gebruikersnaam);
		JsonArrayBuilder jab = Json.createArrayBuilder();

		BehandlingGebruikerDAO bdao = new BehandlingGebruikerDAO();

		for (Sessie s : lijst) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("sessieid", s.getSessieid());
			job.add("moedervlekid", s.getMoedervlek().getMoedervlekID());
			job.add("datum", bdao.getBehandlingGebruikermetSessieID(s.getSessieid()).getdatumafronding());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@Path("/getableselect/{id}")
	@POST
	@Produces("application/json")
	public String getTableSelect(@PathParam("id") String gebruikersnaam) {
		System.out.println(gebruikersnaam);
		tableselectDAO tdao = new tableselectDAO();
		List<tableselect> tt = tdao.getTableselectGebruiker(gebruikersnaam);
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (tableselect c : tt) {
			if (c.getLaasteDatum() == null) {
				JsonObjectBuilder job = Json.createObjectBuilder();
				job.add("moedervlekid", c.getMoedervlekID().getMoedervlekID());
				job.add("locatie", c.getMoedervlekID().getLocatie());
				job.add("groote", c.getMoedervlekID().getGroote());
				job.add("laastedatum", "19-21-1997");

				jab.add(job);
			} else {
				JsonObjectBuilder job = Json.createObjectBuilder();
				job.add("moedervlekid", c.getMoedervlekID().getMoedervlekID());
				job.add("locatie", c.getMoedervlekID().getLocatie());
				job.add("groote", c.getMoedervlekID().getGroote());
				job.add("laastedatum", "19-21-1997");

				jab.add(job);
			}
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@Path("/verzendbehandeling/{id}/{id2}")
	@POST
	@Produces("application/json")
	// Slaat de behandeling op in de database
	public String verzendbehandeling(@PathParam("id") int sessieid, @PathParam("id2") String gebruikersnaam,
			@FormParam("medischadvies") String medischadvies,
			@FormParam("medischverwijzing") String medischverwijzing) {
		BehandelingDAO bdao = new BehandelingDAO();
		bdao.insertBehandling(sessieid, medischadvies, medischverwijzing);

		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String today = formatter.format(date);

		BehandlingGebruikerDAO bgdao = new BehandlingGebruikerDAO();
		bgdao.insertBehandlingGebruiker(sessieid, gebruikersnaam, today);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("message", "Behandeling opgeslagen!");
		return job.build().toString();
	}

	@Path("/dvragenlijst/{id}")
	@POST
	@Produces("application/json")
	// slaat de sessie/consult op in de database
	public String dvragenlijst(@PathParam("id") String gebruikersnaam, @FormParam("locatiea") String locatie,
			@FormParam("isvervormda") boolean isvervormd, @FormParam("isvaaga") boolean isvaaga,
			@FormParam("kleura") boolean heeftverkleuring, @FormParam("diametera") int groote,
			@FormParam("jeuka") boolean jeuk, @FormParam("bloeda") boolean bloeda,
			@FormParam("zweertjea") boolean zweertje, @FormParam("viertiga") boolean viertig,
			@FormParam("srca") String scra) {

		MoedervlekDAO mdao = new MoedervlekDAO();
		Moedervlek m = mdao.insertMoedervlek(locatie, groote, heeftverkleuring, isvervormd);

		SessieDAO sdao = new SessieDAO();
		Sessie s = sdao.insertSessie(m.getMoedervlekID(), false, true);

		AfbeeldingMoedervlekDAO afdao = new AfbeeldingMoedervlekDAO();
		AfbeeldingMoedervlek ak = afdao.insertAfbeeldingMoedervlek(scra, s.getSessieid());

		String vragen = " Waar zit de moedervlek?/Asymmetrie: Heeft de moedervlek aan beide kanten dezelfde vorm?/"
				+ "Heeft de moedervlek een vage, soms aflopende rand?/Kleur: Bestaat de moedervlek uit verschillende kleuren?/"
				+ "Diameter: Hoe groot is de moedervlek in mm?/"
				+ "Jeukt de moedervlek aanhoudend langer dan 2 weken?/Bloed de moedervlek spontaan?/"
				+ "Heeft de moedervlek een kostje of zweertje?/Is de moedervlek ontstaan na uw viertigste levensjaar?";

		String antwoorden = locatie + "/" + isvervormd + "/" + isvaaga + "/" + heeftverkleuring + "/" + groote + "/"
				+ jeuk + "/" + bloeda + "/" + zweertje + "/" + viertig;

		VragenLijstDAO vdao = new VragenLijstDAO();
		VragenLijst vt = vdao.insertVragenLijst(s.getSessieid(), vragen, antwoorden);

		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String today = formatter.format(date);

		GebruikerSessieDAO gsdao = new GebruikerSessieDAO();
		GebruikerSessie grs = gsdao.insertGebruikerSessie(s.getSessieid(), gebruikersnaam, today);

		Moedervlek_AfbeeldingMoedervlekDAO mamdao = new Moedervlek_AfbeeldingMoedervlekDAO();
		Moedervlek_AfbeeldingMoedervlek mam = mamdao.insertMoedervlek_AfbeeldingMoedervlek(m.getMoedervlekID(),
				ak.getAfbeeldingMoedervlek(), today);

		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("message", "toegevoegd");
		return job.build().toString();
	}
}
