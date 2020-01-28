package Pantallas;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import Principal.PanelJuego;

/**
 * 
 * @author Nadia Calle
 *
 */
public interface Pantalla {
	
	public void inicializarPantalla(PanelJuego panel);
	
	public void pintarPantalla(Graphics g);
	
	public void ejecutarFrame();
	
	
	//Listeners
	public void pulsarRaton(MouseEvent e);
	
	public void moverRaton(MouseEvent e);
	
	public void redimensionarPantalla();
	
	
	
}
