import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String[] args) {
		gestionArchivo archivo=new gestionArchivo();
		Estado estInicial=archivo.cargarArchivo("terreno.txt");
		
		Problema p=new Problema(estInicial);
		
		try {
			p.getSolucion();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
