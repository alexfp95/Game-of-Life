/*
 * Asignatura: Programación de Aplicaciones Interactivas (PAI)
 * Curso: 2016
 * Autor: Alexis Daniel Fuentes Pérez
 * Contacto: alu0100816761@ull.edu.es
 * 
 * Clase Principal. Juego de la vida.
 * Puede ejecutarse como Standalone o Applet
 */

package life;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Principal {
	
	final static int ANCHO = 1000;
  final static int ALTO = 1000;
 
	public static void main(String[] args) {
    JFrame frame = new JFrame ("Juego de la vida");
    
    Vista applet = new Vista ();
    
    applet.setStandalone(true);
    
    //applet.getParametrosLineaComandos(args);
    
    frame.add(applet, BorderLayout.CENTER);
    
    applet.init();
    applet.start();
		
    frame.setSize(ANCHO, ALTO);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
	}

}
