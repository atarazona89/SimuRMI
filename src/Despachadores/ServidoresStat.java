package Despachadores;

public class ServidoresStat {

	int tipo;
	String ip;
	int puerto;
	String name;

	public ServidoresStat(int tipo, int puerto, String ip, String name) {
		this.ip = ip;
		this.name = name;
		this.puerto = puerto;
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}

	public String getIp() {
		return ip;
	}

	public int getPuerto() {
		return puerto;
	}

	public String getName() {
		return name;
	}

}
