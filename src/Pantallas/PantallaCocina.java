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
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Principal.PanelJuego;
import Principal.Sprite;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * 
 * @author Nadia Calle
 *
 */
public class PantallaCocina implements Pantalla {

	/** SPRITES **/
	// MUNECO
	Sprite muneco;
	Image imagenMuneco;
	private static final int ANCHO_MUNECO = 300;

	// FLECHA DERECHA
	Sprite flechaDer;
	Image imagenFlecha;
	private static final int ANCHO_FLECHA = 50;

	/** SPRITES COMIDA **/
	Image imagenBebida;
	Sprite bebida;
	
	Image imagenBurger;
	Sprite burger;
	
	Image imagenBurrito;
	Sprite burrito;
	
	Image imagenDonut;
	Sprite donut;
	
	Image imagenPerrito;
	Sprite perrito;
	
	Image imagenTaco;
	Sprite taco;
	
	Image imagenUvas;
	Sprite uvas;
	
	Image imagenFresa;
	Sprite fresa;
	
	Sprite spriteAux = null;
	
	private static final int ANCHO_COMIDA = 80;
	
	/** SONIDO **/
	Player apl;
	
	
	/** FONDO **/
	private BufferedImage fondoCocina;
	private Image fondoEscaladoCocina;

	/** PANEL JUEGO **/
	PanelJuego panelJuego;
	
	/**
	 * Constructo que inicia la pantalla con un panel de juego
	 * @param panel
	 */
	public PantallaCocina(PanelJuego panel) {
		inicializarPantalla(panel);
	}

	/** FUENTE **/
	final Font fuente = new Font("Arial", Font.BOLD, 30);

	/**
	 * Metodo que inicializa la pantalla con los componentes
	 */
	@Override
	public void inicializarPantalla(PanelJuego panel) {
		this.panelJuego = panel;

		
		try {
			// Imagen de fondo
			fondoCocina = ImageIO.read(new File("src/Imagenes/fondoCocina.jpeg"));
			
			// Imagen del muneco
			imagenMuneco = ImageIO.read(new File("src/Imagenes/muneco.png"));
			
			//Imagen de la flecha Derecha
			imagenFlecha = ImageIO.read(new File("src/Imagenes/flechaDer.png"));
			
			//Imagenes de las comidas
			imagenBebida= ImageIO.read(new File("src/Imagenes/bebida.png"));
			imagenBurger = ImageIO.read(new File("src/Imagenes/burger.png"));
			imagenBurrito = ImageIO.read(new File("src/Imagenes/burrito.png"));
			imagenDonut = ImageIO.read(new File("src/Imagenes/donut.png"));
			imagenPerrito = ImageIO.read(new File("src/Imagenes/perrito.png"));
			imagenTaco = ImageIO.read(new File("src/Imagenes/taco.png"));
			imagenUvas = ImageIO.read(new File("src/Imagenes/uvas.png"));
			imagenFresa = ImageIO.read(new File("src/Imagenes/fresa.png"));
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("PROBLEMAS AL CARGAR LAS IMÃ?GENES. FIN DEL PROGRAMA");
			System.exit(1);
		}

		// FONDO ESCALADO
		fondoEscaladoCocina = fondoCocina.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				BufferedImage.SCALE_SMOOTH);

		// CREAR MUNECO
		muneco = new Sprite(((panelJuego.getWidth() / 2) - (ANCHO_MUNECO / 2)),
				((panelJuego.getHeight() / 2) - (ANCHO_MUNECO / 2)), ANCHO_MUNECO, ANCHO_MUNECO, 0, 0, imagenMuneco,
				true);

		// CREAR FLECHA
		flechaDer = new Sprite(panelJuego.getWidth() - 100, panelJuego.getHeight() / 2, ANCHO_FLECHA, ANCHO_FLECHA, 0,
				0, imagenFlecha, true);

