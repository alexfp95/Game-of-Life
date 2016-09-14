/*
 * Asignatura: Programación de Aplicaciones Interactivas (PAI)
 * Curso: 2016
 * Autor: Alexis Daniel Fuentes Pérez
 * Contacto: alu0100816761@ull.edu.es
 * 
 * Clase Vida. Panel en el que se representa el juego de la vida.
 */

package life;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

/**
 * Clase Vida
 * @author Alexis Daniel Fuentes Pérez
 */

public class Vida extends JPanel {
	
	final int TAM_CELDA = 5;                                 // Tamaño de cada celula
	final int FILAS = 80;
	final int COLUMNAS = 80;
	final int LIMITE_DESPOBLACION = 2;                       // Limite de las reglas de evolucion
	final int LIMITE_SUPERPOBLACION = 3;                     // Limite de las reglas de evolucion
	final int LIMITE_REPRODUCCION = 3;                       // Limite de las reglas de evolucion
	final Color colorCelulas = new Color (18, 74, 178);      // Color de las celulas
	
	private Celula[][] generacionActual;
	private int filas;
	private int columnas;
	private boolean inicializado;                            // Ha sido inicializado o no la matriz del juego
	private boolean ejecucion;                               // Ha sido ejecutado o no el juego
	private boolean pausa;                                   // Determina si el juego esta pausado
	private boolean paso;                                    // Determina si se esta ejecutando paso a paso
	
	public Vida () {
		filas = FILAS;
		columnas = COLUMNAS;
		generacionActual = new Celula [filas][columnas];
		inicializado = false;
		ejecucion = false;
		pausa = false;
		paso = false;
	}
	
	/**
	 * Redimensiona la matriz del juego y la deja preparada
	 */
	
	public void redimensionaTablero () {
		 setGeneracionActual(new Celula [FILAS][COLUMNAS]);
		 inicializaCeldas();
		 setInicializado(true);
	}
	
	/**
	 * Inicializa las celulas del juego
	 */
	
	public void inicializaCeldas () {
		Celula[][] t = getGeneracionActual();
		for(int i = 0; i < FILAS; i++) {
			for(int j = 0; j < COLUMNAS; j++) {
				t[i][j] = new Celula (i, j);
			}
		}
	}
	
	/**
	 * Obtiene la siguiente generacion aplicando las reglas a la generacion actual
	 */
	
