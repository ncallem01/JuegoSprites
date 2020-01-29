package Pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import Principal.PanelJuego;
import Principal.Sprite;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * 
 * @author Nadia Calle
 *
 */
public class PantallaDucha implements Pantalla {

	/** SPRITES **/
	// MUNECO
	Sprite muneco;
	Image imagenMuneco;
	private static final int ANCHO_MUNECO = 300;

	// FLECHA DERECHA
	Sprite flechaDer;
	Image imagenFlecha;
	private static final int ANCHO_FLECHA = 50;

	/** SPRITES **/
	Image imagenDucha;
	Sprite ducha = null;

	Image imagenCruz;
	Sprite cruz;
	
	Image imagenLluvia;
	Sprite lluvia=null;


	Sprite spriteAux = null;
	private static final int ANCHO_DUCHA = 100;
	private static final int ANCHO_CRUZ = 100;
	
	/** FONDO **/
	private BufferedImage fondoDucha;
	private Image fondoEscaladoDucha;

	/** PANEL JUEGO **/
	PanelJuego panelJuego;


	/** FUENTE **/
	final Font fuente = new Font("Arial", Font.BOLD, 30);

	int numero;
	
	
	Player apl;

	

	/**
	 * Constructo que inicia la pantalla con un panel de juego
	 * @param panel
	 */
	public PantallaDucha(PanelJuego panel) {
		inicializarPantalla(panel);
	}
	
	/**
	 * Metodo que inicializa la pantalla con los componentes
	 */
	@Override
	public void inicializarPantalla(PanelJuego panel) {
		this.panelJuego = panel;

		// Imagenes
		try {
			fondoDucha = ImageIO.read(new File("src/Imagenes/fondoDucha.jpg"));
			imagenMuneco = ImageIO.read(new File("src/Imagenes/muneco.png"));
			imagenFlecha = ImageIO.read(new File("src/Imagenes/flechaDer.png"));
			
			//ducha
			imagenDucha= ImageIO.read(new File("src/Imagenes/ducha.png"));
			imagenCruz= ImageIO.read(new File("src/Imagenes/cruz.png"));
			imagenLluvia= ImageIO.read(new File("src/Imagenes/lluvia.gif"));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("PROBLEMAS AL CARGAR LAS IMÃ?GENES. FIN DEL PROGRAMA");
			System.exit(1);
		}

		// FONDO ESCALADO
		fondoEscaladoDucha = fondoDucha.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), BufferedImage.SCALE_SMOOTH);

		// CREAR MUNECO
		muneco = new Sprite(((panelJuego.getWidth() / 2) - (ANCHO_MUNECO / 2)),
				((panelJuego.getHeight() / 2) - (ANCHO_MUNECO / 2)), ANCHO_MUNECO, ANCHO_MUNECO, 0, 0, imagenMuneco,
				true);

		// CREAR FLECHA
		flechaDer = new Sprite(panelJuego.getWidth() - 100, panelJuego.getHeight() / 2, ANCHO_FLECHA, ANCHO_FLECHA, 0,
				0, imagenFlecha, true);		
		
		//CREAR DUCHA
		ducha = new Sprite(panelJuego.getWidth()/2 - 400, panelJuego.getHeight()/2 -200, ANCHO_DUCHA, ANCHO_DUCHA, 0, 0, imagenDucha, true);
		cruz = new Sprite(panelJuego.getWidth()/2 - 400, panelJuego.getHeight()/2 +200, ANCHO_CRUZ, ANCHO_CRUZ, 0, 0, imagenCruz, true);
		//lluvia = new Sprite(ducha.getAncho(), ducha.getAlto(), ANCHO_DUCHA, ANCHO_DUCHA, 0, 0, imagenLluvia, true);

	}

	/**
	 * Metodo que pinta por pantalla los componentes
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		rellenarFondo(g);

		muneco.pintarEnMundo(g);

		flechaDer.pintarEnMundo(g);

		if (ducha != null) {
			ducha.pintarEnMundo(g);
		}
		
		if (lluvia != null) {
			lluvia.pintarEnMundo(g);
		}
		
		cruz.pintarEnMundo(g);

		if (spriteAux != null) {
			spriteAux.pintarEnMundo(g);
		}

		g.setFont(fuente);
		g.setColor(Color.BLACK);
		g.drawString("Nivel: " + panelJuego.getNivel(), muneco.getPosX() - 120, muneco.getPosY() - 150);
		g.drawString("Puntuacion: " + panelJuego.getPuntuacion(), muneco.getPosX() + 200, muneco.getPosY() - 150);
	}

	/**
	 * Ejecuto los frames
	 * Comprueba las colisiones
	 */
	@Override
	public void ejecutarFrame() {
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		comprobarColisiones();
		
	}
	
	
	/**
	 * Metodo que realiza una accion al pulsar el raton sobre un elemento
	 */
	@Override
	public void pulsarRaton(MouseEvent e) {

		if (ducha.hagoclick(e)) {

			spriteAux = new Sprite(e.getX() - ANCHO_DUCHA / 2, e.getY() - ANCHO_DUCHA / 2, ANCHO_DUCHA, ANCHO_DUCHA, 0, 0,
					imagenDucha, true);
			
			lluvia = new Sprite(ducha.getAncho(), ducha.getAlto()/2 +100, ANCHO_DUCHA, ANCHO_DUCHA, 0, 0, imagenLluvia, true);
			
			ducha = null;
			
			
		}
		
		if(flechaDer.hagoclick(e)) {
			panelJuego.setPantalla(new PantallaJuego(panelJuego));
		}

	}

	/**
	 * Metodo que realiza una accion al mover el raton
	 */
	@Override
	public void moverRaton(MouseEvent e) {
		if (spriteAux != null) {
			spriteAux.setPosX(e.getX() - spriteAux.getAncho() / 2);
			spriteAux.setPosY(e.getY() - spriteAux.getAlto() / 2);
		}
		if (lluvia != null) {
			lluvia.setPosX(e.getX() - lluvia.getAncho()/2);
			lluvia.setPosY(e.getY() - lluvia.getAlto()/2+10);
		}

	}

	/**
	 * Metodo que al redimensionar la pantalla se escala la imagen de fondo
	 */
	@Override
	public void redimensionarPantalla() {
		fondoEscaladoDucha = fondoDucha.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				BufferedImage.SCALE_SMOOTH);
	}

	/**
	 * Metodo que pinta de fondo la imagen escalada
	 * @param g
	 */
	private void rellenarFondo(Graphics g) {
		g.drawImage(fondoEscaladoDucha, 0, 0, null);
	}

	/**
	 * Metodo que comprueba las colisiones de un Sprite con otro
	 */
	private void comprobarColisiones() {

		// Comprobamos colision de la comida con el muneco:
		if (spriteAux != null) {
			if (spriteAux.colisiona(cruz)) {
				spriteAux = null;
				lluvia=null;
				panelJuego.aumentarPuntuacionDucha();
				panelJuego.aumentarNivel();
				ducha = new Sprite(panelJuego.getWidth()/2 - 400, panelJuego.getHeight()/2 -200, ANCHO_DUCHA, ANCHO_DUCHA, 0, 0, imagenDucha, true);
			}
		}
		

	}
	

}
