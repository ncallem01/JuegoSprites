package Pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Principal.PanelJuego;
import Principal.Sprite;

public class PantallaInicio implements Pantalla {

	/**PINICIAL COLOR **/
	Color colorLetraInicio = Color.WHITE;
	final Font fuenteInicio = new Font("", Font.BOLD, 30);

	PanelJuego panelJuego;
	
	/** SPRITES **/
	// MUNECO
	Sprite caraMuneco;
	Image imagenCaraMuneco;
	private static final int ANCHO_CARA = 100;

	/**
	 * Constructo que inicia la pantalla con un panel de juego
	 * @param panel
	 */
	public PantallaInicio(PanelJuego panel) {
		inicializarPantalla(panel);
	}
	
	
	/**
	 * Metodo que inicializa la pantalla con los componentes
	 */
	@Override
	public void inicializarPantalla(PanelJuego panel) {
		this.panelJuego = panel;
		
	}
	

	/**
	 * Metodo que pinta por pantalla los componentes
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
		g.setFont(fuenteInicio);
		g.setColor(colorLetraInicio);
		g.drawString("Comienza el juego", panelJuego.getWidth()/2-120,  panelJuego.getHeight()/2-10);
		
	}

	/**
	 * Ejecuto los frames
	 */
	@Override
	public void ejecutarFrame() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {e.printStackTrace();}
		colorLetraInicio = Color.WHITE; //colorLetraInicio == Color.WHITE ? Color.ORANGE : Color.WHITE;
		
	}

	/**
	 * Metodo que realiza una accion al pulsar el raton sobre un elemento
	 */
	@Override
	public void pulsarRaton(MouseEvent e) {
		//pantallaActual = P_JUGANDO;
		panelJuego.setPantalla(new PantallaCocina(panelJuego));
		
	}

	/**
	 * Metodo que realiza una accion al mover el raton
	 */
	@Override
	public void moverRaton(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Metodo que al redimensionar la pantalla se escala la imagen de fondo
	 */
	@Override
	public void redimensionarPantalla() {
		// TODO Auto-generated method stub
		
	}

}
