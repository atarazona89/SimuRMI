package Servidores;

import java.rmi.Naming;

import Implement.ServicioCalc;
import Interfaces.Servicio;

/**
 * Servidores que escuchan para ofrecer los servicios RMI
 * @author alejandro
 *
 */

public class Servidor {
	
	/**
	 * Constructor genérico para los servidores.
	 * @param tipo Tipo del servidor que se quiere iniciar.
	 * @param port Puerto por el cual escuchará el servicio iniciado.
	 */
	public Servidor(int tipo, int port) {
		switch (tipo) {
		case 1:
			try {
				Servicio c = new ServicioCalc();
				((ServicioCalc) c).registro();
				// Registra con el nombre ServicioCalcu al objeto c
				// en el Registry que se encuentra el el host <localhost>
				// y puerto <port>

				Naming.rebind("rmi://localhost:" + port + "/Servidor", c);
			} catch (Exception e) {
				System.out.println("Trouble: " + e);
			}
			break;
		default:
			System.out.println("Servicio no ofrecido.");
		}

	}
}
