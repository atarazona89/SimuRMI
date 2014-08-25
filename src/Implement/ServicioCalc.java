package Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Despachadores.Despachador;
import Interfaces.Servicio;

public class ServicioCalc implements Servicio {

	int tipo;
	int port = 12345;

	public ServicioCalc() throws java.rmi.RemoteException {
		super();
	}

	private int suma(int a, int b) {
		return a + b;
	}

	private int resta(int a, int b) {
		return a - b;
	}

	private int multipliacion(int a, int b) {
		return a * b;
	}

	private int division(int a, int b) {
		return a / b;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public void registro() {
		System.out.println("Sercicio de Calculadora Registrado");
	}

	@Override
	public int getTipo() {
		return this.tipo;
	}

	@Override
	public void prntServices() {
		System.out.println("1.- Suma");
		System.out.println("2.- Resta");
		System.out.println("3.- Multiplicación");
		System.out.println("4.- División");
		System.out.println("0.- Salir");
	}
	

	@Override
	public Object exec() {
		int serv = 0, a = 0, b = 0;
		
		prntServices();
		System.out.println("¿Qué servicio desea?");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		try {
			serv = Integer.parseInt(br.readLine());
			System.out.println("Indique el primer valor");
			a = Integer.parseInt(br.readLine());
			System.out.println("Indique el segundo valor");
			b = Integer.parseInt(br.readLine());
			
		} catch (NumberFormatException e) {
			System.out.println("Error de formato en el número");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de lectura de IO");
			e.printStackTrace();
		}
		
		switch (serv) {
		case 0:
			System.out.println("Gracias por usar nuestro servicio");
		case 1:
			return suma(a, b);
		case 2:
			return resta(a, b);
		case 3:
			return multipliacion(a, b);
		case 4:
			return division(a, b);
		}

		return null;
	}

	@Override
	public void registrar(String despIp) {
		String mi_ip;
		
		try {
			mi_ip = InetAddress.getLocalHost().getHostAddress();

			Registry registry = LocateRegistry.getRegistry(despIp);
			Despachador stub = (Despachador) registry.lookup("Despacho");
			String response = stub.sayHello();

			this.tipo = ((Despachador) stub).registerServer(mi_ip, this.port,
					"servicioCalc");

			System.out.println("response: " + response);
		} catch (UnknownHostException e1) {
			System.out.println("No se pudo localizar el localhost (?)."); // WTF?
			e1.printStackTrace();
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String host = "";
		int port = 0;

		try {
			System.out.println("Introduzca el IP del despachador");
			host = br.readLine();

			while (port < 10000 || port > 65535) {
				System.out
						.println("Introduzca el puerto escucha del despachador");
				port = Integer.parseInt(br.readLine());
				if (port < 10000 || port > 65535) {
					System.out
							.println("El rango del valores del puerto tiene que"
									+ " estar entre 10.000 y 65.535 (use "
									+ "-1 para tomar el puerto por defecto.");
				} else if (port == -1) {
					System.out.println("Usted ha seleccionado usar el puerto "
							+ "por defecto (55.555)");
					port = 55555;
				}
			}

			Servicio calc = new ServicioCalc();
			calc.registrar(host);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
