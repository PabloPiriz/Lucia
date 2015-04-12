package principal.entidad;

import java.awt.Rectangle;

import principal.maquinaestado.estados.juego.GestorJuego;

public abstract class Objeto implements Entidad{


	int id;

	boolean remover = false;
	float velocidadMaximaX, velocidadMaximaY;

	float velocidadX, velocidadY;
	float x = 0, y = 0;
	float width, height;
	Rectangle colision;

	protected void mover() {
		//N,S,E,W
		x += velocidadX;
		y += velocidadY;
	}
	
	protected void remover() {
		remover = true;
	}
	
	public boolean isRemover() {
		return remover;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Rectangle getColisionCube() {
		return colision;
	}
	
	public abstract void colision(Objeto o);
}
