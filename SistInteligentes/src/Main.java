import java.security.NoSuchAlgorithmException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * @author oscarjm97 didimojavier Alberto Gomez Leon
 *
 */
public class Main {

	public static void main(String[] args) {
		long t0 = System.currentTimeMillis();
		gestionArchivo archivo = new gestionArchivo();
		Estado estInicial = archivo.cargarArchivo("Problema.txt");
		Problema prob = new Problema(estInicial);

		Queue<Nodo> cola = new LinkedBlockingQueue<Nodo>();
		boolean escribir = false;
		int contador = 0;
		Nodo nodo;
		String estrategia = leer.cadena("Inserte la estrategia deseada: ");
		try {
			cola = prob.getSolucion(estrategia, 20);
			String file = estrategia + "_sol.txt";
			while (!cola.isEmpty()) {
				nodo = cola.poll();
				if (nodo.getAccion() != null)
					contador += nodo.getAccion().getCosto();
				archivo.escribirArchivo(file, nodo, escribir);
				escribir = true;
			}
			archivo.escribirArchivo(file, contador, escribir);
			long t1 = System.currentTimeMillis();
			System.out.println("Ejecutado en " + (t1 - t0) + " ms");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
