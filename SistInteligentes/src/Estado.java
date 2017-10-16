import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Estado {
	private static Terreno t;
	private static int tractorX;
	private static int tractorY;

	public Estado(Terreno t, int tractorx, int tractory) {
		this.t = t;
		tractorX = tractorx;
		tractorY = tractory;
	}

	public void crearEstado(ArrayList<int[]> l, Acciones a) {
		int[][] nuevo = new int[t.size() + 1][t.size() + 1];
		for (int f = 0; f < nuevo.length; f++)
			System.arraycopy(t.getTerreno()[f], 0, nuevo[f], 0, nuevo[f].length);

		for (int i = 0; i < l.size(); i++) {
			nuevo[l.get(i)[0]][l.get(i)[1]] = t.getCantidad(l.get(i)[0], l.get(i)[1]) + a.getDist()[i];
		}
		if (nuevo[tractorX][tractorY] > t.K())
			nuevo[tractorX][tractorY] = t.K();

		Terreno n = new Terreno(t.K(), t.Max(), nuevo);
		imprimir(nuevo);

		Estado nv = new Estado(n, a.getMov()[0], a.getMov()[1]);
		if (!nv.esObjetivo())
			nv.distribucion();
	}

	public boolean esObjetivo() {
		boolean flag = true;
		for (int i = 0; i < t.size() + 1 && flag; i++) {
			for (int j = 0; j < t.size() + 1 && flag; j++) {
				flag = (t.getCantidad(i, j) == t.K());
			}
		}
		return flag;
	}

	public void distribucion() {
		ArrayList<int[]> l = crearLista();
		ArrayList<int[]> todas1 = new ArrayList<>();
		int cant;
		if ((t.getCantidad(tractorX, tractorY) > t.K()))
			cant = (t.getCantidad(tractorX, tractorY) - t.K());
		else
			cant = 0;

		todas1 = back(new int[l.size()], 0, todas1, cant, l.size(), l);
		System.out.println("\n------- DISTRIBUCCIONES POSIBLES --------");

		ArrayList<Acciones> candidatos = Distpos(l, todas1);
		for (int i = 0; i < candidatos.size(); i++)
			System.out.println(candidatos.get(i));
		System.out.println(candidatos.size());

		// Elegimos una accion al azar
		Random r = new Random();
		int k = r.nextInt(candidatos.size());
		System.out.println("Realizar accion: " + candidatos.get(k));
		crearEstado(l, candidatos.get(k));

	}

	public ArrayList<Acciones> Distpos(ArrayList<int[]> l, ArrayList<int[]> todas1) {
		ArrayList<Acciones> candidatos = new ArrayList<>();
		for (int i = 0; i < l.size(); i++) {
			for (int j = 0; j < todas1.size(); j++) {
				Acciones a = new Acciones(l.get(i), todas1.get(j));
				candidatos.add(a);

			}
		}
		return candidatos;
	}

	private boolean esPosible(int[] is, ArrayList<int[]> l) {
		boolean flag = true;
		for (int i = 0; i < is.length; i++) {
			if (t.getTerreno()[l.get(i)[0]][l.get(i)[1]] + is[i] > t.Max())
				flag = false;
		}
		return flag;
	}

	public static ArrayList<int[]> crearLista() {

		ArrayList<int[]> lista = new ArrayList<int[]>();

		if (tractorX == 0 && tractorY == 0) { // Esquina superior izquierda
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY + 1) });

		} else if (tractorX == 0 && tractorY == t.size()) { // Esquina inferior izquierda
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY - 1) });

		} else if (tractorX == t.size() && tractorY == 0) { // Esquina superior derecha
			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY + 1) });

		} else if (tractorX == t.size() && tractorY == t.size()) { // Esquina inferior derecha

			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY - 1) });
		}
		// es decir si el tractor esta en una esquina del terreno
		// si esta en una esquina solo habra dos sitios donde colocarlo
		// hacer como se puede coger y hacer las combinaciones de la cantidad menos k
		// entre dos casillas y meterlas en una lista

		else if (tractorX == 0 && tractorY != 0 && tractorY != t.size()) { // Lateral izquierdo
			lista.add(new int[] { tractorX, (tractorY - 1) });
			lista.add(new int[] { tractorX, (tractorY + 1) });
			lista.add(new int[] { (tractorX + 1), tractorY });

			// es decir, si el tractor esta en el borde izquierdo pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones
		} else if (tractorX == t.size() && tractorY != 0 && tractorY != t.size()) { // Lateral derecho
			lista.add(new int[] { tractorX, (tractorY - 1) });
			lista.add(new int[] { tractorX, (tractorY + 1) });
			lista.add(new int[] { (tractorX - 1), tractorY });
			// es decir, si el tractor esta en el borde derecho pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones

			// lo diferencio para que sea automatico y mas facil de cojer luego
		} else if (tractorY == 0 && tractorX != 0 && tractorX != t.size()) { // Arriba
			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY + 1) });
			// es decir, si el tractor esta en el borde de arriba pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones
		} else if (tractorY == t.size() && tractorX != 0 && tractorX != t.size()) { // Abajo
			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY - 1) });
			// es decir, si el tractor esta en el borde de abajo pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones
		} else {
			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY - 1) });
			lista.add(new int[] { tractorX, (tractorY + 1) });
			// esta en mitad y tendra 4 posibilidades para distribuir
		}
		return lista;
	}

	private ArrayList<int[]> back(int[] actual, int etapa, ArrayList<int[]> sol, int max, int ncasillas,
			ArrayList<int[]> l) {
		int[] copia = new int[actual.length];
		if (etapa == ncasillas) {
			if (suma(actual, ncasillas, max) && esPosible(actual, l)) {
				System.arraycopy(actual, 0, copia, 0, actual.length);
				Arrays.toString(copia);
				sol.add(copia);

			}

		} else {
			for (int cantidad = 0; cantidad <= max; cantidad++) {

				actual[etapa] = cantidad;
				back(actual, etapa + 1, sol, max, ncasillas, l);

			}
		}
		return sol;
	}

	private boolean suma(int[] actual, int ncasillas, int max) {
		int suma = 0;
		for (int i = 0; i < ncasillas; i++)
			suma += actual[i];
		return suma == max;
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