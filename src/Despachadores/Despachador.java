package Despachadores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import Servidores.Servidor;

/**
 * Clase que funciona como administrador de servicios.
 * @author alejandro
 *
 */
public class Despachador {

	private static int menu() {

		int entero = -1;

		while (entero < 0 || entero > 3) {

			System.out.println("\n");
			System.out.println("Menu de opciones:\n");
			System.out.println("1.- Agregar servidor");
			System.out.println("2.- Eliminar servidor");
			System.out.println("3.- Estadísticas");
			System.out.println("0.- Salir");

			BufferedReader buffer = new BufferedReader(new InputStreamReader(
					System.in));
			String line;
			try {
				line = buffer.readLine();
				entero = Integer.parseInt(line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return entero;
	}

	/**
	 * Maneja la selección del usuario.
	 * @param menu la opción elegida por el usario en el menú anterior
	 * @return 1 si la elección fue procesada con éxito. 0 si la opción no fue válida. -1 en caso de otros errores.
	 */
	private static int procesarSeleccion(int menu) {
		if (menu >= 0 && menu <= 3) {
			switch (menu) {
			case 1:
				try {
					agregarServicio();
				} catch (RemoteException e) {
					System.out.println("Error al agregar servicio");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("Excepción no prevista");
					e.printStackTrace();
				} finally {
					return -1;
				}

			case 2:
				eliminarServicio();
				return 1;
			case 3:
				mostrarEsta();
				return 1;
			case 0:
			default:
				salir();
				return 0;
			}
		} else {
			System.out.println("Error del programa");
			return -1;
		}
	}

	private static void mostrarEsta() {
		System.out.println("Funcion no implementada");
	}

	/**
	 * Agrega un servicio al sistema.
	 * 
	 * @throws RemoteException
	 */
	private static void agregarServicio() throws RemoteException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(
				System.in));
		String line;
		int port = 0, tipo = 0;

		System.out.println("Indique las caractrísticas del servicio:");

		try {
			System.out.println("De qué tipo es el servicio a agregar?");
			serviciosImplementados();
			line = buffer.readLine();
			tipo = Integer.parseInt(line);

			while (port < 10000 || port > 65535) {

				System.out
						.println("¿En qué puerto quiere agregar este servicio?");
				System.out.println("(Sólo diponibles entre 10.000-65.535)");
				line = buffer.readLine();
				port = Integer.parseInt(line);
				if (port < 10000 || port > 65535) {
					System.out.println("Error de puerto, vuelva a elegir.");
				}

			}

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

	private static void eliminarServicio() {
		System.out.println("Funcion no implementada");
	}

	private static void salir() {
		System.out.println("Cerrando despachador");
		System.exit(1);
	}

	private static void serviciosImplementados() {
		System.out.println("Los servicios implementados son");
		System.out.println("\t1.- Calculador");
		System.out.println("");
		System.out.println("");
	}

	public static void main(String args[]) {
		while (true) {
			int seleccion = menu();
			procesarSeleccion(seleccion);
		}

	}

}
