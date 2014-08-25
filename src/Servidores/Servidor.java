package Servidores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import Despachadores.Despachador;
import Implement.ServicioCalc;
import Interfaces.Servicio;

/**
 * Servidores que escuchan para ofrecer los servicios RMI
 * 
 * @author alejandro
 * 
 */

public class Servidor {
	
	static int port = 12345; // Puerto de escucha del despachador. Por defecto.
	static int servPort = 10001; //Puerto de prestación de servicio. Por defecto.
	
	static ArrayList<Despachador> listaDespachadores = null; //Lista de los despachadores auxiliares. El primero es el principal activo.

	/**
	 * Constructor genérico para los servidores.
	 * 
	 * @param tipo
	 *            Tipo del servidor que se quiere iniciar.
	 * @param port
	 *            Puerto por el cual escuchará el servicio iniciado.
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

	public static void main(String args[]) {
		try {
			agregarServicio();
		} catch (RemoteException e) {
			System.out.println("No se pudo registrar en servidor.");
			e.printStackTrace();
		}
		

	}

	private static void agregarServicio() throws RemoteException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(
				System.in));
		String line;
		int tipo = 0;

		System.out.println("Indique las caractrísticas del servicio:");

		try {
			System.out.println("De qué tipo es el servicio a agregar?");
			serviciosImplementados();
			line = buffer.readLine();
			tipo = Integer.parseInt(line);			
			

			LocateRegistry.createRegistry(port);
		} catch (IOException e) {
			System.out.println("java.io.Exception");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println();
			System.out.println("java.lang.Exception");
			System.out.println(e);
		}
		new Servidor(tipo, port);
		System.out.println("¡Servidor creado con éxito!");
	}

	private static void serviciosImplementados() {
		System.out.println("Los servicios implementados son");
		System.out.println("\t1.- Calculador");
		System.out.println("");
		System.out.println("");
	}
}
