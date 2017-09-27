import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
			escribirArchivo("terreno.txt",0,3,3,11,3,3,terreno);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return s;
	}
	public void escribirArchivo(String path,int x,int y,int k, int M,int c,int f,int[][] terreno) {
		File file=new File(path);
		try {
			FileWriter fw=new FileWriter(file,false);
			BufferedWriter bw = new BufferedWriter(fw); 
			//bw.newLine();
			//bw.write("------------------");
			//bw.newLine();
			bw.write(x+" "+y+" "+k+" "+M+" "+c+" "+f+"\n ");
			bw.newLine();
			for(int i=0;i<f;i++) {
				for(int j=0;j<c;j++) {
					bw.write(" "+terreno[i][j]);
				}
				bw.newLine();
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
