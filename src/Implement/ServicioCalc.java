package Implement;

import Interfaces.Servicio;

public class ServicioCalc implements Servicio{

	private int suma(int a, int b){
		return a+b;
	}
	
	private int resta(int a, int b){
		return a-b;
	}
	
	private int multipliacion(int a, int b){
		return a*b;
	}
	
	private int division(int a, int b){
		return a/b;
	}
	
	public ServicioCalc() 
			throws java.rmi.RemoteException {
		super();
	}

	@Override
	public String getHost() {
		return host;
	}


	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void registro() {
	      System.out.println("Sercicio de Calculadora Registrado");
	  }

	@Override
	public int getTipo() {
		return 1;
	}

	@Override
	public void prntSercices() {
		System.out.println("1.- Suma");
		System.out.println("2.- Resta");
		System.out.println("3.- Multiplicación");
		System.out.println("4.- División");
	}
	
	@Override
	public Object exec(int serv, Object[] args) {
		int a,b;
		switch(serv){
		case 1:
			a = ((Integer) args[0]).intValue();
			b = ((Integer) args[1]).intValue();
			return suma(a,b);
		case 2:
			a = ((Integer) args[0]).intValue();
			b = ((Integer) args[1]).intValue();
			return resta(a,b);
		case 3:
			a = ((Integer) args[0]).intValue();
			b = ((Integer) args[1]).intValue();
			return multipliacion(a,b);
		case 4:
			a = ((Integer) args[0]).intValue();
			b = ((Integer) args[1]).intValue();
			return division(a,b);
		}
		
		return null;
	}

}
