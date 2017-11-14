import java.security.NoSuchAlgorithmException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	public static void main(String[] args) {
		gestionArchivo archivo=new gestionArchivo();
		Estado estInicial=archivo.cargarArchivo("terren0.txt");

		Problema p=new Problema(estInicial);
		
		Queue<Nodo> cola = new LinkedBlockingQueue<Nodo>(); 
		boolean escribir=false;
		try {
			cola = p.getSolucion("DFS", 5);
			System.out.println("--" + cola.size());
			while(!cola.isEmpty()) {
				
				archivo.escribirArchivo("soluci0n.txt", cola.poll(),escribir);
				escribir=true;
			}
			System.out.println("HECHO!!!!");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
