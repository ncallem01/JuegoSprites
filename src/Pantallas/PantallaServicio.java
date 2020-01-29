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

public class PantallaServicio implements Pantalla {

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
	Image imagenCaca;
	Sprite caca = null;

	Image imagenPapelera;
	Sprite papelera;

	Sprite spriteAux = null;

	private static final int ANCHO_CACA = 60;
	private static final int ANCHO_PAPELERA = 150;
	
	Player apl;
	
	/** FONDO **/
	private BufferedImage fondoServicio;
	private Image fondoEscaladoServicio;

	/** PANEL JUEGO **/
	PanelJuego panelJuego;

	/** FUENTE **/
	final Font fuente = new Font("Arial", Font.BOLD, 30);

	int numero;

	
	/**
	 * Constructo que inicia la pantalla con un panel de juego
	 * @param panel
	 */
	public PantallaServicio(PanelJuego panel) {
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
			fondoServicio = ImageIO.read(new File("src/Imagenes/fondoServicio.jpg"));
			imagenMuneco = ImageIO.read(new File("src/Imagenes/muneco.png"));
			imagenFlecha = ImageIO.read(new File("src/Imagenes/flechaDer.png"));
			
			//servicio
			imagenCaca= ImageIO.read(new File("src/Imagenes/caca.png"));
			imagenPapelera = ImageIO.read(new File("src/Imagenes/papelera.png"));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("PROBLEMAS AL CARGAR LAS IMÃ?GENES. FIN DEL PROGRAMA");
			System.exit(1);
		}

		// FONDO ESCALADO
		fondoEscaladoServicio = fondoServicio.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), BufferedImage.SCALE_SMOOTH);

		// CREAR MUNECO
		muneco = new Sprite(((panelJuego.getWidth() / 2) - (ANCHO_MUNECO / 2)),
				((panelJuego.getHeight() / 2) - (ANCHO_MUNECO / 2)), ANCHO_MUNECO, ANCHO_MUNECO, 0, 0, imagenMuneco,
				true);

		// CREAR FLECHA
		flechaDer = new Sprite(panelJuego.getWidth() - 100, panelJuego.getHeight() / 2, ANCHO_FLECHA, ANCHO_FLECHA, 0,
				0, imagenFlecha, true);

		
		//CREAR PAPELERA
		papelera = new Sprite(panelJuego.getWidth()/2 + 200, panelJuego.getHeight()/2 +100, ANCHO_PAPELERA, ANCHO_PAPELERA, 0, 0, imagenPapelera, true);
		
		
		//CREAR CACAS
		caca = new Sprite(panelJuego.getWidth()/2 - 400, panelJuego.getHeight()/2 +200, ANCHO_CACA, ANCHO_CACA, 0, 0, imagenCaca, true);
		
	}

	/**
	 * Metodo que pinta por pantalla los componentes
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		rellenarFondo(g);

		muneco.pintarEnMundo(g);

		flechaDer.pintarEnMundo(g);

		if (caca != null) {
			caca.pintarEnMundo(g);
		}

		papelera.pintarEnMundo(g);

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

		if (caca.hagoclick(e)) {

			spriteAux = new Sprite(e.getX() - ANCHO_CACA / 2, e.getY() - ANCHO_CACA / 2, ANCHO_CACA, ANCHO_CACA, 0, 0,
					imagenCaca, true);
			caca = null;
		}
		
		if(flechaDer.hagoclick(e)) {
			panelJuego.setPantalla(new PantallaDucha(panelJuego));
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

	}

	/**
	 * Metodo que al redimensionar la pantalla se escala la imagen de fondo
	 */
	@Override
	public void redimensionarPantalla() {
		fondoEscaladoServicio = fondoServicio.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				BufferedImage.SCALE_SMOOTH);
	}

	/**
	 * Metodo que pinta de fondo la imagen escalada
	 * @param g
	 */
	private void rellenarFondo(Graphics g) {
		g.drawImage(fondoEscaladoServicio, 0, 0, null);
	}

	/**
	 * Metodo que comprueba las colisiones de un Sprite con otro
	 */
	private void comprobarColisiones() {

		// Comprobamos colision de la comida con el muneco:
		if (spriteAux != null) {
			if (spriteAux.colisiona(papelera)) {
				spriteAux = null;
				panelJuego.aumentarPuntuacionCaca();
				panelJuego.aumentarNivel();
			

				try {
					apl = new Player(new FileInputStream("src/Sonidos/caca.mp3"));
					apl.play();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JavaLayerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				caca = new Sprite(panelJuego.getWidth()/2 - 400, panelJuego.getHeight()/2 +200, ANCHO_CACA, ANCHO_CACA, 0, 0, imagenCaca, true);

			}
		}
	}
	
	
}
