
/**
 * 
 * @author oscarjm97 didimojavier Alberto Gomez Leon
 *
 */
public class Main {

	public static void main(String[] args) {
		
		Menu menu = new Menu();

		try {

			menu.menu(leer.cadena("Introduce el nombre del archivo donde se encuentra el terreno"));
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
