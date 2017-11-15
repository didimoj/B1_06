import java.security.NoSuchAlgorithmException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	public static void main(String[] args) {
		long t0=System.currentTimeMillis();
		gestionArchivo archivo=new gestionArchivo();
		Estado estInicial=archivo.cargarArchivo("terren0.txt");

		Problema p=new Problema(estInicial);
		
		Queue<Nodo> cola = new LinkedBlockingQueue<Nodo>(); 
		boolean escribir=false;
		try {
			cola = p.getSolucion("DFS", 10);
			System.out.println("--" + cola.size());
			while(!cola.isEmpty()) {
				
				archivo.escribirArchivo("soluci0n.txt", cola.poll(),escribir);
				escribir=true;
			}
			long t1=System.currentTimeMillis();
			System.out.println("HECHOO!!!! "+(t1-t0));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
