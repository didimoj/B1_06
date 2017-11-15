import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Estado {
	private Terreno t;
	private int tractorX;
	private int tractorY;

	public Estado(Terreno t, int tractorx, int tractory) {
		this.t = t;
		this.tractorX = tractorx;
		this.tractorY = tractory;
	}

	public Terreno getTerreno() {
		return t;
	}
	/*
	public int getCosto(Acciones a) {
		int costo = 0;
		for (int i = 0; i < a.getDist().length; i++)
			costo += a.getDist()[i];
		return costo + 1;
	}
*/
	@Override
	public String toString() {
		return imprimir(t.getTerreno()) + "\n(" + getTractorX() + ", " + getTractorY() + ")";
	}

	public String imprimir(int[][] solar) {
		String s = "";
		for (int i = 0; i < solar.length; i++) {
			for (int j = 0; j < solar.length; j++) {
				// System.out.print(solar[i][j] + "\t");
				s += solar[i][j] + " ";
			}
			s += "\n";
			// System.out.println();
		}
		return s;
	}

	public int getTractorX() {
		return tractorX;
	}

	public void setTractorX(int tractorX) {
		this.tractorX = tractorX;
	}

	public int getTractorY() {
		return tractorY;
	}

	public void setTractorY(int tractorY) {
		this.tractorY = tractorY;
	}
}