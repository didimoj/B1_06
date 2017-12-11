import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * @author oscarjm97 didimojavier Alberto Gomez Leon
 *
 */
public class Operaciones {

	public Operaciones() {
	}

	/**
	 * 
	 * @param prob
	 * @param estrategia
	 * @param profundidad_maxima
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Queue<Nodo> busquedaAcotada(Problema prob, String estrategia, int profundidad_maxima)
			throws NoSuchAlgorithmException {
		Frontera frontera = new Frontera();
		Nodo nodoInicial = new Nodo(prob.getId(prob.getEstInicial()), prob.getEstInicial(), 0,
				tipoEstrategia(estrategia, profundidad_maxima, 0, 0, 0, prob.esObjetivo(prob.getEstInicial())), null,
				null, 0);
		boolean solucion = false;
		Nodo nodoActual = new Nodo();
		Hashtable<String, Nodo> visitados = new Hashtable<String, Nodo>();
		frontera.insertar(nodoInicial);
		while (solucion == false && !frontera.esVacia()) {
			nodoActual = frontera.eliminar();

			if (prob.esObjetivo(nodoActual.getEstado()) == 0) {
				solucion = true;
			} else {

				if (nodoActual.getProf() < profundidad_maxima) {

					ArrayList<Sucesor> sucesores = prob.getE().getSucesores(nodoActual.getEstado());
					for (int i = 0; i < sucesores.size(); i++) {
						Nodo nodo = new Nodo(prob.getId(sucesores.get(i).getEstado()), sucesores.get(i).getEstado(),
								nodoActual.getCosto() + sucesores.get(i).getAccion().getCosto(),
								tipoEstrategia(estrategia, profundidad_maxima, nodoActual.getCosto(),
										nodoActual.getProf() + 1, sucesores.get(i).getAccion().getCosto(),
										prob.esObjetivo(sucesores.get(i).getEstado())),
								nodoActual, sucesores.get(i).getAccion(), nodoActual.getProf() + 1);

						if ((visitados.containsKey(nodo.getId())
								&& visitados.get(nodo.getId()).getValor() > nodo.getValor())
								|| !visitados.containsKey(nodo.getId())) {
							frontera.insertar(nodo);
							visitados.put(nodo.getId(), nodo);
						}
					}
				}
			}
		}

		if (solucion) {
			return getSolucion(nodoActual);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * @param prob
	 * @param estrategia
	 * @param prof_max
	 * @param inc_prof
	 * @return solucion
	 * @throws NoSuchAlgorithmException
	 */
	public Queue<Nodo> busquedaIterativa(Problema prob, String estrategia, int prof_max, int inc_prof)
			throws NoSuchAlgorithmException {
		int prof_act = inc_prof;
		Queue<Nodo> solucion = null;

		while (solucion == null && prof_act <= prof_max) {
			solucion = busquedaAcotada(prob, estrategia, prof_act);
			prof_act += inc_prof;
		}

		return solucion;
	}
	/**
	 * 
	 * @param estra
	 * @param prof_max
	 * @param coste_act
	 * @param prof_act
	 * @param coste
	 * @param heuristica
	 * @return valor
	 */
	public int tipoEstrategia(String estra, int prof_max, int coste_act, int prof_act, int coste, int heuristica) {
		int valor = 0;

		switch (estra) {
		case "DFS":
			valor = prof_max - prof_act;
			break;
		case "BFS":
			valor = prof_act;
			break;
		case "CU":
			valor = coste_act + coste;
			break;
		case "A":
			valor = coste_act + coste + heuristica;
			break;
		case "VORAZ":
			valor = heuristica;
			break;
		default:
			System.out.println("La estrategia elegida es erronea");
		}

		return valor;
	}

	public Queue<Nodo> getSolucion(Nodo nodo) {
		Stack<Nodo> pila = new Stack<Nodo>();
		Queue<Nodo> cola = new LinkedBlockingQueue<Nodo>();

		while (nodo != null) {
			pila.add(nodo);
			nodo = nodo.getParent();
		}
		while (!pila.isEmpty()) {
			cola.add(pila.pop());
		}

		return cola;
	}
}