package Practica1;

import Practica1.leer;

public class Tractor {
	private int[][] pos_tractor;
	
	public Tractor() {
		int pos_x, pos_y;
		
		do {
			pos_x = leer.entero("Introduzca la posición X donde se encuentra el tractor");
			if (pos_x < 0)
				System.out.println("Error en la posición X introducida. "
						+ "Introduzca un valor mayor o igual que 1: \n");
		} while (pos_x <= 0);
		
		do {
			pos_y = leer.entero("Introduzca la posición Y donde se encuentra el tractor");
			if (pos_y < 0)
				System.out.println("Error en la posici�n Y introducida. "
						+ "Introduzca un valor mayor o igual que 1: \n");
		} while (pos_y <= 0);
		
		pos_tractor = new int [pos_x][pos_y];
	}
}