		//CREAR COMIDAS
		bebida = new Sprite(panelJuego.getWidth()/2 - 400, panelJuego.getHeight()/2 +250, ANCHO_COMIDA, ANCHO_COMIDA, 0, 0, imagenBebida, true);
		burger = new Sprite(panelJuego.getWidth()/2 - 300, panelJuego.getHeight()/2 +250, ANCHO_COMIDA, ANCHO_COMIDA, 0, 0, imagenBurger, true);
		burrito = new Sprite(panelJuego.getWidth()/2 - 200, panelJuego.getHeight()/2 +250, ANCHO_COMIDA, ANCHO_COMIDA, 0, 0, imagenBurrito, true);
		donut = new Sprite(panelJuego.getWidth()/2 - 100, panelJuego.getHeight()/2 +250, ANCHO_COMIDA, ANCHO_COMIDA, 0, 0, imagenDonut, true);
		perrito = new Sprite(panelJuego.getWidth()/2, panelJuego.getHeight()/2 +250, ANCHO_COMIDA, ANCHO_COMIDA, 0, 0, imagenPerrito, true);
		taco = new Sprite(panelJuego.getWidth()/2 + 100, panelJuego.getHeight()/2 +250, ANCHO_COMIDA, ANCHO_COMIDA, 0, 0, imagenTaco, true);
		uvas = new Sprite(panelJuego.getWidth()/2 + 200, panelJuego.getHeight()/2 +250, ANCHO_COMIDA, ANCHO_COMIDA, 0,	0, imagenUvas, true);
		fresa = new Sprite(panelJuego.getWidth()/2 + 300, panelJuego.getHeight()/2 +250, ANCHO_COMIDA, ANCHO_COMIDA, 0,	0, imagenFresa, true);

		
	}

	/**
	 * Metodo que pinta por pantalla los componentes
	 */
	@Override
	public void pintarPantalla(Graphics g) {
		rellenarFondo(g);

		muneco.pintarEnMundo(g);

		flechaDer.pintarEnMundo(g);

		bebida.pintarEnMundo(g);
		burger.pintarEnMundo(g);
		burrito.pintarEnMundo(g);
		donut.pintarEnMundo(g);
		perrito.pintarEnMundo(g);
		taco.pintarEnMundo(g);
		uvas.pintarEnMundo(g);
		fresa.pintarEnMundo(g);
		
		if(spriteAux != null) {
			spriteAux.pintarEnMundo(g);
		}
			
		g.setFont(fuente);
		g.setColor(Color.BLACK);
		g.drawString("Nivel: " + panelJuego.getNivel(), muneco.getPosX() - 100, muneco.getPosY() - 150);
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
		} catch (InterruptedException e) {e.printStackTrace();}
		
		comprobarColisiones();
		
	}

	/**
	 * Metodo que realiza una accion al pulsar el raton sobre un elemento
	 */
	@Override
	public void pulsarRaton(MouseEvent e) {
		
		if(bebida.hagoclick(e)) {
			spriteAux = new Sprite(e.getX() - ANCHO_COMIDA/2, e.getY() - ANCHO_COMIDA/2, ANCHO_COMIDA, ANCHO_COMIDA, 0,	0, imagenBebida, true);
		}
		
		if(burger.hagoclick(e)) {
			spriteAux = new Sprite(e.getX() - ANCHO_COMIDA/2, e.getY() - ANCHO_COMIDA/2, ANCHO_COMIDA, ANCHO_COMIDA, 0,	0, imagenBurger, true);
		}
		
		if(burrito.hagoclick(e)) {
			spriteAux = new Sprite(e.getX() - ANCHO_COMIDA/2, e.getY() - ANCHO_COMIDA/2, ANCHO_COMIDA, ANCHO_COMIDA, 0,	0, imagenBurrito, true);
		}
		
		if(donut.hagoclick(e)) {
			spriteAux = new Sprite(e.getX() - ANCHO_COMIDA/2, e.getY() - ANCHO_COMIDA/2, ANCHO_COMIDA, ANCHO_COMIDA, 0,	0, imagenDonut, true);
		}
		
		if(perrito.hagoclick(e)) {
			spriteAux = new Sprite(e.getX() - ANCHO_COMIDA/2, e.getY() - ANCHO_COMIDA/2, ANCHO_COMIDA, ANCHO_COMIDA, 0,	0, imagenPerrito, true);
		}

		if(taco.hagoclick(e)) {
			spriteAux = new Sprite(e.getX() - ANCHO_COMIDA/2, e.getY() - ANCHO_COMIDA/2, ANCHO_COMIDA, ANCHO_COMIDA, 0,	0, imagenTaco, true);
		}

		if(uvas.hagoclick(e)) {
			spriteAux = new Sprite(e.getX() - ANCHO_COMIDA/2, e.getY() - ANCHO_COMIDA/2, ANCHO_COMIDA, ANCHO_COMIDA, 0,	0, imagenUvas, true);
		}

		if(fresa.hagoclick(e)) {
			spriteAux = new Sprite(e.getX() - ANCHO_COMIDA/2, e.getY() - ANCHO_COMIDA/2, ANCHO_COMIDA, ANCHO_COMIDA, 0,	0, imagenFresa, true);
		}
		
		if(flechaDer.hagoclick(e)) {
			panelJuego.setPantalla(new PantallaServicio(panelJuego));
		}

		
	}

	/**
	 * Metodo que realiza una accion al mover el raton
	 */
	@Override
	public void moverRaton(MouseEvent e) {
		if(spriteAux != null) {
			spriteAux.setPosX(e.getX()-spriteAux.getAncho()/2);
			spriteAux.setPosY(e.getY()-spriteAux.getAlto()/2);
		}
		
	}

	/**
	 * Metodo que al redimensionar la pantalla se escala la imagen de fondo
	 */
	@Override
	public void redimensionarPantalla() {
		fondoEscaladoCocina = fondoCocina.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				BufferedImage.SCALE_SMOOTH);
	}

	/**
	 * Metodo que pinta de fondo la imagen escalada
	 * @param g
	 */
	private void rellenarFondo(Graphics g) {
		g.drawImage(fondoEscaladoCocina, 0, 0, null);
	}

	/**
	 * Metodo que comprueba las colisiones de un Sprite con otro
	 */
	private void comprobarColisiones() {

		// Comprobamos colision de la comida elegida con el muneco:
		if (spriteAux != null) {
			if (spriteAux.colisionaMuneco(muneco)) {
				spriteAux = null;
				panelJuego.aumentarPuntuacionComida();
				panelJuego.aumentarNivel();

				// Reproductor de sonido al comer
				try {
					apl = new Player(new FileInputStream("src/Sonidos/chips.mp3"));
					apl.play();
				} catch (FileNotFoundException | JavaLayerException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
}
