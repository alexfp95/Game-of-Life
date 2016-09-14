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

public class InfoRepeticiones extends JFrame {
	
	final int ANCHO = 550;
	final int ALTO = 100;
	final int VGAP = 20;
	final int HGAP = 20;
	
	public InfoRepeticiones () {
		setTitle("Error al insertar las repeticiones");
		setLayout(new FlowLayout(FlowLayout.CENTER, HGAP, VGAP));
		
		JLabel autor = new JLabel ("Debe ser un valor numérico entre 1 y 100");
		add(autor);
		
		setSize(ANCHO, ALTO);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
