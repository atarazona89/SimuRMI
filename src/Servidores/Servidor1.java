import java.io.*;
import java.util.ArrayList;

public class Servidor1{

	ArrayList<Servicios> serviciosRegistrados = new ArrayList<>();

	public static void main(String args[]){
		int seleccion = menu();
		procesarSeleccion(seleccion);


	}

	private int menu(){
		int entero = -1;

		while(seleccion  < 0 || seleccion > 3){

			System.out.println("\n");
			System.out.println("Menu de opciones:\n");
			System.out.println("1.- Agregar servidor");
			System.out.println("2.- Eliminar servidor");
			System.out.println("3.- Estadísticas");
			System.out.println("0.- Salir");

			BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
			String line=buffer.readLine();
	
			entero = Integer.parseInt(enteroString);
		}
		
		return entero;
	}

	private int procesarSeleccion(int menu){
		if(menu >= 0 && menu <=3){
			switch (menu){
				case 1:
					agregarServicio();
					return 1;
					break;
				case 2:
					eliminarServicio();
					return 1;
					break;
				case 3:
					mostrarEsta();
					return 1;
					break;
				case 0:
				default:
					salir();
					return 0;
			}
			return 1;
		} else {
			System.out.println("Error del programa");
			return -1;
		}
	}

	private void mostrarEsta();

	private void agregarServicio();

	private void eliminarServicio();
}
