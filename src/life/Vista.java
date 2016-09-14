/*
 * Asignatura: Programación de Aplicaciones Interactivas (PAI)
 * Curso: 2016
 * Autor: Alexis Daniel Fuentes Pérez
 * Contacto: alu0100816761@ull.edu.es
 * 
 * Clase Vista. Representa el juego de la vida.
 * Puede ser ejecutado como Standalone y Applet
 */

package life;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

/**
 * Clase Vista
 * @author Alexis Daniel Fuentes Pérez
 */

public class Vista extends JApplet {
	
  final int HGAP = 20;
  final int VGAP = 5;
  final int TAM_INFO = 30;                        // Tamaño del boton de informacion
  final int AJUSTE_INFO = 15;                     // Ajuste para el icono de informacion
  final int VELOCIDAD_DEFECTO = 15;
  final int VELOCIDAD_MINIMA = 1;
  final int VELOCIDAD_MAXIMA = 30;
  final int UN_SEGUNDO = 1000;                    // En milisegundos
  final int DIMENSION_SCROLL_ANCHO = 100;
  final int DIMENSION_SCROLL_ALTO = 20;
  private String[] PATRONES_PREDEF = {"Ninguno","Blinker","Glider","Diehard"};
	
  private Vida juego;                             // Panel con el juego de la vida
  private JPanel controles;                       // Panel de controles
  private TimerVida timerVida;                    // Timer para refrescar el juego
  private TimerRepaint timerRepaint;              // Timer para generar generaciones
  private JButton inicio;
  private JButton pausa;
  private JButton paso;
  private JButton fin;
  private JLabel velocidad; 											// Velocidad de evolucion
  private JScrollBar velocidadScroll;             // Scroll para determinar la velocidad de ejecucion
  private Informacion ventanaInfo;                // Ventana informativa de la aplicacion
  private JButton informacion;
  private JTextField repeticiones;
  private InfoRepeticiones ventanaRepeticiones;		// Aparece si cometes un error introduciendo repeticiones
  private JComboBox comboPatrones;
  private boolean patronSeleccionado;							// Para poder combinar Inicio con el ComboBox
  private int numRepeticiones;										// Repeticiones del patron
  
  private boolean esStandalone = false;           // Determina si es Standalone o no
  
	public void init () {
		
		if(!getStandalone()) {
      // Obtener parametros
    }
		
  	setLayout(new BorderLayout (HGAP, VGAP));
		 
		juego = new Vida ();
		add(juego, BorderLayout.CENTER);
		 
		controles = new JPanel ();
		controles.setLayout(new FlowLayout (FlowLayout.CENTER, HGAP, VGAP));
		 
		inicio = new JButton ("Inicio");
		controles.add(inicio);
		 
		pausa = new JButton ("Pausa");
		controles.add(pausa);
		 
		paso = new JButton ("Paso");
		controles.add(paso);
		 
		fin = new JButton ("Fin");
		controles.add(fin);
		 
		velocidadScroll = new JScrollBar (JScrollBar.HORIZONTAL, VELOCIDAD_DEFECTO, 0, VELOCIDAD_MINIMA, VELOCIDAD_MAXIMA);
		velocidadScroll.setPreferredSize(new Dimension (DIMENSION_SCROLL_ANCHO, DIMENSION_SCROLL_ALTO));
		velocidad = new JLabel ("Velocidad " + velocidadScroll.getValue() + " fps");
		controles.add(velocidad);
		controles.add(velocidadScroll);
		
		repeticiones = new JTextField ("50");
		controles.add(new JLabel ("Repeticiones"));
		controles.add(repeticiones);
		
		comboPatrones = new JComboBox (PATRONES_PREDEF);
		controles.add(comboPatrones);
		
		informacion = new JButton ();
		informacion.setPreferredSize(new Dimension (TAM_INFO, TAM_INFO));
	  ImageIcon ayudaIcon = new ImageIcon ("src/life/info.png");
	  Icon icono = new ImageIcon(ayudaIcon.getImage().getScaledInstance(TAM_INFO + AJUSTE_INFO, TAM_INFO + AJUSTE_INFO, Image.SCALE_DEFAULT));
	  informacion.setIcon(icono);
		 
		controles.add(informacion);
		 
		add(controles, BorderLayout.SOUTH);
		 
		ventanaInfo = new Informacion ();
		ventanaRepeticiones = new InfoRepeticiones ();
		 
		insertarListeners();
		timerVida = new TimerVida (juego);
		timerRepaint = new TimerRepaint (juego);
		patronSeleccionado = false;
		numRepeticiones = 1;
	}
	
	/**
	 * Inserta los listeners
	 */
	
