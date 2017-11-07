import java.util.ArrayList;
import java.util.Arrays;

public class EspacioEstados {

	public Estado crearEstado(ArrayList<int[]> l, Acciones a, Estado e) {

		int[][] nuevo = new int[e.getTerreno().size() + 1][e.getTerreno().size() + 1];
		for (int f = 0; f < nuevo.length; f++) {
			for (int c = 0; c < nuevo.length; c++) {
				nuevo[f][c] = e.getTerreno().getCantidad(f, c);
			}
		}
		// System.arraycopy(getTerreno().getTerreno()[f], 0, nuevo[f], 0,
		// nuevo[f].length);

		for (int i = 0; i < l.size(); i++) {
			nuevo[l.get(i)[0]][l.get(i)[1]] = e.getTerreno().getCantidad(l.get(i)[0], l.get(i)[1]) + a.getDist()[i];
		}

		if (nuevo[e.getTractorX()][e.getTractorY()] > e.getTerreno().K())
			nuevo[e.getTractorX()][e.getTractorY()] = e.getTerreno().K();

		// if (!nv.esObjetivo())
		// nv.distribucion();

		return new Estado(new Terreno(e.getTerreno().K(), e.getTerreno().Max(), nuevo), a.getMov()[0], a.getMov()[1]);
	}

	/*
	 * public boolean esObjetivo() { boolean flag = true; for (int i = 0; i <
	 * t.size() + 1 && flag; i++) { for (int j = 0; j < t.size() + 1 && flag; j++) {
	 * flag = (t.getCantidad(i, j) == t.K()); } } return flag; }
	 * 
	 * @Override public String toString() { return
	 * imprimir(t.getTerreno())+"\n("+getTractorX()+", "+getTractorY()+")"; }
	 */
	public ArrayList<Sucesor> getSucesores(Estado e) {
		ArrayList<int[]> l = crearLista(e);
		ArrayList<int[]> todas1 = new ArrayList<>();
		int cant;
		if ((e.getTerreno().getCantidad(e.getTractorX(), e.getTractorY()) > e.getTerreno().K()))
			cant = (e.getTerreno().getCantidad(e.getTractorX(), e.getTractorY()) - e.getTerreno().K());
		else
			cant = 0;

		todas1 = back(new int[l.size()], 0, todas1, cant, l.size(), l, e);
		// System.out.println("\n------- DISTRIBUCCIONES POSIBLES --------");

		ArrayList<Acciones> candidatos = Distpos(l, todas1);
		ArrayList<Sucesor> sucesores = new ArrayList<>();
		for (int k = 0; k < candidatos.size(); k++)
			sucesores.add(new Sucesor("acc" + k, candidatos.get(k), crearEstado(l, candidatos.get(k), e)));

		/*
		 * ArrayList<Estado> sucesores =new ArrayList<>(); for(int
		 * k=0;k<candidatos.size();k++)
		 * sucesores.add(crearEstado(l,candidatos.get(k),e));
		 * 
		 * 
		 * 
		 * /* for (int i = 0; i < sucesores.size(); i++)
		 * System.out.println(sucesores.get(i));
		 * //System.out.println(candidatos.size()); /* // Elegimos una accion al azar
		 * Random r = new Random(); int k = r.nextInt(candidatos.size());
		 * System.out.println("Realizar accion: " + candidatos.get(k)); crearEstado(l,
		 * candidatos.get(k));
		 */
		return sucesores;
	}

	public ArrayList<Acciones> Distpos(ArrayList<int[]> l, ArrayList<int[]> todas1) {
		ArrayList<Acciones> candidatos = new ArrayList<>();
		for (int i = 0; i < l.size(); i++) {
			for (int j = 0; j < todas1.size(); j++) {
				Acciones a = new Acciones(l.get(i), todas1.get(j), l);
				candidatos.add(a);

			}
		}
		return candidatos;
	}

	private boolean esPosible(int[] is, ArrayList<int[]> l, Estado e) {
		boolean flag = true;
		for (int i = 0; i < is.length; i++) {
			if (e.getTerreno().getTerreno()[l.get(i)[0]][l.get(i)[1]] + is[i] > e.getTerreno().Max())
				flag = false;
		}
		return flag;
	}

