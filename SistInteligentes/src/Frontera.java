import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

import java.util.*;
public class Frontera {
	

	PriorityQueue<Nodo> lista;

	public Frontera() {
		lista = new PriorityQueue<Nodo>(100);
	}

	/*
	 * public LinkedList<Nodo> creaFrontera() { lista = new LinkedList<Nodo>();
	 * //Criterio de ordenacion return lista; }
	 */
	public void insertar(Nodo nodo) {
		lista.add(nodo);
	}

	public Nodo eliminar() {
		return lista.remove();

	}

	public boolean esVacia() {
		return lista.isEmpty();
	}
	@Override
	public String toString() {
		String s="";
		Iterator<Nodo> it=lista.iterator();
		while(it.hasNext())
			s+=" "+it.next().getValor();
		return "Frontera = ["+s+"]";
	}
	
}
