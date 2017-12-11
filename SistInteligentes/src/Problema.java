import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Queue;

/**
 * 
 * @author oscarjm97 didimojavier Alberto Gomez Leon
 *
 */
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

	/**
	 * 
	 * @param est
	 * @return heuristica
	 */
	public int esObjetivo(Estado est) {
		int heuristica = 0;

		for (int i = 0; i < est.getTerreno().getTerreno().length; i++) {
			for (int j = 0; j < est.getTerreno().getTerreno()[0].length; j++) {
				if (est.getTerreno().getCantidad(i, j) != est.getTerreno().K())
					heuristica++;
			}
		}
		return heuristica;
	}

	/**
	 * 
	 * @param estrategia
	 * @param prof_max
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Queue<Nodo> getSolucion(String estrategia, int prof_max) throws NoSuchAlgorithmException {
		Operaciones operacion = new Operaciones();
		if (estrategia.equals("DFS"))
			return operacion.busquedaIterativa(this, estrategia, prof_max, 10);
		else
			return operacion.busquedaAcotada(this, estrategia, prof_max);

	}

	/**
	 * 
	 * @param estado
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
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
