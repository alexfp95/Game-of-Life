/*
 * Asignatura: Programación de Aplicaciones Interactivas (PAI)
 * Curso: 2016
 * Autor: Alexis Daniel Fuentes Pérez
 * Contacto: alu0100816761@ull.edu.es
 * 
 * Clase TimerRepaint. Timer que se encarga de refrescar el estado del juego
 */

package life;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

/**
 * Clase TimerRepaint
 * @author Alexis Daniel Fuentes Pérez
 */

public class TimerRepaint {
	
	final int DELAY = 10;                    // Delay del timer
	
	private Vida juego;
	private Timer timer;
	
	public TimerRepaint (Vida j) {
		timer = new Timer(DELAY, new TimerListener());
		juego = j;
		timer.start();
	}
	
	/**
	 * Getters y setters
	 */
	
	public Vida getPanelJuego () {
		return juego;
	}
	
	/**
	 * Clase interna TimerListener. Refresca el panel del juego para ver el estado en el que estan las celulas a cada momento
	 * @author Alexis Daniel Fuentes Pérez
	 */
	
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getPanelJuego().repaint();
		}
	}
}
