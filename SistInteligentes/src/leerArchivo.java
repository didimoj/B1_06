import java.io.File;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;



public class leerArchivo {
	public String cargarArchivo(String file) {
		String s="";
		int x, y, k, M, c, f;
		try {
			FileReader fr = new FileReader(new File(file));
			Scanner pantalla = new Scanner(fr);
			x=pantalla.nextInt();
			y=pantalla.nextInt();
			k=pantalla.nextInt();
			M=pantalla.nextInt();
			c=pantalla.nextInt();
			f=pantalla.nextInt();
			
			int[][] terreno=new int[c][f];
			for(int i=0;i<f;i++) {
				for(int j=0;j<c;j++) {
					terreno[i][j]=pantalla.nextInt();
				}
			}
			imprimir(terreno);
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return s;
	}
	public static void imprimir(int[][] solar){
		for(int i=0;i<solar.length;i++){
			for(int j=0;j<solar.length;j++){
				System.out.print(solar[i][j]+"\t");
			}
			System.out.println();
		}
	}
}
