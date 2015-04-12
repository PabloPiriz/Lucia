package principal.swing;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import principal.Constantes;
import principal.herramientas.CargadorRecursos;
import principal.log.Log;

public class Menu implements MouseListener,KeyListener,MouseMotionListener{
	
	private int x,y,width,height;
	
	private BufferedImage fondo;
	
	private List<Componente> components;
	
	
	
	public Menu(final int x, final int y, final int width, final int height, final String fondo) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		components = Collections.synchronizedList(new ArrayList<Componente>());
		cargarFondo(fondo);
	}
	
	
	private void cargarFondo(final String ruta) {
		fondo = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta);
	}
	
	public void agregarComponente(final Componente comp){
		components.add(comp);
		comp.addMenu(this);
	}
	
	public void removerComponente(final Componente comp){
		components.remove(comp);
	}
	
	public void actualizar(final float delta) {
		
	}
	
	public void dibujar(Graphics g){
		g.drawImage(fondo, x, y, width, height, null);
		Iterator<Componente> it = components.iterator();
		while(it.hasNext()){
			Componente c = it.next();
			c.dibujar(g);
		}
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Iterator<Componente> it = components.iterator();
		Point p = e.getPoint();
		p = reEscalarPuntero(p);
		while(it.hasNext()){
			Componente c = it.next();
			if(c.sobreElComponente(p))c.EventClicked();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}


	@Override
	public void mouseDragged(MouseEvent e) {
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		Iterator<Componente> it = components.iterator();
		Point p = e.getPoint();
		p = reEscalarPuntero(p);
		while(it.hasNext()){
			Componente c = it.next();
			if(c.sobreElComponente(p)) c.EventMouseOver();
			else c.EventLoseFocus();
		}
	}
	
	private Point reEscalarPuntero(Point p){
		p.x = (int) ((p.x*Constantes.ANCHO_JUEGO)/Constantes.ANCHO_PANTALLA_COMPLETA);
		p.y = (int) ((p.y*Constantes.ALTO_JUEGO)/Constantes.ALTO_PANTALLA_COMPLETA);
		return p;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
}
