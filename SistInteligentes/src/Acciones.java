
import java.util.ArrayList;
import java.util.Arrays;

public class Acciones {
	private int mov[];
	private int dist[];
	private ArrayList<int[]> casillas;
	public Acciones(int[] m, int[] d,ArrayList<int[]> l) {
		mov=m;
		
		dist=d;
		casillas=l;
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
	public int getCosto() {
		int costo=0;
		for(int i=0;i<dist.length;i++)
			costo+=dist[i];
		return costo+1;
	}
	@Override
	public String toString() {
		String s="";
		for(int i=0;i<casillas.size();i++)
			s+=dist[i]+",("+Arrays.toString(casillas.get(i))+") ";
		return "Acciones [Movimiento=" + Arrays.toString(mov) + ", Distribucion= [ " + s + "] , Coste: "+getCosto();
	}
}