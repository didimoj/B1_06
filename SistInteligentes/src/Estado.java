/**
 * 
 * @author oscarjm97 didimojavier Alberto Gomez Leon
 *
 */
public class Estado {
	private Terreno terreno;
	private int tractorX;
	private int tractorY;

	public Estado(Terreno t, int tractorx, int tractory) {
		terreno = t;
		this.tractorX = tractorx;
		this.tractorY = tractory;
	}

	public Terreno getTerreno() {
		return terreno;
	}

	@Override
	public String toString() {
		return "(" + getTractorX() + ", " + getTractorY() + ")\n" + imprimir(terreno.getTerreno());
	}
/**
 * 
 * @param solar
 * @return s
 */
	public String imprimir(int[][] solar) {
		String s = "\t";
		for (int i = 0; i < solar.length; i++) {
			for (int j = 0; j <  solar[0].length; j++) {
				s += solar[i][j] + " ";
			}
			s += "\n\t";
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