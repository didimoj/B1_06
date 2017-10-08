import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Estado {
	private static Terreno t;
	private static int tractorX;
	private static int tractorY;

	public Estado(Terreno t, int tractorx, int tractory) {
		this.t = t;
		tractorX = tractorx;
		tractorY = tractory;
		distribucion();
	}

	public void distribucion() {
		ArrayList<int[]> l = crearLista();
		ArrayList todas = new ArrayList<>();
		ArrayList<int[]> todas1 = new ArrayList<>();
		//todas = posibilidades(l, 0, (t.getCantidad(tractorX, tractorY) - t.K()), todas);
		// for(int i=0;i<todas.size();i++)
		// System.out.println(todas.get(i));
		System.out.println(todas.size() + " primera");
		todas1 = back(new int[l.size()],0,todas1,  (t.getCantidad(tractorX, tractorY) - t.K()), l.size());
		
		DistPos(l, todas1);
		// for(int i=0;i<todas1.size();i++)
		//	 System.out.println(Arrays.toString(todas1.get(i)));
		System.out.println(todas1.size() + " segunda");
	}
	
	public void DistPos(ArrayList<int[]> l, ArrayList<int[]> todas1) {
		System.out.println("------ DISTRIBUCCIONES POSIBLES -------");
		int index = 0;
		do {
			int[] disp = todas1.get(index);
			for (int i = 0; i < l.size(); i++) {
				int[] pos = l.get(i);
				System.out.println(disp[i]+" a "+Arrays.toString(pos));
			}
			System.out.println();
			index++;
		}while(index < todas1.size());
	}

	public static ArrayList<int[]> crearLista() {

		ArrayList<int[]> lista = new ArrayList<int[]>();

		if (tractorX == 0 && tractorY == 0) { // Esquina superior izquierda
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY + 1) });

		} else if (tractorX == 0 && tractorY == t.size()) { // Esquina inferior izquierda
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY - 1) });

		} else if (tractorX == t.size() && tractorY == 0) { // Esquina superior derecha
			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY + 1) });

		} else if (tractorX == t.size() && tractorY == t.size()) { // Esquina inferior derecha

			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY - 1) });
		}
		// es decir si el tractor esta en una esquina del terreno
		// si esta en una esquina solo habra dos sitios donde colocarlo
		// hacer como se puede cojer y hacer las combinaciones de la cantidad menos k
		// entre dos casillas y meterlas en una lista

		else if (tractorX == 0 && tractorY != 0 && tractorY != t.size()) { // Lateral izquierdo
			lista.add(new int[] { tractorX, (tractorY - 1) });
			lista.add(new int[] { tractorX, (tractorY + 1) });
			lista.add(new int[] { (tractorX + 1), tractorY });
			System.out.println(tractorX + " " + tractorY + " Aqui" + t.getCantidad(tractorX, tractorY));
			// es decir, si el tractor esta en el borde izquierdo pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones
		} else if (tractorX == t.size() && tractorY != 0 && tractorY != t.size()) { // Lateral derecho
			lista.add(new int[] { tractorX, (tractorY - 1) });
			lista.add(new int[] { tractorX, (tractorY + 1) });
			lista.add(new int[] { (tractorX - 1), tractorY });
			// es decir, si el tractor esta en el borde derecho pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones

			// lo diferencio para que sea automatico y mas facil de cojer luego
		} else if (tractorY == 0 && tractorX != 0 && tractorX != t.size()) { // Arriba
			lista.add(new int[] { (tractorX - 1), tractorY });
			lista.add(new int[] { (tractorX + 1), tractorY });
			lista.add(new int[] { tractorX, (tractorY + 1) });
			// es decir, si el tractor esta en el borde de arriba pero no en una esquina
			// de esta forma tendria tres sitios donde colocar tierra
			// hacer que se pueda cojer y las combinaciones
		} else if (tractorY == t.size() && tractorX != 0 && tractorX != t.size()) { // Abajo
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

	private ArrayList<int[]> back(int[] actual, int etapa, ArrayList<int[]> sol, int max,int ncasillas) {
		int[] copia = new int[actual.length];
		if (etapa == ncasillas) {
			if(suma(actual,ncasillas,max)) {
				System.arraycopy(actual, 0, copia, 0, actual.length);
				sol.add(copia);
				System.out.println(Arrays.toString(actual));
			}
				
			
		} else {
			for (int cantidad = 0; cantidad <= max; cantidad++) {
				//if (vale(actual, etapa, cantidad)) {// no sobrepasa los millones que tengo
					actual[etapa] = cantidad;
					back(actual, etapa + 1, sol,max,ncasillas);
				//}
			}
		}
		return sol;
	}

	private boolean suma(int[] actual, int ncasillas, int max) {
		int suma=0;
		for(int i=0;i<ncasillas;i++) suma+=actual[i];
		return suma==max;
	}

	private boolean vale(int[] actual, int casillas, int cantidad) {
		//int hay=0;
		//for(int n=0;n<casillas;n++) hay=hay+actual[n];
		
		return actual[casillas]<=cantidad;
		
	}

	public ArrayList<Distribucion> posibilidades(ArrayList<int[]> L, int in, int cantidad, ArrayList todas) {
		Distribucion d;
		for (int i = 0; i <= cantidad; i++) {
			if (in != L.size() - 1) {
				d = new Distribucion(i, L.get(in));
				todas.add(d);
				posibilidades(L, in + 1, cantidad - i, todas);
			} else {
				d = new Distribucion(cantidad, L.get(in));
				todas.add(d);
				return null;
				// posibilidades(L,0,cantidad-i,todas);

			}

			// System.out.println(d.toString());

			/*
			 * if(in<L.size()-1) { posibilidades(L,in+1,cantidad-i,todas); }else in=0;
			 */
			// System.out.println();
		}

		return todas;
	}

	public ArrayList<ArrayList<Distribucion>> posibilidades1(ArrayList<int[]> L, int in, int cantidad,
			ArrayList<ArrayList<Distribucion>> todas, int count) {
		for (int i = 0; i <= cantidad; i++) {
			Distribucion d;

			if (in < L.size() - 1) {
				d = new Distribucion(i, L.get(in));
				System.out.println(d);
				todas.get(count).add(d);
				posibilidades1(L, in + 1, cantidad - i, todas, count);
			} else {
				d = new Distribucion(cantidad, L.get(in));
				System.out.println(d);
				todas.get(count).add(d);

				return null;

			}
			count++;

		}

		return todas;
	}
}
