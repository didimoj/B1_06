import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author oscarjm97 didimojavier Alberto Gomez Leon
 *
 */
public class gestionArchivo {

	/**
	 * 
	 * @param file
	 * @return null
	 */
	public Estado cargarArchivo(String file) {
		int x, y, k, max, c, f;

		try {
			FileReader fr = new FileReader(new File(file));
			Scanner pantalla = new Scanner(fr);
			x = pantalla.nextInt();
			y = pantalla.nextInt();
			k = pantalla.nextInt();
			max = pantalla.nextInt();
			c = pantalla.nextInt();
			f = pantalla.nextInt();
			
			int cantidad = 0;
			int[][] terreno = new int[f][c];
			for (int i = 0; i < f; i++) {
				for (int j = 0; j < c; j++) {
					terreno[i][j] = pantalla.nextInt();
					cantidad += terreno[i][j];
				}
			}
			pantalla.close();
			if(y<c && x<f)
			if (cantidad == c * f * k ) {
				imprimir(terreno);

				Terreno t = new Terreno(k, max, terreno);

				Estado e = new Estado(t, x, y);
				return e;
			} else {
				System.out.println("La cantidad de arena a distribuir no es correcta");
				System.exit(0);
			}else
				System.out.println("El tractor esta fuera de los limites del terreno");
				System.exit(0);

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * 
	 * @param path
	 * @param nodo
	 * @param escribir
	 */
	public void escribirArchivo(String path, Nodo nodo, boolean escribir) {
		File file = new File(path);
		try {
			FileWriter fw = new FileWriter(file, escribir);
			BufferedWriter bw = new BufferedWriter(fw);
			if (nodo.getAccion() != null)
				bw.write(nodo.getAccion().toString() + " " + nodo.getValor() + " " + nodo.getProf() + " "
						+ nodo.getCosto() + "\n" + nodo.getEstado() + "\n");
			bw.newLine();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param path
	 * @param costoTotal
	 * @param escribir
	 *            Metodo para escribir el coste total en el ficher
	 */
	public void escribirArchivo(String path, int costoTotal, boolean escribir) {
		File file = new File(path);
		try {
			FileWriter fw = new FileWriter(file, escribir);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Coste total del algoritmo: " + costoTotal + "\n");
			bw.newLine();

			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param solar
	 */
	public static void imprimir(int[][] solar) {
		for (int i = 0; i < solar.length; i++) {
			for (int j = 0; j < solar[0].length; j++) {
				System.out.print(solar[i][j] + "\t");
			}
			System.out.println();
		}
	}
}