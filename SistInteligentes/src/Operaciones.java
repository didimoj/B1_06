import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Operaciones {

	public ArrayList<Integer> busquedaAnchura(Nodo nodo) {
		ArrayList<Integer> recorridos = new ArrayList<Integer>(); 				// Lista donde guardo los nodos recorridos
		nodo.setVisited(true);													// El nodo inicial ya está visitado
		ArrayList<Integer> cola = new ArrayList<Integer>(); 					// Cola de visitas de los nodos adyacentes

		recorridos.add(Integer.parseInt(nodo.getId()));							// Se lista el nodo como ya recorrido
		cola.add(Integer.parseInt(nodo.getId())); 								// Se agrega el nodo a la cola de visitas

		while (!cola.isEmpty()) { 									// Hasta que visite todos los nodos
			int j = cola.remove(0); 								// Se saca el primero nodo de la cola
			
			for (int i = 0; i < g.length; i++) { 					// Se busca en la matriz que representa el grafo los nodos adyacentes
				if (g[j][i] == 1 && !visitiadoAnchura[i]) { 		// Si es un nodo adyacente y no está visitado entonces
					cola.add(i);									// Se agrega a la cola de visitas
					recorridos.add(i);								// Se marca como recorrido
					visitiadoAnchura[i] = true;						// Se marca como visitado
				}
			}
		}
		return recorridos;					// Devuelvo el recorrido del grafo en anchura
	}

	public ArrayList<Integer> busquedaProfunidad(Nodo nodo) {
		ArrayList<Integer> recorridos = new ArrayList<Integer>();
		nodo.setVisited(true);													
		ArrayList<Integer> cola = new ArrayList<Integer>();

		recorridos.add(Integer.parseInt(nodo.getId()));
		cola.add(Integer.parseInt(nodo.getId())); 														
		
		while (!cola.isEmpty()) {
			int j = cola.remove(0);
			
			for (int i = 0; i < g.length; i++) {
				if (g[j][i] == 1 && !visitiadoProfunidad[i]) {
					cola.add(i);
					recorridos.addAll(recorridoProfunidad(i));		// Se recorren los hijos del nodo actual de visita y se agrega el recorrido a la lista
					visitiadoProfunidad[i] = true;
				}
			}
		}
		return recorridos;// Se devuelve el recorrido del grafo en profundidad
	}
	 

}
