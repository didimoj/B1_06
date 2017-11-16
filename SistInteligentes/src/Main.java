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
		int contador=0;
		Nodo n;
		try {
			cola = p.getSolucion("A*", 20);
			while(!cola.isEmpty()) {
				n=cola.poll();
				if(n.getAccion()!=null)
				contador+=n.getAccion().getCosto();
				archivo.escribirArchivo("soluci0n.txt", n,escribir);
				escribir=true;
			}
			archivo.escribirArchivo("soluci0n.txt",contador,escribir);
			long t1=System.currentTimeMillis();
			System.out.println("HECHOO!!!! "+(t1-t0));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}

}
