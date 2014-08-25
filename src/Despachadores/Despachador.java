package Despachadores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase que funciona como administrador de servicios.
 * 
 * @author alejandro
 * 
 */
public class Despachador implements Runnable, Remote {

	// La lista contiene una lista de servidores por cada tipo (Servicio)
	// registrado.
	ArrayList<ArrayList<ServidoresStat>> listaServidores = null;
	ArrayList<String> listaDespachadores = null;

	int port; // Por defecto será el 55555

	ArrayList<String> servImplementados;

	public Despachador() {
	};

	public Despachador(int port) {
		listaDespachadores = new ArrayList<String>();
		listaServidores = new ArrayList<ArrayList<ServidoresStat>>();
		servImplementados = new ArrayList<String>();
		
		try {
			switch (port) {
			case -1:
				this.port = 55555; // Puerto por defecto
				break;
			default:
				this.port = port;
			}
			Despachador c = new Despachador();

			// Registra con el nombre CalculatorService al objeto c
			// en el Registry que se encuentra el el host <localhost>
			// y puerto <port>

			Naming.rebind("rmi://localhost:" + port + "/Despacho", c);
			
		} catch (Exception e) {
			System.out
					.println("Hubo problemas en el constructor del despachador");
			System.out.println("Problemas: " + e);
		}
	}

	public String sayHello() {
		return "Despachador contactado con éxito";
	}

	/**
	 * Procedimiento que registra un nuevo servidor para ser usado por los
	 * clientes que acuden al despachador.
	 * 
	 * @param tipo
	 *            Tipo del servidor que se quiere agregar. Enteros a partir de 1
	 *            hasta el número de diferentes servicios implementados.
	 * @param host
	 *            Direccion IP, en String, del servicio a agregar.
	 * @param port
	 *            Puerto por el cual escucha el servicio.
	 * @param nombre
	 *            Nombre para poder hacer la solicitud RMI.
	 */
	public int registerServer(String host, int port, String nombre) {

		if (!servImplementados.contains(nombre)) {
			servImplementados.add(nombre);
		}

		int tipo = servImplementados.indexOf(nombre);
		ServidoresStat st = new ServidoresStat(tipo, port, host, nombre);
		agregarServidor(st);
		return tipo;
	}

	/**
	 * Busca la lista adecuada entre las listas de servidores y agrega al final
	 * el nuevo servidor.
	 * 
	 * @param st
	 *            Datos del servidor a agregar.
	 */
	private void agregarServidor(ServidoresStat st) {
		ArrayList<ServidoresStat> lst = null;

		Iterator<ArrayList<ServidoresStat>> it = listaServidores.iterator();
		while (it.hasNext()) {
			lst = (ArrayList<ServidoresStat>) it.next();
			ServidoresStat ss = lst.get(0);

			if (ss.getTipo() == st.getTipo()) {
				lst.add(st);
				break;
			}
		}

		lst = new ArrayList<ServidoresStat>();
		lst.add(st);
		listaServidores.add(lst);
	}

	/**
	 * Regresa los datos del primer servidor en registro y hace una
	 * reorganización al estilo roud robin para mantener el balance de carga.
	 * 
	 * @param tipo
	 *            Tipo de servicio solicitado.
	 * @return Datos del servidor al cual redirigir la petición.
	 */
	public ServidoresStat solicitarServicio(int tipo) {
		ServidoresStat sst;
		ArrayList<ServidoresStat> lst = null;

		Iterator<ArrayList<ServidoresStat>> it = listaServidores.iterator();
		while (it.hasNext()) {
			lst = (ArrayList<ServidoresStat>) it.next();
			ServidoresStat primero = lst.get(0);
			if (primero.getTipo() == tipo) {
				sst = primero;

				// Coloca el usado al final de la cola.
				// Round Robin.
				lst.remove(primero);
				lst.add(primero);
				return sst;
			}
		}
		System.out.println("Servicio no encontrado");
		return null;
	}

	public ArrayList<String> recieveAux(String host) {
		listaDespachadores.add(host);
		return listaDespachadores;

	}

	public void registerAsAux(String host) throws UnknownHostException,
			RemoteException, NotBoundException {
		String mi_ip = InetAddress.getLocalHost().getHostAddress();

		Registry registry = LocateRegistry.getRegistry(host);
		Despachador stub = (Despachador) registry.lookup("Despacho");

		listaDespachadores = ((Despachador) stub).recieveAux(mi_ip);
	}

	public void registerAsMain() {
		System.out.println("Hello world!");
		int port = 0;

		while (port < 10000 || port > 65535 || port != -1) {
			System.out.println("Indique el puerto a usar"
					+ "(-1 para usar el puerto por defecto");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String line = "";
			try {
				line = br.readLine();
			} catch (IOException e1) {
				System.out.println("Error en la lectura por cónsola.");
				e1.printStackTrace();
			}
			port = Integer.parseInt(line);

			try {
				// Crea un Registry en el puerto especificado
				LocateRegistry.createRegistry(port);
			} catch (RemoteException re) {
				System.out.println();
				System.out.println("RemoteException");
				System.out.println(re);
			} catch (Exception e) {
				System.out.println();
				System.out.println("java.lang.Exception");
				System.out.println(e);
			}
		}

		new Despachador(port);
		System.out.println("Despacho creado con éxito.");
	}

	@Override
	public void run() {
		// TODO Método que revisa la disponibilidad de los servidores
		// registrados.
		// También por acá se podría hacer la propagación del registro de
		// servicios.

	}

	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean main = true;
		String line = "";

		while (!(line.equals("S") || line.equals("s") || line.equals("N") || 
				line.equals("n"))) {
			System.out
					.println("¿Desea registrar este despachador como principal?"
							+ "s/n");
			try {
				line = br.readLine();
			} catch (IOException e) {
				System.out.println("Error en la lectura de cónsola.");
				e.printStackTrace();
				System.exit(1);
			}
		}
		main = (line.equals("S") || line.equals("s"));
		Despachador d = new Despachador();

		if (main) {
			d.registerAsMain();
		} else {
			System.out.println("Indique la dirección del servidor principal");
			String host;
			try {
				host = br.readLine();
				d.registerAsAux(host);
			} catch (IOException e) {
				System.out.println("Error en la lectura de cónsola.");
				e.printStackTrace();
				System.exit(1);
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
