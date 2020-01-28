package Pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import Principal.PanelJuego;
import Principal.Sprite;

public class PantallaJuego implements Pantalla {

	/** SPRITES **/
	// MUNECO
	Sprite muneco;
	Image imagenMuneco;
	private static final int ANCHO_MUNECO = 100;

	// FLECHA DERECHA
	Sprite flechaDer;
	Image imagenFlecha;
	private static final int ANCHO_FLECHA = 50;

	/** SPRITES **/
	Image imagenComidas;
	ArrayList<Sprite> comidas;
	private static final int NUM_COMIDAS = 10;
	private static final int LADO_COMIDAS = 40;
	
	Image imagenBombas;
	ArrayList<Sprite> bombas;
	private static final int NUM_BOMBAS = 3;
	private static final int LADO_BOMBAS = 40;

	private int contEliminados;
	
	/** FONDO **/
	private BufferedImage fondoJuego;
	private Image fondoEscaladoJuego;

	/** PANEL JUEGO **/
	PanelJuego panelJuego;

	
	public PantallaJuego(PanelJuego panel) {
		inicializarPantalla(panel);
	}

	/** FUENTE **/
	final Font fuente = new Font("Arial", Font.BOLD, 30);

	int numero;

	@Override
	public void inicializarPantalla(PanelJuego panel) {
		this.panelJuego = panel;

		// Imagenes
		try {
			fondoJuego = ImageIO.read(new File("src/Imagenes/fondoJuego.jpg"));
			imagenMuneco = ImageIO.read(new File("src/Imagenes/muneco.png"));
			imagenFlecha = ImageIO.read(new File("src/Imagenes/flechaDer.png"));
			
			//Comidas
			imagenComidas = ImageIO.read(new File("src/Imagenes/donut.png"));
			imagenBombas = ImageIO.read(new File("src/Imagenes/bomba.png"));
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("PROBLEMAS AL CARGAR LAS IMÃ?GENES. FIN DEL PROGRAMA");
			System.exit(1);
		}

		// FONDO ESCALADO
		fondoEscaladoJuego = fondoJuego.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), BufferedImage.SCALE_SMOOTH);

		// CREAR MUNECO
		muneco = new Sprite(-ANCHO_MUNECO -10, -ANCHO_MUNECO -10, ANCHO_MUNECO, ANCHO_MUNECO, 0, 0, imagenMuneco,
				true);

		// CREAR FLECHA
		flechaDer = new Sprite(panelJuego.getWidth() - 100, panelJuego.getHeight() / 2, ANCHO_FLECHA, ANCHO_FLECHA, 0,
				0, imagenFlecha, true);

		
		// COMIDAS:
		imagenComidas = imagenComidas.getScaledInstance(LADO_COMIDAS, LADO_COMIDAS, Image.SCALE_SMOOTH);
		comidas = new ArrayList<>();
		Random rd = new Random();
		for (int i = 0; i < NUM_COMIDAS; i++) {
			comidas.add(new Sprite(10, 10, LADO_COMIDAS, LADO_COMIDAS, rd.nextInt(14) + 1, rd.nextInt(14) + 1,
					imagenComidas, false));
		}
		
		// COMIDAS:
		imagenBombas = imagenBombas.getScaledInstance(LADO_BOMBAS, LADO_BOMBAS, Image.SCALE_SMOOTH);
		bombas = new ArrayList<>();
		Random rd2 = new Random();
		for (int i = 0; i < NUM_BOMBAS; i++) {
			bombas.add(new Sprite(10, 10, LADO_BOMBAS, LADO_BOMBAS, rd2.nextInt(14) + 1, rd2.nextInt(14) + 1,
					imagenBombas, false));
		}
	}

	@Override
	public void pintarPantalla(Graphics g) {
		rellenarFondo(g);

		for (int i = 0; i < comidas.size(); i++) {
			comidas.get(i).pintarEnMundo(g);	
		}
		
		for (int i = 0; i < bombas.size(); i++) {
			bombas.get(i).pintarEnMundo(g);	
		}
		
		muneco.pintarEnMundo(g);

		flechaDer.pintarEnMundo(g);

		g.setFont(fuente);
		g.setColor(Color.BLACK);
		g.drawString("Nivel: " + panelJuego.getNivel(), panelJuego.getWidth()/2 - 250, panelJuego.getHeight()/2 - 300);
		g.drawString("Puntuacion: " + panelJuego.getPuntuacion(),  panelJuego.getWidth()/2 + 50, panelJuego.getHeight()/2 - 300);
	}

	@Override
	public void ejecutarFrame() {
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		comprobarColisiones();
		moverSprites();
	}
	
	

	@Override
	public void pulsarRaton(MouseEvent e) {

		if(flechaDer.hagoclick(e)) {
			panelJuego.setPantalla(new PantallaCocina(panelJuego));
		}

	}

	@Override
	public void moverRaton(MouseEvent e) {
		muneco.setPosX(e.getX()-muneco.getAncho()/2);
		muneco.setPosY(e.getY()-muneco.getAlto()/2);

	}

	@Override
	public void redimensionarPantalla() {
		fondoEscaladoJuego = fondoJuego.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
				BufferedImage.SCALE_SMOOTH);
	}

	private void rellenarFondo(Graphics g) {
		g.drawImage(fondoEscaladoJuego, 0, 0, null);
	}
	
	
	/**
	 * Mueve todos los sprites del PanelJuego
	 */
	private void moverSprites() {
		//Asteroides:
		for (int i = 0; i < comidas.size(); i++) {
			comidas.get(i).actualizarPosicion(panelJuego);	
		}
		
		for (int i = 0; i < bombas.size(); i++) {
			bombas.get(i).actualizarPosicion(panelJuego);	
		}
		
	}

	private void comprobarColisiones() {

		// Comprobamos colisiÃ³n del disparo con asteroides:
		for (int i = 0; i < comidas.size(); i++) {
			if (muneco.colisionaJuego(comidas.get(i))) {
				comidas.remove(i);
				panelJuego.aumentarPuntuacionJuego();
			}
		}
		
		for (int i = 0; i < bombas.size(); i++) {
			if (muneco.colisionaJuego(bombas.get(i))) {
				panelJuego.restarPuntuacionJuego();
				
			}
		}

	}
	

}
