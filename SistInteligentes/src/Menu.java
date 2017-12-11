import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * @author oscarjm97 didimojavier Alberto Gomez Leon
 *
 */
public class Menu {
	/**
	 * 
	 * @param estrategia
	 * @param prof_max
	 * @throws FileNotFoundException
	 */
	public void realizarEstrategia(String estrategia, int prof_max, String path,Estado estInicial) throws FileNotFoundException {
		int contador = 0;
		gestionArchivo archivo = new gestionArchivo();
		Problema prob = new Problema(estInicial);
		Queue<Nodo> cola = new LinkedBlockingQueue<Nodo>();
		Nodo nodo;
		boolean escribir = false;
		long t0 = System.currentTimeMillis();

		try {
			cola = prob.getSolucion(estrategia, prof_max);
			String file = estrategia + "_sol.txt";
			while (!cola.isEmpty() && cola != null) {
				nodo = cola.poll();
				if (nodo.getAccion() != null)
					contador += nodo.getAccion().getCosto();
				archivo.escribirArchivo(file, nodo, escribir);
				escribir = true;
			}
			archivo.escribirArchivo(file, contador, escribir);
			long t1 = System.currentTimeMillis();
			abrirArchivo(estrategia + "_sol.txt");
			System.out.println("Ejecutado en " + (t1 - t0) + " ms");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NullPointerException ex) {
			System.out.println("\nNo se ha encontrado solucion");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 * @throws Exception
	 */
	
	public void menu(String path) throws Exception {
		int choose, prof_max;
		boolean bandera = false;
		String estrategia = "";
		gestionArchivo archivo = new gestionArchivo();
		Estado estInicial = archivo.cargarArchivo(path);

		do {
			choose = leer.entero(
					"\nElija la estrategia deseada:\n\n1. BFS (anchura)\n2. DFS (profundidad)\n3. Coste Uniforme\n4. A*\n5. Voraz\n6. Salir",
					1, 6);

			switch (choose) {
			case 1:
				estrategia = "BFS";
				break;
			case 2:
				estrategia = "DFS";
				break;
			case 3:
				estrategia = "CU";
				break;
			case 4:
				estrategia = "A";
				break;
			case 5:
				estrategia = "VORAZ";
				break;
			case 6:
				bandera = true;
				break;
			}

			if (!bandera) {
				prof_max = leer.entero("Indique la profundidad m�xima deseada: ");
				realizarEstrategia(estrategia, prof_max,path,estInicial);
			}

		} while (bandera == false);
	}

	public void abrirArchivo(String archivo) {

		try {
			File objetofile = new File(archivo);
			Desktop.getDesktop().open(objetofile);

		} catch (IOException ex) {
			System.out.println(ex);
		}

	}

}
