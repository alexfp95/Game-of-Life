/*
 * Asignatura: Programación de Aplicaciones Interactivas (PAI)
 * Curso: 2016
 * Autor: Alexis Daniel Fuentes Pérez
 * Contacto: alu0100816761@ull.edu.es
 * 
 * Clase Célula. Representa una célula.
 */

package life;

/**
 * Clase Celula. Representa una célula.
 * @author Alexis Daniel Fuentes Pérez
 */

public class Celula {
	
	private int coordX;
	private int coordY;
	private boolean vida;
	
	public Celula (int x, int y) {
		coordX = x;
		coordY = y;
		vida = false;
	}
		
	public Celula (int x, int y, boolean v) {
		coordX = x;
		coordY = y;
		vida = v;
	}
	
	/**
	 * Getters y setters
	 */
	
	public boolean getVida() {
		return vida;
	}
	
	public void setVida (boolean estado) {
		vida = estado;
	}
	
	public int getCoordX () {
		return coordX;
	}
	
	public int getCoordY () {
		return coordY;
	}
}