	public void insertarListeners () {
		getPanelJuego().addMouseListener(new MouseAdapter() {
      public void mouseClicked (MouseEvent e) {
        if(!getPanelJuego().getInicializado()) {
        	getPanelJuego().redimensionaTablero();
        }
        getPanelJuego().setVida(e.getX(), e.getY());
      }
    });
		
		getPanelJuego().addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged (MouseEvent e) {
        if(!getPanelJuego().getInicializado()) {
        	getPanelJuego().redimensionaTablero();
        }
        getPanelJuego().setVida(e.getX(), e.getY());
      }
    });
		
		getInicio().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	if(!getPanelJuego().getInicializado()) {
        	getPanelJuego().redimensionaTablero();
        }
      	if(!getPanelJuego().getEjecucion()) {
      		getPanelJuego().setEjecucion(true);
      		if(getSeleccionado()) {
      			for(int i = 0; i < getNumRepeticiones(); i++)
      				gestionarPatrones();
      		}
      	} else {
      		for(int i = 0; i < getNumRepeticiones(); i++)
      			gestionarPatrones();
  			}
      }
    });
		
		getPausa().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	if(getPanelJuego().getEjecucion()) {
      		if(getPanelJuego().getPausa()) {
      			getPanelJuego().setPausa(false);
      		} else {
      			getPanelJuego().setPausa(true);
      		}
      	}
      }
    });
		
		getPaso().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	if(!getPanelJuego().getInicializado()) {
        	getPanelJuego().redimensionaTablero();
        }
      	if(!getPanelJuego().getEjecucion())
      		getPanelJuego().setEjecucion(true);
    		if(!getPanelJuego().getPausa()) {
    			getPanelJuego().setPausa(true);
    		}
    		getPanelJuego().siguienteGeneracion();
      }
    });
		
		getFin().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getPanelJuego().redimensionaTablero();
        getPanelJuego().setEjecucion(false);
        getPanelJuego().setPausa(false);
      }
    });
		
		getBotonInfo().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        getVentanaInfo().setVisible(true);
      }
    });
		
		getVelocidadScroll().addAdjustmentListener(new AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
      	getVelocidadLabel().setText("Velocidad " + getVelocidadScroll().getValue() + " fps");
      	getTimerVida().getMiTimer().setDelay(UN_SEGUNDO / getVelocidadScroll().getValue());
      }
    });
		
		getRepeticiones().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if((Integer.parseInt(getRepeticiones().getText()) >= 1) && (Integer.parseInt(getRepeticiones().getText()) <= 100))
        	setNumRepeticiones(Integer.parseInt(getRepeticiones().getText()));
        else
        	getVentanaRepeticiones().setVisible(true);
      }
    });
		
		getComboPatrones().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        System.out.println(getComboPatrones().getSelectedItem());
        if(!getComboPatrones().getSelectedItem().equals("Ninguno"))
        	setSeleccionado (true);
        else
        	setSeleccionado (false);
      }
    });
	}
	
	public void gestionarPatrones () {
		switch("" + getComboPatrones().getSelectedItem()) {
			case "Blinker": Blinker b = new Blinker (getPanelJuego().getGeneracionActual()); b.dibujar(); break;
			case "Glider": Glider d = new Glider (getPanelJuego().getGeneracionActual()); d.dibujar(); break;
			case "Diehard": Diehard dh = new Diehard (getPanelJuego().getGeneracionActual()); dh.dibujar(); break;
			default: break;
		}
	}
	
	/**
	 * Getters y setters
	 */
	
	public Vida getPanelJuego () {
		return juego;
	}
	
	public JButton getInicio () {
		return inicio;
	}
	
	public JButton getPausa () {
		return pausa;
	}
	
	public JButton getPaso () {
		return paso;
	}
	
	public JButton getFin () {
		return fin;
	}
	
	public JButton getBotonInfo () {
		return informacion;
	}
	
	public JFrame getVentanaInfo () {
		return ventanaInfo;
	}
	
	public JLabel getVelocidadLabel () {
		return velocidad;
	}
	
	public JScrollBar getVelocidadScroll () {
		return velocidadScroll;
	}
	
	public TimerVida getTimerVida () {
		return timerVida;
	}
	
	public void setStandalone (boolean estado) {
    esStandalone = estado;
  }
  
  public boolean getStandalone () {
    return esStandalone;
  }
  
  public void getParametrosLineaComandos (String[] args) {
    // Obtener parametros
  }
  
  public JTextField getRepeticiones () {
  	return repeticiones;
  }
  
  public InfoRepeticiones getVentanaRepeticiones () {
  	return ventanaRepeticiones;
  }
  
  public JComboBox getComboPatrones () {
  	return comboPatrones;
  }
  
  public boolean getSeleccionado () {
  	return patronSeleccionado;
  }
  
  public void setSeleccionado (boolean estado) {
  	patronSeleccionado = estado;
  }
  
  public void setNumRepeticiones (int n) {
		numRepeticiones = n;
	}
	
	public int getNumRepeticiones () {
		return numRepeticiones;
	}
}
