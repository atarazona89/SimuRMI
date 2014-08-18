package Interfaces;

public interface Servicio extends java.rmi.Remote{
	String host ="localhost";
	int port = 10000;
	
	public String getHost();
	public int getPort();
	public int getTipo();
	
	public String toString();
	
	public void registro();
	
	/**
	 * Imprime el menú de los servicios prestados.
	 */
	void prntSercices();
	
	/**
	 * Función que ejecuta los diferentes servicios prestados por la instancia de Servicio.
	 * @param Tipo Servicio solicitado.
	 * @param args Argumentos pasados al Servicio.
	 * @return Reultado de la prestación del servicio.
	 */
	Object exec(int Tipo, Object[] args);
	

}
