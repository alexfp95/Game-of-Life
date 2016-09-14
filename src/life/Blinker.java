package life;

import java.util.Random;

public class Blinker {
	
	final int FILAS = 80;
	final int COLUMNAS = 80;
	
	Celula[][] generacionActual;
	
	public Blinker (Celula[][] v) {
		generacionActual = v;
	}
	
	public void dibujar () {
		boolean repetir = true;
		
		while(repetir) {
			Random rand = new Random ();
			int coordX = rand.nextInt(FILAS);
			int coordY = rand.nextInt(COLUMNAS);
			
			while (((coordX - 1) < 0) || ((coordX + 1) > FILAS)) {
				coordX = rand.nextInt(FILAS);
			}
			
			while (((coordY - 1) < 0) || ((coordY + 1) > COLUMNAS)) {
				coordY = rand.nextInt(COLUMNAS);
			}
			
			if(!getGeneracionActual()[coordX][coordY].getVida()) 
				if(!getGeneracionActual()[coordX ][coordY - 1].getVida())
					if(!getGeneracionActual()[coordX][coordY + 1].getVida()) {
						getGeneracionActual()[coordX][coordY].setVida(true);
						getGeneracionActual()[coordX ][coordY - 1].setVida(true);
						getGeneracionActual()[coordX][coordY + 1].setVida(true);
						repetir = false;
					} 
		}
	}
	
	public Celula[][] getGeneracionActual() {
		return generacionActual;
	}

}
