package Pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Principal.PanelJuego;

public class PantallaInicio implements Pantalla {

	/**PINICIAL COLOR **/
	Color colorLetraInicio = Color.WHITE;
	final Font fuenteInicio = new Font("", Font.BOLD, 30);

	PanelJuego panelJuego;
	

	public PantallaInicio(PanelJuego panel) {
		inicializarPantalla(panel);
	}
	
	@Override
	public void inicializarPantalla(PanelJuego panel) {
		this.panelJuego = panel;
		
	}
	

	@Override
	public void pintarPantalla(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());
		g.setFont(fuenteInicio);
		g.setColor(colorLetraInicio);
		g.drawString("Comienza el juego", panelJuego.getWidth()/2-120,  panelJuego.getHeight()/2-10);
		
	}

	@Override
	public void ejecutarFrame() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {e.printStackTrace();}
		colorLetraInicio = Color.WHITE; //colorLetraInicio == Color.WHITE ? Color.ORANGE : Color.WHITE;
		
	}

	@Override
	public void pulsarRaton(MouseEvent e) {
		//pantallaActual = P_JUGANDO;
		panelJuego.setPantalla(new PantallaCocina(panelJuego));
		
	}

	@Override
	public void moverRaton(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redimensionarPantalla() {
		// TODO Auto-generated method stub
		
	}

}
