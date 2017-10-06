import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Estado {
	private static Terreno t;
	private static int tractorX;
	private static int tractorY;
	public  Estado(Terreno t, int tractorx, int tractory) {
		this.t=t;
		tractorX=tractorx;
		tractorY=tractory;
		distribucion();
	}
	public void distribucion() {
		ArrayList l=crearLista();
		ArrayList todas=new ArrayList<>();
		posibilidades(l,0,(t.getCantidad(tractorX, tractorY)-t.K()),todas);
	}
	public static ArrayList<int[]> crearLista() {

		ArrayList<int[]> lista = new ArrayList<int[]>();

		if (tractorX == 0 && tractorY == 0) {						// Esquina superior izquierda
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY + 1) });
		
		} else if (tractorX == 0 && tractorY == t.size()) {			// Esquina inferior izquierda
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY - 1) });
			
		} else if (tractorX == t.size() && tractorY == 0) {			// Esquina superior derecha
			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY + 1) });
		
		} else if (tractorX == t.size() && tractorY == t.size()) {	// Esquina inferior derecha
			
			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY - 1) });
		}
		// es decir si el tractor esta en una esquina del terreno
		// si esta en una esquina solo habra dos sitios donde colocarlo
		// hacer como se puede cojer y hacer las combinaciones de la cantidad menos k
		// entre dos casillas y meterlas en una lista

		else if (tractorX == 0 && tractorY != 0 && tractorY != t.size()) {			// Lateral izquierdo
			lista.add(new int[] { tractorX, (tractorY - 1) });
			lista.add(new int[] { tractorX, (tractorY + 1) });
			lista.add(new int[] { (tractorX + 1), tractorY });
			System.out.println(tractorX+" "+tractorY+" Aqui"+t.getCantidad(tractorX, tractorY)+" "+t.size());
			// es decir, si el tractor esta en el borde izquierdo pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones
		} else if (tractorX == t.size() && tractorY != 0 && tractorY != t.size()) {	// Lateral derecho
			lista.add(new int[] { tractorX, (tractorY - 1) });
			lista.add(new int[] { tractorX, (tractorY + 1) });
			lista.add(new int[] { (tractorX - 1), tractorY });
			// es decir, si el tractor esta en el borde derecho pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones

			// lo diferencio para que sea automatico y mas facil de cojer luego
		} else if (tractorY == 0 && tractorX != 0 && tractorX != t.size()) {		// Arriba
			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY + 1) });
			// es decir, si el tractor esta en el borde de arriba pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones
		} else if (tractorY == t.size() && tractorX != 0 && tractorX != t.size()) {	// Abajo
			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY - 1) });
			// es decir, si el tractor esta en el borde de abajo pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones
		} else {
			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY - 1) });
			lista.add(new int[] { tractorX, (tractorY + 1) });
			// esta en mitad y tendra 4 posibilidades para distribuir
		}
		return lista;
	}
	public void posibilidades(ArrayList<int[]> L, int in,int cantidad,ArrayList todas) {
		
		for(int i=cantidad;i>=0;i--) {
			
			Distribucion d=new Distribucion(i, L.get(in));
			todas.add(d);
			System.out.println(d.toString());
			if(in<L.size()-1)
				posibilidades(L,in+1,cantidad-i,todas);
			else
				i=0;
				
		}
		
		//for(int i=0;i<todas.size();i++)
		//	System.out.println(todas.get(i));
		
	}
}
