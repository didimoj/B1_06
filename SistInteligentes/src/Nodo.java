import java.util.ArrayList;

public class Nodo implements Comparable<Nodo>{
	private String id;
	private Estado estado;
	private int costo;
	private int valor;
	private boolean visited;
	private Nodo parent;

	public Nodo(String i, Estado e, int c, int v, Nodo p) {
		id = i;
		estado = e;
		costo = c;
		valor = v;
		visited = false;
		parent = p;
	}
	
	public String getId() {
		return id;
	}

	public Estado getEstado() {
		return estado;
	}

	public int getCosto() {
		return costo;
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
	


	@Override
	public String toString() {
		return "Nodo [id=" + id + ", estado=\n" + estado + "]";
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
			resultado = 0;
		}
		return resultado;
	}

	public ArrayList<Estado> getSucesores() {
		GestionEstado g=new GestionEstado();
		return g.distribucion(estado);
	}
}
