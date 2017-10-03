
public class agente {
	public static void combinaciones(Terreno t) {
		if(t.tractorX() == 0 && t.tractorY()==0|| t.tractorX() == 0 && t.tractorY()== t.size() || t.tractorX() == t.size() && t.tractorY()==0|| t.tractorX() == t.size() && t.tractorY()== t.size()) {
			//es decir si el tractor esta en una esquina del terreno
			//si esta en una esquina solo habra dos sitios donde colocarlo
			//hacer como se puede cojer y hacer las combinaciones de la cantidad menos k entre dos casillas y meterlas en una lista
		}
		else if(t.tractorX()==0 && t.tractorY()!=0 && t.tractorY() != t.size()) {
			//es decir, si el tractor esta en el borde izquierdo pero no en una esquina
			//de esta forma tendria tres sitios donde colocar tierra
			//hacer que se pueda cojer y las combinaciones
		}
		else if(t.tractorX()==t.size() && t.tractorY()!=0 && t.tractorY() != t.size()) {
			//es decir, si el tractor esta en el borde derecho pero no en una esquina
			//de esta forma tendria tres sitios donde colocar tierra
			//hacer que se pueda cojer y las combinaciones
			
			//lo diferencio para que sea automatico y mas facil de cojer luego
		}else if(t.tractorY()==0 && t.tractorX()!=0 && t.tractorX() != t.size()) {
			//es decir, si el tractor esta en el borde de arriba pero no en una esquina
			//de esta forma tendria tres sitios donde colocar tierra
			//hacer que se pueda cojer y las combinaciones
		}else if(t.tractorY()==t.size() && t.tractorX()!=0 && t.tractorX() != t.size()) {
			//es decir, si el tractor esta en el borde de arriba pero no en una esquina
			//de esta forma tendria tres sitios donde colocar tierra
			//hacer que se pueda cojer y las combinaciones
		}else {
			//esta en mitad y tendra 4 posibilidades para distribuir
		}
		
	}
}
