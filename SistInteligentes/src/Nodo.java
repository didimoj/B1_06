
public class Nodo {
	private int id;
	private Estado estado;
	private int costo;
	private Acciones accion;
	private int valor;
	private boolean visited;
	private Nodo parent;

	public Nodo(int i, Estado e, int c, Acciones a, int v, Nodo p) {
		id = i;
		estado = e;
		costo = c;
		accion = a;
		valor = v;
		visited = false;
		parent = p;
	}

	public int getId() {
		return id;
	}

	public Estado getEstado() {
		return estado;
	}

	public int getCosto() {
		return costo;
	}

	public Acciones getAccion() {
		return accion;
	}

	public int getValor() {
		return valor;
	}

	public boolean getVisited() {
		return visited;
	}

	public Nodo getParent() {
		return parent;
	}

	public void setCosto(int c) {
		costo = c;
	}

	public void setValor(int v) {
		valor = v;
	}

	public void setVisited(boolean v) {
		visited = v;
	}

	public boolean equals(Object x) {
		if (x instanceof Nodo && id == ((Nodo) x).getId());
		return true;
	}

	public int compareTo(Nodo o) {
		int resultado = 0;
		if (this.valor < o.valor)
			resultado = -1;
		else if (this.valor > o.valor)
			resultado = 1;
		else {
			if (this.id < o.id)
				resultado = -1;
			else if (this.id > o.id)
				resultado = 1;
			else
				resultado = 0;
		}
		return resultado;
	}
}
