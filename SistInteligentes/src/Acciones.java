import java.util.Arrays;

public class Acciones {
	private int mov[];
	private int dist[];
	public Acciones(int[] m, int[] d) {
		mov=m;
		
		dist=d;
	}
	
	public int[] getMov() {
		return mov;
	}

	public void setMov(int[] mov) {
		this.mov = mov;
	}

	public int[] getDist() {
		return dist;
	}

	public void setDist(int[] dist) {
		this.dist = dist;
	}

	@Override
	public String toString() {
		return "Acciones [Movimiento=" + Arrays.toString(mov) + ", Distribucion=" + Arrays.toString(dist) + "], Coste: 1";
	}
}
