import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Agente {
	private Frontera f;

	public Agente() {
		f = new Frontera();

	}

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

	public void getSolucion(Estado estInicial) throws NoSuchAlgorithmException {
		Random r = new Random();
		Nodo nodo = new Nodo(getId(estInicial), estInicial, 1, r.nextInt(40000), null);
		f.insertar(nodo);
		while (!f.esVacia()) {
			nodo = f.eliminar();
			System.out.println("Elegimos el "+nodo);
			if (esObjetivo(nodo)) {
				Stack pila = new Stack();
				pila.add(nodo);
				System.out.println("CONSEGUIDO!!!!!!!!!!!!!!!!!!!!!!!");
				while (nodo.getParent() != null) {
					pila.add(nodo.getParent());
					nodo = nodo.getParent();
				}
			} else {
				ArrayList<Estado> l = nodo.getSucesores();
				
				for (int i = 0; i < l.size(); i++) {
					Nodo n = new Nodo(getId(l.get(i)), l.get(i), 1, r.nextInt(40000), nodo);
					f.insertar(n);
				}
			}

		}

	}

	private String getId(Estado estado) throws NoSuchAlgorithmException {
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
