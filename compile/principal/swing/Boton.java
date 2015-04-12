package principal.swing;

import java.awt.Graphics;

import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class Boton extends Componente{
	private final int ESTADO_NORMAL = 0;
	private final int ESTADO_FOCUS = 1;
	
	private final int CANT_ESTADOS = 3;
	
	private final int ESTADO_INICIAL = ESTADO_NORMAL;
	
	private Sprite []imagenes;
	private int estadoActual = ESTADO_INICIAL;
	private EventComponente listener;
	
	public Boton(final Sprite normal, final Sprite focus) {
//		this.hs = hs;
		cargarImagenes(normal,focus);
	}
	public Boton(final int id, final Sprite normal, final Sprite focus) {
		this.id = id;
		cargarImagenes(normal,focus);
	}
	
	private void cargarImagenes(final Sprite normal, final Sprite focus){
		imagenes = new Sprite[CANT_ESTADOS];
		imagenes[ESTADO_NORMAL] = normal;
		imagenes[ESTADO_FOCUS] = focus;
	}
	
	@Override
	public void EventGetFocus() {
		estadoActual = ESTADO_FOCUS;
	}

	@Override
	public void EventLoseFocus() {
		estadoActual = ESTADO_NORMAL;
	}

	@Override
	public void EventMouseOver() {
		estadoActual = ESTADO_FOCUS;
	}

	@Override
	public void EventMouseLose() {
		estadoActual = ESTADO_NORMAL;
	}

	@Override
	public void EventClicked() {
		if(listener != null) listener.EventClicked(this);
	}
	
	@Override
	public void dibujar(Graphics g) {
		g.drawImage(imagenes[estadoActual].getImagen(), x+menu.getX(), y+menu.getY(), width, height, null);
	}
	
	public void addEvent(EventComponente e){
		listener = e;
	}
	
}
