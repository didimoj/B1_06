import java.util.ArrayList;
import java.util.Arrays;
/**
 * 
 * @author oscarjm97 didimojavier Alberto Gomez Leon
 *
 */
public class EspacioEstados {
/**
 * 
 * @param l
 * @param a
 * @param e
 * @return new Estado
 */
	public Estado crearEstado(ArrayList<int[]> l, Acciones a, Estado e) {

		int[][] nuevo = new int[e.getTerreno().size() + 1][e.getTerreno().size() + 1];
		for (int f = 0; f < nuevo.length; f++) {
			for (int c = 0; c < nuevo.length; c++) {
				nuevo[f][c] = e.getTerreno().getCantidad(f, c);
			}
		}

		for (int i = 0; i < l.size(); i++) {
			nuevo[l.get(i)[0]][l.get(i)[1]] = e.getTerreno().getCantidad(l.get(i)[0], l.get(i)[1]) + a.getDist()[i];
		}

		if (nuevo[e.getTractorX()][e.getTractorY()] > e.getTerreno().K())
			nuevo[e.getTractorX()][e.getTractorY()] = e.getTerreno().K();

		return new Estado(new Terreno(e.getTerreno().K(), e.getTerreno().Max(), nuevo), a.getMov()[0], a.getMov()[1]);
	}
/**
 * 
 * @param e
 * @return sucesores
 */
	public ArrayList<Sucesor> getSucesores(Estado e) {
		ArrayList<int[]> l = crearLista(e);
		ArrayList<int[]> todas = new ArrayList<>();
		int cant;
		if ((e.getTerreno().getCantidad(e.getTractorX(), e.getTractorY()) > e.getTerreno().K()))
			cant = (e.getTerreno().getCantidad(e.getTractorX(), e.getTractorY()) - e.getTerreno().K());
		else
			cant = 0;

		todas = back(new int[l.size()], 0, todas, cant, l.size(), l, e);

		ArrayList<Acciones> candidatos = Distpos(l, todas);
		ArrayList<Sucesor> sucesores = new ArrayList<>();
		for (int k = 0; k < candidatos.size(); k++)
			sucesores.add(new Sucesor("acc" + k, candidatos.get(k), crearEstado(l, candidatos.get(k), e)));

		return sucesores;
	}
/**
 * 
 * @param l
 * @param todas
 * @return candidatos
 */
	public ArrayList<Acciones> Distpos(ArrayList<int[]> l, ArrayList<int[]> todas) {
		ArrayList<Acciones> candidatos = new ArrayList<>();
		for (int i = 0; i < l.size(); i++) {
			for (int j = 0; j < todas.size(); j++) {
				Acciones a = new Acciones(l.get(i), todas.get(j), l);
				candidatos.add(a);

			}
		}
		return candidatos;
	}
/**
 * 
 * @param is
 * @param l
 * @param e
 * @return flag
 */
	private boolean esPosible(int[] is, ArrayList<int[]> l, Estado e) {
		boolean flag = true;
		for (int i = 0; i < is.length; i++) {
			if (e.getTerreno().getTerreno()[l.get(i)[0]][l.get(i)[1]] + is[i] > e.getTerreno().Max())
				flag = false;
		}
		return flag;
	}
/**
 * 
 * @param e
 * @return lista
 */
	public static ArrayList<int[]> crearLista(Estado e) {

		ArrayList<int[]> lista = new ArrayList<int[]>();

		/* Si el tractor esta en una esquina del terreno, tiene dos casillas donde poder colocar tierra */
		
		if (e.getTractorX() == 0 && e.getTractorY() == 0) { // Esquina superior izquierda
			lista.add(new int[] { (e.getTractorX() + 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() + 1) });

		} else if (e.getTractorX() == 0 && e.getTractorY() == e.getTerreno().size()) { // Esquina inferior izquierda
			lista.add(new int[] { (e.getTractorX() + 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() - 1) });

		} else if (e.getTractorX() == e.getTerreno().size() && e.getTractorY() == 0) { // Esquina superior derecha
			lista.add(new int[] { (e.getTractorX() - 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() + 1) });

		} else if (e.getTractorX() == e.getTerreno().size() && e.getTractorY() == e.getTerreno().size()) { // Esquina inferior derecha

			lista.add(new int[] { (e.getTractorX() - 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() - 1) });
		}

		/* Si el tractor esta en el borde izquierdo, pero no en una esquina. Tiene tres casillas donde poder colocar tierra */
		
		else if (e.getTractorX() == 0 && e.getTractorY() != 0 && e.getTractorY() != e.getTerreno().size()) { // Lateral
																												// izquierdo
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() - 1) });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() + 1) });
			lista.add(new int[] { (e.getTractorX() + 1), e.getTractorY() });

		/* Si el tractor esta en el borde derecho, pero no en una esquina. Tiene tres casillas donde poder colocar tierra */
			
		} else if (e.getTractorX() == e.getTerreno().size() && e.getTractorY() != 0
				&& e.getTractorY() != e.getTerreno().size()) { // Lateral derecho
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() - 1) });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() + 1) });
			lista.add(new int[] { (e.getTractorX() - 1), e.getTractorY() });
		
		/* Si el tractor esta en el borde de arriba, pero no en una esquina. Tiene tres casillas donde poder colocar tierra */
			
		} else if (e.getTractorY() == 0 && e.getTractorX() != 0 && e.getTractorX() != e.getTerreno().size()) { // Arriba
			lista.add(new int[] { (e.getTractorX() - 1), e.getTractorY() });
			lista.add(new int[] { (e.getTractorX() + 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() + 1) });
			
		/* Si el tractor esta en el borde de abajo, pero no en una esquina. Tiene tres casillas donde poder colocar tierra */
			
		} else if (e.getTractorY() == e.getTerreno().size() && e.getTractorX() != 0
				&& e.getTractorX() != e.getTerreno().size()) { // Abajo
			lista.add(new int[] { (e.getTractorX() - 1), e.getTractorY() });
			lista.add(new int[] { (e.getTractorX() + 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() - 1) });
			
		/* Si el tractor esta en la mitad del terreno, tiene cuatro casillas donde poder colocar tierra */
			
		} else {
			lista.add(new int[] { (e.getTractorX() - 1), e.getTractorY() });
			lista.add(new int[] { (e.getTractorX() + 1), e.getTractorY() });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() - 1) });
			lista.add(new int[] { e.getTractorX(), (e.getTractorY() + 1) });
		}
		return lista;
	}
/**
 * 
 * @param actual
 * @param etapa
 * @param sol
 * @param max
 * @param ncasillas
 * @param l
 * @param e
 * @return sol
 */
	private ArrayList<int[]> back(int[] actual, int etapa, ArrayList<int[]> sol, int max, int ncasillas, ArrayList<int[]> l, Estado e) {
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
/**
 * 
 * @param actual
 * @param ncasillas
 * @param max
 * @return suma == max
 */
	private boolean suma(int[] actual, int ncasillas, int max) {
		int suma = 0;
		for (int i = 0; i < ncasillas; i++)
			suma += actual[i];
		return suma == max;
	}
/**
 * 
 * @param solar
 * @return s
 */
	public String imprimir(int[][] solar) {
		String s = "";
		for (int i = 0; i < solar.length; i++) {
			for (int j = 0; j < solar.length; j++) {
				s += solar[i][j] + " ";
			}
			s += "\n";
		}
		return s;
	}
}
