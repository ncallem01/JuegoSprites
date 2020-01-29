package Pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Principal.PanelJuego;
import Principal.Sprite;

public class PantallaInicio implements Pantalla {

	/**PINICIAL COLOR **/
	Color colorLetraInicio = Color.WHITE;
	final Font fuenteInicio = new Font("", Font.BOLD, 20);

	PanelJuego panelJuego;
	
	/** SPRITES **/
	// MUNECO
	Sprite caraMuneco;
	Image imagenCaraMuneco;
	private static final int ANCHO_CARA = 180;

	/** FONDO **/
	private BufferedImage fondoInicio;
	private Image fondoEscaladoInicio;
	

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
		
		try {
			// Imagen de fondo
			fondoInicio = ImageIO.read(new File("src/Imagenes/fondoInicio.jpg"));
			
			// Imagen del muneco
			imagenCaraMuneco = ImageIO.read(new File("src/Imagenes/caraMuneco.png"));
			
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("PROBLEMAS AL CARGAR LAS IMÃ?GENES. FIN DEL PROGRAMA");
			System.exit(1);
		}

		// FONDO ESCALADO
		fondoEscaladoInicio = fondoInicio.getScaledInstance(1000, 800,
				BufferedImage.SCALE_SMOOTH);

		// CREAR MUNECO
		caraMuneco = new Sprite(((1000 / 2) - (ANCHO_CARA / 2)),
				((800 / 2) - (ANCHO_CARA / 2)), ANCHO_CARA, ANCHO_CARA, 0, 0, imagenCaraMuneco,
				true);
		
	}
	

	/**
	 * Metodo que pinta por pantalla los componentes
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		
		rellenarFondo(g);

		caraMuneco.pintarEnMundo(g);
		
		g.setFont(fuenteInicio);
		g.setColor(colorLetraInicio);
		g.drawString("HAZ CLICK PARA INICIAR EL JUEGO", panelJuego.getWidth()/2-150,  panelJuego.getHeight()/2+220);
		
	}

	/**
	 * Ejecuto los frames
	 */
	@Override
	public void ejecutarFrame() {
		try {
			Thread.sleep(110);
		} catch (InterruptedException e) {e.printStackTrace();}
		colorLetraInicio = colorLetraInicio == Color.WHITE ? Color.RED : Color.WHITE;
		
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
		fondoEscaladoInicio = fondoInicio.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				BufferedImage.SCALE_SMOOTH);
	}
	
	/**
	 * Metodo que pinta de fondo la imagen escalada
	 * @param g
	 */
	private void rellenarFondo(Graphics g) {
		g.drawImage(fondoEscaladoInicio, 0, 0, null);
	}

}
