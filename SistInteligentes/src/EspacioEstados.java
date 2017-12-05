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
 * @param distribuciones
 * @param accion
 * @param est
 * @return new Estado
 */
	public Estado crearEstado(ArrayList<int[]> distribuciones, Acciones accion, Estado est) {

		int[][] nuevo = new int[est.getTerreno().getTerreno().length][est.getTerreno().getTerreno()[0].length];
		for (int f = 0; f < nuevo.length; f++) {
			for (int c = 0; c < nuevo[0].length; c++) {
				nuevo[f][c] = est.getTerreno().getCantidad(f, c);
			}
		}

		for (int i = 0; i < distribuciones.size(); i++) {
			nuevo[distribuciones.get(i)[0]][distribuciones.get(i)[1]] = est.getTerreno().getCantidad(distribuciones.get(i)[0], distribuciones.get(i)[1]) + accion.getDist()[i];
		}

		if (nuevo[est.getTractorX()][est.getTractorY()] > est.getTerreno().K())
			nuevo[est.getTractorX()][est.getTractorY()] = est.getTerreno().K();

		return new Estado(new Terreno(est.getTerreno().K(), est.getTerreno().Max(), nuevo), accion.getMov()[0], accion.getMov()[1]);
	}
/**
 * 
 * @param est
 * @return sucesores
 */
	public ArrayList<Sucesor> getSucesores(Estado est) {
		ArrayList<int[]> distribuciones = crearLista(est);
		ArrayList<int[]> posiciones = new ArrayList<>();
		int cant;
		if ((est.getTerreno().getCantidad(est.getTractorX(), est.getTractorY()) > est.getTerreno().K()))
			cant = (est.getTerreno().getCantidad(est.getTractorX(), est.getTractorY()) - est.getTerreno().K());
		else
			cant = 0;

		posiciones = back(new int[distribuciones.size()], 0, posiciones, cant, distribuciones.size(), distribuciones, est);

		ArrayList<Acciones> candidatos = Distpos(distribuciones, posiciones);
		ArrayList<Sucesor> sucesores = new ArrayList<>();
		for (int k = 0; k < candidatos.size(); k++)
			sucesores.add(new Sucesor("acc" + k, candidatos.get(k), crearEstado(distribuciones, candidatos.get(k), est)));

		return sucesores;
	}
/**
 * 
 * @param distribuciones
 * @param posiciones
 * @return candidatos
 */
	public ArrayList<Acciones> Distpos(ArrayList<int[]> distribuciones, ArrayList<int[]> posiciones) {
		ArrayList<Acciones> candidatos = new ArrayList<>();
		for (int i = 0; i < distribuciones.size(); i++) {
			for (int j = 0; j < posiciones.size(); j++) {
				Acciones a = new Acciones(distribuciones.get(i), posiciones.get(j), distribuciones);
				candidatos.add(a);

			}
		}
		return candidatos;
	}
/**
 * 
 * @param is
 * @param distribuciones
 * @param est
 * @return flag
 */
	private boolean esPosible(int[] is, ArrayList<int[]> distribuciones, Estado est) {
		boolean flag = true;
		for (int i = 0; i < is.length; i++) {
			if (est.getTerreno().getTerreno()[distribuciones.get(i)[0]][distribuciones.get(i)[1]] + is[i] > est.getTerreno().Max())
				flag = false;
		}
		return flag;
	}