	public static ArrayList<int[]> crearLista(Estado e) {

		ArrayList<int[]> lista = new ArrayList<int[]>();

		if (e.getTractorX() == 0 && e.getTractorY() == 0) { // Esquina superior izquierda
			lista.add(new int[] { (e.getTractorX() + 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() + 1) });

		} else if (e.getTractorX() == 0 && e.getTractorY() == e.getTerreno().size()) { // Esquina inferior izquierda
			lista.add(new int[] { (e.getTractorX() + 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() - 1) });

		} else if (e.getTractorX() == e.getTerreno().size() && e.getTractorY() == 0) { // Esquina superior derecha
			lista.add(new int[] { (e.getTractorX() - 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() + 1) });

		} else if (e.getTractorX() == e.getTerreno().size() && e.getTractorY() == e.getTerreno().size()) { // Esquina
																											// inferior
																											// derecha

			lista.add(new int[] { (e.getTractorX() - 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() - 1) });
		}
		// es decir si el tractor esta en una esquina del terreno
		// si esta en una esquina solo habra dos sitios donde colocarlo
		// hacer como se puede coger y hacer las combinaciones de la cantidad menos k
		// entre dos casillas y meterlas en una lista

		else if (e.getTractorX() == 0 && e.getTractorY() != 0 && e.getTractorY() != e.getTerreno().size()) { // Lateral
																												// izquierdo
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() - 1) });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() + 1) });
			lista.add(new int[] { (e.getTractorX() + 1), e.getTractorY() });

			// es decir, si el tractor esta en el borde izquierdo pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones
		} else if (e.getTractorX() == e.getTerreno().size() && e.getTractorY() != 0
				&& e.getTractorY() != e.getTerreno().size()) { // Lateral derecho
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() - 1) });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() + 1) });
			lista.add(new int[] { (e.getTractorX() - 1), e.getTractorY() });
			// es decir, si el tractor esta en el borde derecho pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones

			// lo diferencio para que sea automatico y mas facil de cojer luego
		} else if (e.getTractorY() == 0 && e.getTractorX() != 0 && e.getTractorX() != e.getTerreno().size()) { // Arriba
			lista.add(new int[] { (e.getTractorX() - 1), e.getTractorY() });
			lista.add(new int[] { (e.getTractorX() + 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() + 1) });
			// es decir, si el tractor esta en el borde de arriba pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones
		} else if (e.getTractorY() == e.getTerreno().size() && e.getTractorX() != 0
				&& e.getTractorX() != e.getTerreno().size()) { // Abajo
			lista.add(new int[] { (e.getTractorX() - 1), e.getTractorY() });
			lista.add(new int[] { (e.getTractorX() + 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() - 1) });
			// es decir, si el tractor esta en el borde de abajo pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones
		} else {
			lista.add(new int[] { (e.getTractorX() - 1), e.getTractorY() });
			lista.add(new int[] { (e.getTractorX() + 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() - 1) });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() + 1) });
			// esta en mitad y tendra 4 posibilidades para distribuir
		}
		return lista;
	}

	private ArrayList<int[]> back(int[] actual, int etapa, ArrayList<int[]> sol, int max, int ncasillas,
			ArrayList<int[]> l, Estado e) {
		int[] copia = new int[actual.length];
		if (etapa == ncasillas) {
			if (suma(actual, ncasillas, max) && esPosible(actual, l, e)) {
				System.arraycopy(actual, 0, copia, 0, actual.length);
				Arrays.toString(copia);
				sol.add(copia);

			}

		} else {
			for (int cantidad = 0; cantidad <= max; cantidad++) {

				actual[etapa] = cantidad;
				back(actual, etapa + 1, sol, max, ncasillas, l, e);

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

	public String imprimir(int[][] solar) {
		String s = "";
		for (int i = 0; i < solar.length; i++) {
			for (int j = 0; j < solar.length; j++) {
				// System.out.print(solar[i][j] + "\t");
				s += solar[i][j] + " ";
			}
			s += "\n";
			// System.out.println();
		}
		return s;
	}
}
