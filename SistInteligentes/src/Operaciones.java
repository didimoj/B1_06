import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Operaciones {

	public boolean busquedaAcotada(Problema prob, Estrategias est, profundidad_maxima) {
		Frontera frontera = new Frontera();
		Nodo nodoInicial = new Nodo(getId(estInicial), estInicial, 0, r.nextInt(40000), 1, null, null);
		ArrayList<Nodo> sucesores = new ArrayList<Nodo>();
		ArrayList<Nodo> listaNodos;
		boolean solucion;
		Nodo nodoActual;
		
		frontera.insertar(nodoInicial);
		solucion = false;
		
		while(solucion=false && !frontera.esVacia()) {
			nodoActual = frontera.eliminar();
			if(prob.esObjetivo(nodoActual)) {
				solucion = true;
			} else {
				sucesores.add(nodoActual);
				listaNodos = new ArrayList<Nodo>(sucesores, nodoActual, profundidad_maxima, op);
				frontera.insertar(listaNodos);
			}
		}
		
		return solucion;
	}
}