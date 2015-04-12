package principal.entidad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import principal.Constantes;
import principal.control.GestorControles;
import principal.maquinaestado.estados.juego.GestorJuego;
import principal.sprites.Animacion;
import principal.sprites.HojaSprites;

public class Jugador extends ObjetoAnimado{
	public final int heart = 1;
	
	private final int ANIM_VOLAR = 0;
	private final int MAX_VIDAS = 5;
	private int vidas = MAX_VIDAS;
	private int puntaje = 0;
	
	public Jugador() {
		super();
		width = 192;
		height = 64;
		velocidadMaximaX = 3.5f;
		velocidadMaximaY = 3.5f;
		colision = new Rectangle((int)width,(int) height);
		cambiarAnimacion(animacionActual);
	}
	
	@Override
	public void actualizar(float delta) {
		super.actualizar(delta);
		velocidadY = 0;
		velocidadX = 0;
		if (GestorControles.teclado.abajo.estaPulsada()) velocidadY = velocidadMaximaY;
		if (GestorControles.teclado.arriba.estaPulsada()) velocidadY = -velocidadMaximaY;
		if (GestorControles.teclado.izquierda.estaPulsada()) velocidadX = -velocidadMaximaX;
		if (GestorControles.teclado.derecha.estaPulsada()) velocidadX = velocidadMaximaX;
		mover();
	}
	
	@Override
	protected void mover() {
		colision.x = (int) x;
		colision.y = (int) y;
		boolean colisiones[] = GestorJuego.getMapaActual().enMapa(colision);
		if (colisiones[0] && velocidadY < 0) velocidadY = 0;
		if (colisiones[1] && velocidadY > 0) velocidadY = 0;
		if (colisiones[2] && velocidadX > 0) velocidadX = 0;
		if (colisiones[3] && velocidadX < 0) velocidadX = 0;
		super.mover();
	}

	@Override
	public void dibujar(Graphics g) {
		super.dibujar(g);
		g.setColor(Color.green);
		g.drawString("x:"+x+"\ny:"+y, 0,100);
	}

	@Override
	protected void cargarAnimaciones() {
		HojaSprites PERSONAJE_VOLAR 			= new HojaSprites(Constantes.RUTA_PERSONAJE,384,128,1,1,false);
		Animacion volar = new Animacion(PERSONAJE_VOLAR, 0, 0, 1, (byte)0, .2f, true, true);
		animaciones = new Animacion[]{volar};
	}
	
	public int getVidas() {
		return vidas;
	}

	@Override
	public void colision(Objeto o) {
		if (o instanceof Moneda){
			Moneda moneda = (Moneda) o;
			puntaje += moneda.getPuntaje();
		}
	}

	public int getPuntaje() {
		return puntaje;
	}
}
