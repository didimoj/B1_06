
public class Sucesor {
	private String nombre;
	private Acciones accion;
	private Estado estado;
	
	public Sucesor(String nom, Acciones acc, Estado es) {
		nombre=nom;
		accion=acc;
		estado=es;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Acciones getAccion() {
		return accion;
	}

	public void setAccion(Acciones accion) {
		this.accion = accion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
