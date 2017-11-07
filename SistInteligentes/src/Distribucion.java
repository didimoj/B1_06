import java.util.Arrays;

public class Distribucion {
	private int[] posicion;
	private int cantidad;

	public Distribucion(int c, int[] p) {
		cantidad = c;
		posicion = p;
	}

	public int[] getPosicion() {
		return posicion;
	}

	public void setPosicion(int[] posicion) {
		this.posicion = posicion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Distribucion [posicion=" + Arrays.toString(posicion) + ", cantidad=" + cantidad + "]";
	}

}
