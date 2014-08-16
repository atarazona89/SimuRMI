package Servidores;

import java.io.*;
import java.util.ArrayList;
import Interfaces.*;

public class Servidor1{

	ArrayList<Servicio> serviciosRegistrados = new ArrayList<Servicio>();
	int peticionesAceptadas = 0;
	int peticionesRechazadas = 0;
	

	public static void main(String args[]){
		int seleccion = menu();
		procesarSeleccion(seleccion);


	}

	private static int menu(){
		
		int entero = -1;

		while(entero  < 0 || entero > 3){


			System.out.println("\n");
			System.out.println("Menu de opciones:\n");
			System.out.println("1.- Agregar servidor");
			System.out.println("2.- Eliminar servidor");
			System.out.println("3.- Estadísticas");
			System.out.println("0.- Salir");

			BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
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

	private static int procesarSeleccion(int menu){
		if(menu >= 0 && menu <=3){
			switch (menu){
				case 1:
					agregarServicio();
					return 1;
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

	private static void mostrarEsta(){
		System.out.println("Funcion no implementada");
	}

	private static void agregarServicio(){
		System.out.println("Funcion no implementada");
	}

	private static void eliminarServicio(){
		System.out.println("Funcion no implementada");
	}
	
	private static void salir(){
		System.out.println("Funcion no implementada");
	}
}
