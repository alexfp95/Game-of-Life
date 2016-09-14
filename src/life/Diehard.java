package life;

import java.util.Random;

public class Diehard {
	
	final int FILAS = 80;
	final int COLUMNAS = 80;
	final int LIMITE_DIEHARD = 7;
	final int SEIS = 6;
	final int CINCO = 5;
	
	Celula[][] generacionActual;
	
	public Diehard (Celula[][] v) {
		generacionActual = v;
	}
	
	public void dibujar () {
		boolean repetir = true;
		while (repetir) {
			Random rand = new Random ();
			int coordX = rand.nextInt(FILAS);
			int coordY = rand.nextInt(COLUMNAS);
			
			while (((coordY - 1) < 0) || ((coordY + 1) > FILAS)) {
				coordY = rand.nextInt(FILAS);
			}
			
			while ((coordX + LIMITE_DIEHARD) > COLUMNAS) {
				coordX = rand.nextInt(COLUMNAS);
			}
			
			if(!getGeneracionActual()[coordX][coordY].getVida())
				if(!getGeneracionActual()[coordX + 1][coordY].getVida())
					if(!getGeneracionActual()[coordX + 1][coordY + 1].getVida())
						if(!getGeneracionActual()[coordX + SEIS][coordY - 1].getVida())
							if(!getGeneracionActual()[coordX + CINCO][coordY + 1].getVida())
								if(!getGeneracionActual()[coordX + SEIS][coordY + 1].getVida())
									if(!getGeneracionActual()[coordX + LIMITE_DIEHARD][coordY + 1].getVida()) {
										getGeneracionActual()[coordX][coordY].setVida(true);
										getGeneracionActual()[coordX + 1][coordY].setVida(true);
										getGeneracionActual()[coordX + 1][coordY + 1].setVida(true);
										getGeneracionActual()[coordX + SEIS][coordY - 1].setVida(true);
										getGeneracionActual()[coordX + CINCO][coordY + 1].setVida(true);
										getGeneracionActual()[coordX + SEIS][coordY + 1].setVida(true);
										getGeneracionActual()[coordX + LIMITE_DIEHARD][coordY + 1].setVida(true);
										repetir = false;
									}
		}
	}
	
	public Celula[][] getGeneracionActual() {
		return generacionActual;
	}

}
