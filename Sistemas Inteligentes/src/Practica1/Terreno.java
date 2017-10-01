package Practica1;

import Practica1.leer;

public class Terreno {
	private static int[][] terreno;
	
	public Terreno() {
		int n_filas, n_columnas, cantidad_maxima;
		
		do {
			n_filas = leer.entero("Introduzca el número de filas del terreno");
			if (n_filas <= 0)
				System.out.println("Error en el número de filas introducidas. "
						+ "Introduzca un valor mayor o igual que 1: \n");
		} while (n_filas <= 0);
		
		do {
			n_columnas = leer.entero("Introduzca el número de columnas del terreno");
			if (n_columnas <= 0)
				System.out.println("Error en el número de columnas introducidas. "
						+ "Introduzca un valor mayor o igual que 1: \n");
		} while (n_columnas <= 0);
		
		do {
			cantidad_maxima = leer.entero("Introduzca la cantidad máxima de arena para cada casilla");
			if (cantidad_maxima <= 0)
				System.out.println("Error en la cantidad introducida. "
						+ "Introduzca un valor mayor o igual que 1: \n");
		} while (cantidad_maxima <= 0);
		
		this.terreno = new int [n_filas][n_columnas];
	}
	
	public static int[][] getTerreno() {
		return terreno;
	}
	
}
