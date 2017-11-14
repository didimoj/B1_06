import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Operaciones {

	public Stack busquedaAcotada(Problema prob, String estrategia, int profundidad_maxima) {
		Frontera frontera = new Frontera();
		Nodo nodoInicial = new Nodo(); // String i, Estado e, int prof, int c, int v, Nodo p, Acciones a
		ArrayList<Nodo> sucesores = new ArrayList<Nodo>();
		ArrayList<Nodo> listaNodos;
		boolean solucion;
		Nodo nodoActual;

		frontera.insertar(nodoInicial);
		solucion = false;

		while (solucion = false && !frontera.esVacia()) {
			nodoActual = frontera.eliminar();
			if (prob.esObjetivo(nodoActual)) {
				solucion = true;
			} else {
				sucesores.add(nodoActual);
				listaNodos = new ArrayList<Nodo>(sucesores, nodoActual, profundidad_maxima, estrategia);
				frontera.insertar(listaNodos);
			}
		}
		
		if (solucion) {
			return getSolucion(nodoActual);
		} else {
			return null;
		}
	}

	public Stack getSolucion(Nodo nodo) {
		Stack pila = new Stack();

		while (nodo.getParent() != null) {
			pila.add(nodo.getParent());
			nodo = nodo.getParent();
		}

		return pila;
	}

	public Stack busquedaIterativa(Problema prob, String estrategia, int prof_max, int inc_prof) {
		int prof_act = inc_prof;
		Stack solucion = null;

		while (solucion != null && prof_act <= prof_max) {
			solucion = busquedaAcotada(prob, estrategia, prof_act);
			prof_act = prof_act + inc_prof;
		}

		return solucion;
	}
}