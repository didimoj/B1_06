import java.security.NoSuchAlgorithmException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

	public static void main(String[] args) {
		long t0=System.currentTimeMillis();
		gestionArchivo archivo=new gestionArchivo();
		Estado estInicial=archivo.cargarArchivo("Problema.txt");
		Problema p=new Problema(estInicial);
		
		Queue<Nodo> cola = new LinkedBlockingQueue<Nodo>(); 
		boolean escribir=false;
		int contador=0;
		Nodo n;
		String estrategia=leer.cadena("Inserte la estrategia deseada: ");
		try {
			cola = p.getSolucion(estrategia, 20);
			String file=estrategia+"_sol.txt";
			while(!cola.isEmpty()) {
				n=cola.poll();
				if(n.getAccion()!=null)
				contador+=n.getAccion().getCosto();
				archivo.escribirArchivo(file, n,escribir);
				escribir=true;
			}
			archivo.escribirArchivo(file,contador,escribir);
			long t1=System.currentTimeMillis();
			System.out.println("HECHOO!!!! "+(t1-t0)+" ms");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
