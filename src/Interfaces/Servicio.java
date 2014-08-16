package Interfaces;

public interface Servicio {
	String host ="localhost";
	int port = 12345;
	
	public void register(String host, int port);
	public void unregister();

}
