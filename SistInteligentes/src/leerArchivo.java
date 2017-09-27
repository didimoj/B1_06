import java.io.File;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;



public class leerArchivo {
	public String cargarArchivo(String file) {
		String s="";
		try {
			FileReader fr = new FileReader(new File(file));
			Scanner pantalla = new Scanner(fr);
			System.out.println(pantalla.next());
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return s;
	}
}
