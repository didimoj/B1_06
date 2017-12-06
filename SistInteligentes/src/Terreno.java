import java.util.Arrays;

/**
 * 
 * @author oscarjm97 didimojavier Alberto Gomez Leon
 *
 */
public class Terreno {
	private int k;
	private int max;
	private int[][] terreno;

	public Terreno(int k, int m, int[][] terreno) {
		this.k = k;
		max = m;
		this.terreno = terreno;
	}

	public int getCantidad(int x, int y) {
		return terreno[x][y];
	}

	public int K() {
		return k;
	}

	public int[][] getTerreno() {
		return terreno;
	}

	public int Max() {
		return max;
	}

	public int getFilas() {
		return terreno.length;
	}

	public int getColumnas() {
		return terreno[0].length;
	}

	@Override
	public String toString() {
		return "Terreno [terreno=" + Arrays.toString(terreno) + "]";
	}
}