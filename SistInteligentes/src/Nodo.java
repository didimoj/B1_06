
public class Nodo {
	private int id;
	private Estado estado;
	private int costo;
	private Acciones accion;
	private int valor;
	private boolean visited;
	private Nodo parent;
	
	public Nodo(int i, Estado e, int c, Acciones a, int v, Nodo p) {
		id  = i;
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
	//Metodo equals para saber que nodo tiene mayor valor que otro (para la prioridad)
	//En la lista los nodos tienen que ordenarse de menor a mayor valor
}
