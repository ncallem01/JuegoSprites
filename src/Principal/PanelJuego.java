package Principal;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Pantallas.Pantalla;
import Pantallas.PantallaInicio;


public class PanelJuego extends JPanel implements Runnable{
	
	/** PANTALLAS **/
	Pantalla pantallaEjecucion;
	
	/** Nivel de juego **/
	private String nivel = "Bebé";
	
	/** Contador de puntos**/
	private int puntuacion = 0;
	
	//El contructor
	public PanelJuego(){
		
		pantallaEjecucion = new PantallaInicio(this);
		
		//HILO
		new Thread(this).start();
		
		//LÃ?STENERS
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				pantallaEjecucion.pulsarRaton(e);
				
			}
		});
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				pantallaEjecucion.redimensionarPantalla();
			}
		});
		
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				pantallaEjecucion.moverRaton(e);
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				mouseMoved(e);
			}
		});
    }

	//MÃ©todo que se llama automÃ¡ticamente
	@Override
	public void paintComponent(Graphics g){
		
		pantallaEjecucion.pintarPantalla(g);
	}


	@Override
	public void run() {
		while(true) {
			repaint();
			Toolkit.getDefaultToolkit().sync();
			
			pantallaEjecucion.ejecutarFrame();
			//Siempre repinto.
			
		}
		
	}

	/**
	 * 
	 * @param pantalla
	 */
	public void setPantalla(Pantalla pantalla) {
		this.pantallaEjecucion = pantalla;
	}

	/**
	 * 
	 * @return
	 */
	public String getNivel() {
		return nivel;
	}
	
	/**
	 * 
	 * @param nivel
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * Aumenta el contador de asteroides explotados
	 */
	public void aumentarNivel() {
		if((nivel.equalsIgnoreCase("bebé")) && puntuacion > 149) {
			setNivel("Adolescente");
		}
		if((nivel.equalsIgnoreCase("Adolescente")) && puntuacion > 499) {
			setNivel("Adulto");
		}
		if((nivel.equalsIgnoreCase("Adulto")) && puntuacion > 799) {
			setNivel("Anciano");
		}
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public void aumentarPuntuacionComida() {
		puntuacion += 20;
	}
	
	public void aumentarPuntuacionCaca() {
		puntuacion += 10;
	}
	
	public void aumentarPuntuacionDucha() {
		puntuacion += 5;
	}
	
	public void aumentarPuntuacionJuego() {
		puntuacion += 5;
	}
	
	public void restarPuntuacionJuego() {
		puntuacion -= 5;
	}
	
	
   }




















