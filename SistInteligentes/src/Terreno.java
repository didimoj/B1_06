
public class Terreno {
	private int tractorX;
	private int tractorY;
	private int k;
	private int max;
	private int[][] terreno;

	public Terreno(int x, int y, int k, int m, int[][] terreno) {
		tractorX = x;
		tractorY = y;
		this.k = k;
		max = m;
		this.terreno = terreno;
	}

	public int tractorX() {
		return tractorX;
	}

	public int tractorY() {
		return tractorY;
	}

	public int K() {
		return k;
	}

	public int Max() {
		return max;
	}

	public int size() {
		return terreno[0].length;
	}
}
