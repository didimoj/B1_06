import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Operaciones {

	public Operaciones() {
	}

	public Queue<Nodo> busquedaAcotada(Problema prob, String estrategia, int profundidad_maxima)
			throws NoSuchAlgorithmException {
		Frontera frontera = new Frontera();
		Nodo nodoInicial = new Nodo(prob.getId(prob.getEstInicial()), prob.getEstInicial(), 0,
				tipoEstrategia(estrategia, profundidad_maxima, 0, 0, 0, prob.esObjetivo(prob.getEstInicial())), null,
				null, 0);
		ArrayList<Nodo> listaNodos;
		boolean solucion = false;
		Nodo nodoActual = new Nodo();
		int valor = 0;
		Hashtable<String, Nodo> visitados = new Hashtable<String, Nodo>();
		frontera.insertar(nodoInicial);
		boolean max = true;
		while (solucion == false && !frontera.esVacia() && max) {
			nodoActual = frontera.eliminar();
			visitados.put(nodoActual.getId(), nodoActual);
			if (prob.esObjetivo(nodoActual.getEstado()) == 0) { // AQUI SE PUEDE CAMBIAR LA FUNCION OBJETIVO CON BOOLEAN
				solucion = true;
			} else {
				if (nodoActual.getProf() > profundidad_maxima)
					max = false;
				else {

					ArrayList<Sucesor> sucesores = prob.getE().getSucesores(nodoActual.getEstado());

					// valor = tipoEstrategia(estrategia, profundidad_maxima, nodoActual.getCosto(),
					// nodoActual.getProf());
					// frontera.insertar(sucesores);
					for (int i = 0; i < sucesores.size(); i++) {
						Nodo n = new Nodo(prob.getId(sucesores.get(i).getEstado()), sucesores.get(i).getEstado(),
								nodoActual.getCosto() + sucesores.get(i).getAccion().getCosto(),
								tipoEstrategia(estrategia, profundidad_maxima, nodoActual.getCosto(),
										nodoActual.getProf() + 1, sucesores.get(i).getAccion().getCosto(),
										prob.esObjetivo(sucesores.get(i).getEstado())),
								nodoActual, sucesores.get(i).getAccion(), nodoActual.getProf() + 1);
						// System.out.println(n);
						if ((visitados.containsKey(n.getId()) && visitados.get(n.getId()).getValor() > n.getValor() )|| !visitados.containsKey(n.getId())) {
							frontera.insertar(n);
							visitados.put(n.getId(), n);
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

	public Queue<Nodo> busquedaIterativa(Problema prob, String estrategia, int prof_max, int inc_prof)
			throws NoSuchAlgorithmException {
		int prof_act = prof_max;
		Queue<Nodo> solucion = null;

		while (solucion == null/* && prof_act <= prof_max */) {
			solucion = busquedaAcotada(prob, estrategia, prof_act);
			prof_act += inc_prof;
		}

		return solucion;
	}

	public int tipoEstrategia(String est, int prof_max, int coste_act, int prof_act, int c, int heuristica) {
		int valor = 0;

		switch (est) {
		case "DFS":
			valor = prof_max - prof_act;
			break;
		case "BFS":
			valor = prof_act;
			break;
		case "CU":
			valor = coste_act + c;
			break;
		case "A*":
			valor = coste_act + c + heuristica;
			break;
		case "VORAZ":
			valor = heuristica;
			break;
		default:
			System.out.println("La estrategia elegida es errónea");
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
		// System.out.println("pila:" + pila.size());
		while (!pila.isEmpty()) {
			cola.add(pila.pop());
		}

		return cola;
	}
}