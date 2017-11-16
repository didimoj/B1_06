import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class gestionArchivo {

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
			if (cantidad == c * f * k) {
				imprimir(terreno);

				Terreno t = new Terreno(/* x, y, */k, max, terreno);

				Estado e = new Estado(t, x, y);
				return e;
			} else {
				System.out.println("La cantidad de arena a distribuir no es correcta");
			}

			// escribirArchivo("terreno.txt",0,3,3,12,3,3,terreno);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}

	public void escribirArchivo(String path, Nodo nodo,boolean escribir) {
		File file = new File(path);
		try {
			FileWriter fw = new FileWriter(file, escribir);
			BufferedWriter bw = new BufferedWriter(fw);
			// bw.newLine();
			// bw.write("------------------");
			// bw.newLine();
			if (nodo.getAccion()!=null)
				bw.write(nodo.getAccion().toString() + "\n");
			bw.newLine();
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void escribirArchivo(String path, int costoTotal,boolean escribir) {
		File file = new File(path);
		try {
			FileWriter fw = new FileWriter(file, escribir);
			BufferedWriter bw = new BufferedWriter(fw);
			// bw.newLine();
			// bw.write("------------------");
			// bw.newLine();
			
			bw.write("Coste total del algoritmo: "+costoTotal+ "\n");
			bw.newLine();
			
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void imprimir(int[][] solar) {
		for (int i = 0; i < solar.length; i++) {
			for (int j = 0; j < solar.length; j++) {
				System.out.print(solar[i][j] + "\t");
			}
			System.out.println();
		}
	}
}