	public void siguienteGeneracion () {
		Celula[][] nuevaGeneracion = new Celula[FILAS][COLUMNAS];
		
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				int vecinas = vecinasVivas(getGeneracionActual()[i][j]);
				if (getGeneracionActual()[i][j].getVida()) {
					if (reglaDespoblacion (vecinas)) {
						nuevaGeneracion[i][j] = new Celula (i, j, false);
					}  if (reglaVivir (vecinas)) {
						nuevaGeneracion[i][j] = new Celula (i, j, true);
					}  if (reglaSuperpoblacion (vecinas)) {
						nuevaGeneracion[i][j] = new Celula (i, j, false);
					}
				} else {
					if (reglaReproduccion (vecinas)) {
						nuevaGeneracion[i][j] = new Celula (i, j, true);
					} else {
						nuevaGeneracion[i][j] = new Celula (i, j, false);
					}
				}
			}
		}
		setGeneracionActual(nuevaGeneracion);
	}
	
	/**
	 * Regla que determina si una celula muere por despoblacion
	 * @param vecinas
	 * @return true - false
	 */
	
	public boolean reglaDespoblacion (int vecinas) {
		if (vecinas < LIMITE_DESPOBLACION) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Regla que determina si una celula sigue viva
	 * @param vecinas
	 * @return ture - false
	 */
	
	public boolean reglaVivir (int vecinas) {
		if ((vecinas >= LIMITE_DESPOBLACION) && (vecinas <= LIMITE_SUPERPOBLACION)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Regla que determina si una celula muere por despoblacion
	 * @param vecinas
	 * @return true - false
	 */
	
	public boolean reglaSuperpoblacion (int vecinas) {
		if (vecinas > LIMITE_SUPERPOBLACION) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Regla que determina si una celula muerta vuelve a la vida por reproduccion
	 * @param vecinas
	 * @return ture - false
	 */
	
	public boolean reglaReproduccion (int vecinas) {
		if(vecinas == LIMITE_REPRODUCCION) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Devuelve el numero de vecinas vivas que tiene una celula dada
	 * @param c
	 * @return true - false
	 */
	
	public int vecinasVivas (Celula c) {
		int contador = 0;
		int x = c.getCoordX();
		int y = c.getCoordY();
		
		if (x > 0) {
			if (getGeneracionActual()[x - 1][y].getVida() == true)
				contador++;
		}
		if ((x > 0) && (y < (COLUMNAS - 1))) {
			if(getGeneracionActual()[x - 1][y + 1].getVida() == true)
				contador++;
		}
		if (y < (COLUMNAS - 1)) {
			if(getGeneracionActual()[x][y + 1].getVida() == true)
				contador++;
		}
		if ((x < (FILAS - 1)) && (y < (COLUMNAS - 1))) {
			if(getGeneracionActual()[x + 1][y + 1].getVida() == true)
				contador++;
		}
		if (x < (FILAS - 1)) {
			if(getGeneracionActual()[x + 1][y].getVida() == true)
				contador++;
		}
		if ((x < (FILAS - 1)) && (y > 0)) {
			if(getGeneracionActual()[x + 1][y - 1].getVida() == true)
				contador++;
		}
		if (y > 0) {
			if(getGeneracionActual()[x][y - 1].getVida() == true)
				contador++;
		}
		if((x > 0) && (y > 0)) {
			if(getGeneracionActual()[x - 1][y - 1].getVida() == true)
				contador++;
		}
		
		return contador;
	}
	
	/**
	 * Sobreescribe paintComponent para dibujar las celulas vivas y muertas del juego
	 */
	
	protected void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		
		int coordX = 0;
    int coordY = 0;
    
    int k = 1;
    while(coordX <= getHeight()) {
      g.drawLine(0, coordX, getWidth(), coordX);
      coordX = getProporcionAlto (k);
      k++;
    }
    
    k = 1;
    while(coordY <= getWidth()) {
      g.drawLine(coordY, 0, coordY, getHeight());
      coordY = getProporcionAncho (k);
      k++;
    }
		
		g.setColor(colorCelulas);
		if(getInicializado()) {
			for(int i = 0; i < FILAS; i++) {
				for(int j = 0; j < COLUMNAS; j++) {
					Celula c = getGeneracionActual()[i][j];
					if(getGeneracionActual()[i][j].getVida())
						g.fillRect(getProporcionAncho(i), getProporcionAlto(j), (getWidth() / FILAS), (getHeight() / COLUMNAS));
				}
			}
		}
	}
		
	/**
	 * Getters y setters
	 */
	
	public int getProporcionAlto (int i) {
    return ((getHeight() * i)/ COLUMNAS);
  }
	
	public int getProporcionAncho (int i) {
    return ((getWidth() * i) / FILAS);
  }
		
	public void setFilas (int n) {
		filas = n;
	}
		
	public void setColumnas (int n) {
		columnas = n;
	}
	
	public Celula[][] getGeneracionActual () {
		return generacionActual;
	}
	
	public void setGeneracionActual (Celula[][] g) {
		generacionActual = g;
	}
	
	public boolean getInicializado () {
		return inicializado;
	}
	
	public void setInicializado (boolean estado) {
		inicializado = true;
	}
	
	public void setVida (int x, int y) {
	  int coordX = 0;
	  int coordY = 0;
	  
	  int k = 0;
    while(coordX <= x) {
      coordX = getProporcionAncho (k + 1);
      k++;
    }
    int indiceX = k - 1;
    
    k = 0;
    while(coordY <= y) {
      coordY = getProporcionAlto (k + 1);
      k++;
    }
	  int indiceY = k - 1;
	  
		getGeneracionActual()[indiceX][indiceY].setVida(true);
	}
	
	public void setEjecucion (boolean estado) {
		ejecucion  = estado;
	}
	
	public boolean getEjecucion () {
		return ejecucion;
	}
	
	public void setPausa (boolean estado) {
		pausa = estado;
	}
	
	public boolean getPausa () {
		return pausa;
	}
	
}
