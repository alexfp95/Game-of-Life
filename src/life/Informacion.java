/*
 * Asignatura: Programación de Aplicaciones Interactivas (PAI)
 * Curso: 2016
 * Autor: Alexis Daniel Fuentes Pérez
 * Contacto: alu0100816761@ull.edu.es
 * 
 * Clase Información. Ventana que muestra información sobre la practica
 */

package life;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Clase Informacion
 * @author Alexis Daniel Fuentes Pérez
 */

public class Informacion extends JFrame {
	
	final int ANCHO = 550;
	final int ALTO = 250;
	final int VGAP = 20;
	final int HGAP = 20;
	
	public Informacion () {
		setTitle("Informacion - Juego de la vida");
		setLayout(new FlowLayout(FlowLayout.CENTER, HGAP, VGAP));
		
		JLabel autor = new JLabel ("Autor: Alexis Daniel Fuentes Pérez");
		add(autor);
		
		JLabel contacto = new JLabel ("Contacto: alu0100816761@ull.edu.es");
		add(contacto);
		
		JLabel asignatura = new JLabel ("Asignatura: Programacion de Aplicaciones Interactivas");
		add(asignatura);
		
		JLabel practica = new JLabel ("Practica: Juego de la vida");
		add(practica);
		
		JLabel info = new JLabel ("Inserta células y observa como evolucionan las siguientes generaciones.");
		add(info);
		
		JLabel infoExtra = new JLabel ("Puede ejecutarse como StandAlone y Applet");
		add(infoExtra);
		
		setSize(ANCHO, ALTO);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