/**
 * 
 * @param est
 * @return lista
 */
	public static ArrayList<int[]> crearLista(Estado est) {

		ArrayList<int[]> lista = new ArrayList<int[]>();
		

		/* Si el tractor esta en una esquina del terreno, tiene dos casillas donde poder colocar tierra */
		
		if (est.getTractorX() == 0 && est.getTractorY() == 0) { // Esquina superior izquierda
			lista.add(new int[] { (est.getTractorX() + 1), est.getTractorY() });
			lista.add(new int[] { est.getTractorX(), (est.getTractorY() + 1) });

		} else if (est.getTractorX() == est.getTerreno().getFilas()-1 && est.getTractorY() ==0) { // Esquina inferior izquierda
			lista.add(new int[] { (est.getTractorX() - 1), est.getTractorY() });
			lista.add(new int[] { est.getTractorX(), (est.getTractorY() + 1) });

		} else if (est.getTractorX() == 0 && est.getTractorY() == est.getTerreno().getColumnas()-1) { // Esquina superior derecha
			lista.add(new int[] { (est.getTractorX()), est.getTractorY() - 1});
			lista.add(new int[] { est.getTractorX()+1, (est.getTractorY()) });

		} else if (est.getTractorX() == est.getTerreno().getFilas()-1 && est.getTractorY() == est.getTerreno().getColumnas() - 1) { // Esquina inferior derecha

			lista.add(new int[] { (est.getTractorX() - 1), est.getTractorY() });
			lista.add(new int[] { est.getTractorX(), (est.getTractorY() - 1) });
		}

		/* Si el tractor esta en el borde izquierdo, pero no en una esquina. Tiene tres casillas donde poder colocar tierra */
		
		else if (est.getTractorX() == 0 && est.getTractorY() != 0 && est.getTractorY() !=est.getTerreno().getColumnas()-1) { // Lateral
																												// arriba
			lista.add(new int[] { est.getTractorX(), (est.getTractorY() - 1) });
			lista.add(new int[] { est.getTractorX(), (est.getTractorY() + 1) });
			lista.add(new int[] { (est.getTractorX() + 1), est.getTractorY() });

		/* Si el tractor esta en el borde derecho, pero no en una esquina. Tiene tres casillas donde poder colocar tierra */
			
		} else if (est.getTractorX() == est.getTerreno().getFilas() - 1 && est.getTractorY() != 0
				&& est.getTractorY() !=est.getTerreno().getColumnas() - 1) { // Lateral abajo
			lista.add(new int[] { est.getTractorX(), (est.getTractorY() - 1) });
			lista.add(new int[] { est.getTractorX(), (est.getTractorY() + 1) });
			lista.add(new int[] { (est.getTractorX() - 1), est.getTractorY() });
		
		/* Si el tractor esta en el borde de arriba, pero no en una esquina. Tiene tres casillas donde poder colocar tierra */
			
		} else if (est.getTractorY() == 0 && est.getTractorX() != 0 && est.getTractorX() != est.getTerreno().getFilas() - 1) { // izquierda
			lista.add(new int[] { (est.getTractorX() - 1), est.getTractorY() });
			lista.add(new int[] { (est.getTractorX() + 1), est.getTractorY() });
			lista.add(new int[] { est.getTractorX(), (est.getTractorY() + 1) });
			
		/* Si el tractor esta en el borde de abajo, pero no en una esquina. Tiene tres casillas donde poder colocar tierra */
			
		} else if (est.getTractorY() == est.getTerreno().getColumnas() - 1 && est.getTractorX() != 0
				&& est.getTractorX() != est.getTerreno().getFilas() - 1) { // derecha
			lista.add(new int[] { (est.getTractorX() - 1), est.getTractorY() });
			lista.add(new int[] { (est.getTractorX() + 1), est.getTractorY() });
			lista.add(new int[] { est.getTractorX(), (est.getTractorY() - 1) });
			
		/* Si el tractor esta en la mitad del terreno, tiene cuatro casillas donde poder colocar tierra */
			
		} else {
			lista.add(new int[] { (est.getTractorX() - 1), est.getTractorY() });
			lista.add(new int[] { (est.getTractorX() + 1), est.getTractorY() });
			lista.add(new int[] { est.getTractorX(), (est.getTractorY() - 1) });
			lista.add(new int[] { est.getTractorX(), (est.getTractorY() + 1) });
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
 * @param distribuciones
 * @param est
 * @return sol
 */
	private ArrayList<int[]> back(int[] actual, int etapa, ArrayList<int[]> sol, int max, int ncasillas, ArrayList<int[]> distribuciones, Estado est) {
		int[] copia = new int[actual.length];
		if (etapa == ncasillas) {
			if (suma(actual, ncasillas, max) && esPosible(actual, distribuciones, est)) {
				System.arraycopy(actual, 0, copia, 0, actual.length);
				Arrays.toString(copia);
				sol.add(copia);
			}

		} else {
			for (int cantidad = 0; cantidad <= max; cantidad++) {
				actual[etapa] = cantidad;
				back(actual, etapa + 1, sol, max, ncasillas, distribuciones, est);
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
			for (int j = 0; j < solar[0].length; j++) {
				s += solar[i][j] + " ";
			}
			s += "\n";
		}
		return s;
	}
}
