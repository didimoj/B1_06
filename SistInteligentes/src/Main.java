import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String[] args) {
		gestionArchivo yo=new gestionArchivo();
		Estado estInicial=yo.cargarArchivo("terreno.txt");
		Agente a=new Agente();
		try {
			a.getSolucion(estInicial);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}

}
