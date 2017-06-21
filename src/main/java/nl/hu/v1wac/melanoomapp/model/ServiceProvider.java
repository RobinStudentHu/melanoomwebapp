package nl.hu.v1wac.melanoomapp.model;

public class ServiceProvider {
	private static MelanoomService melanoomService = new MelanoomService();

	public static MelanoomService getMelanoomService() {
		return melanoomService;
	}
}
