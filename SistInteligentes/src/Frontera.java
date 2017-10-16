import java.util.LinkedList;

public class Frontera {
	
	public LinkedList<Nodo> creaFrontera() {
		LinkedList<Nodo> lista = new LinkedList<Nodo>();
		//Criterio de ordenacion
		return lista;
	}
	public void insertar(LinkedList<Nodo> lista, Nodo nodo) {
		lista.add(nodo);
	}
	public Nodo eliminar(LinkedList<Nodo> lista) {
		Nodo nodo = lista.removeFirst();
		return nodo;
	}
	public boolean esVacia(LinkedList<Nodo> lista) {
		return lista.isEmpty();
	}
}
