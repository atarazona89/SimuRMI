package Interfaces;

import java.util.ArrayList;

public interface Servicio extends java.rmi.Remote{
	ArrayList<Estadistica> log = null;
	
	class Estadistica{
		String peticion;
		boolean termiada;
	}
	
	String host ="localhost";
	int port = 10000;
	
	/**
	 * Host desde donde se está prestando el servicio.
	 * @return String con el IP del host.
	 */
	public String getHost();
	
	/**
	 * Puerto de escucha del servicio.
	 * @return int con el valor del puerto por donde está escuchando el servicio ofrecido.
	 */
	public int getPort();
	
	/**
	 * Tipo del servicio de acuerdo con la numeración codificada.
	 * @return int con el valor que haya sido codificado como tipo.
	 */
	public int getTipo();
	
	public String toString();
	
	/**
	 * Da un mensaje de bienvenida al servidor en que se ofrece el servicio.
	 */
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
