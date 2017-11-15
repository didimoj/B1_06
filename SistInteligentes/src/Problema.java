import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Problema {
	private EspacioEstados e;
	private Estado estInicial;

	public Problema(Estado e_ini) {
		e = new EspacioEstados();
		estInicial = e_ini;

	}

	public EspacioEstados getE() {
		return e;
	}

	public Estado getEstInicial() {
		return estInicial;
	}
/*
	public boolean esObjetivo(Nodo n) {
		boolean flag = true;
		Estado e = n.getEstado();
		for (int i = 0; i < e.getTerreno().size() + 1 && flag; i++) {
			for (int j = 0; j < e.getTerreno().size() + 1 && flag; j++) {
				flag = (e.getTerreno().getCantidad(i, j) == e.getTerreno().K());
			}
		}
		return flag;
	}
	*/
	public int esObjetivo(Estado e) {
		int heuristica = 0;
		
		for (int i = 0; i < e.getTerreno().size() + 1; i++) {
			for (int j = 0; j < e.getTerreno().size() + 1; j++) {
				if (e.getTerreno().getCantidad(i, j) != e.getTerreno().K()) heuristica++;
			}
		}
		return heuristica;
	}

	/*
	 * public void getSolucion() throws NoSuchAlgorithmException { Frontera f = new
	 * Frontera(); Random r = new Random(); Nodo nodo = new Nodo(getId(estInicial),
	 * estInicial, 0, r.nextInt(40000), 1, null, null); f.insertar(nodo); while
	 * (!f.esVacia()) { nodo = f.eliminar(); System.out.println("Elegimos el " +
	 * nodo); if (esObjetivo(nodo)) { Stack pila = new Stack(); pila.add(nodo);
	 * System.out.println("CONSEGUIDO!!!!!!!!!!!!!!!!!!!!!!!"); while
	 * (nodo.getParent() != null) { pila.add(nodo.getParent()); nodo =
	 * nodo.getParent(); } } else { ArrayList<Sucesor> l =
	 * e.getSucesores(nodo.getEstado());
	 * 
	 * for (int i = 0; i < l.size(); i++) {
	 * System.out.println(l.get(i).getAccion()); // El 1 es el valor Nodo n = new
	 * Nodo(getId(l.get(i).getEstado()), l.get(i).getEstado(), nodo.getProf() + 1,
	 * l.get(i).getAccion().getCosto(), 1, nodo, l.get(i).getAccion());
	 * System.out.println(n); f.insertar(n); } }
	 * 
	 * }
	 * 
	 * }
	 */

	// llamar al tipo de búsqueda
	public Queue getSolucion(String estrategia, int prof_max) throws NoSuchAlgorithmException {
		Operaciones operacion = new Operaciones();
		if (estrategia.equals("DFS")) 
			return operacion.busquedaIterativa(this, estrategia, prof_max, 10);
		else
			return operacion.busquedaAcotada(this, estrategia, prof_max);	
		
	}

	public String getId(Estado estado) throws NoSuchAlgorithmException {
		String clear = estado.toString();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = md.digest(clear.getBytes());

		int size = b.length;
		StringBuffer h = new StringBuffer(size);
		for (int i = 0; i < size; i++) {
			int u = b[i] & 255;
			if (u < 16) {
				h.append("0" + Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
			}
		}
		return h.toString();

	}
}
