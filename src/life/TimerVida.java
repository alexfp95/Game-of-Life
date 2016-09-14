/*
 * Asignatura: Programación de Aplicaciones Interactivas (PAI)
 * Curso: 2016
 * Autor: Alexis Daniel Fuentes Pérez
 * Contacto: alu0100816761@ull.edu.es
 * 
 * Clase TimerVida. Timer que se utiliza para obtener nuevas generaciones
 */

package life;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

/**
 * Clase TimerVida
 * @author Alexis Daniel Fuentes Pérez
 */

public class TimerVida {
	
	final int DELAY = 1000 / 15;                    // Delay del timer
	
	private Vida juego;
	private Timer timer;
	
	public TimerVida (Vida j) {
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
	
	public Timer getMiTimer () {
		return timer;
	}
	
	/**
	 * Clase interna TimerListener. Obtiene nueva generaciones para el juego de la vida
	 * @author Alexis Daniel Fuentes P�rez
	 */
	
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if((getPanelJuego().getEjecucion()) && (!getPanelJuego().getPausa())) {
				getPanelJuego().siguienteGeneracion();
			}
		}
	}
}
