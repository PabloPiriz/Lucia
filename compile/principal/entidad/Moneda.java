package principal.entidad;

import java.awt.Graphics;
import java.awt.Rectangle;

import principal.Constantes;
import principal.sprites.Animacion;
import principal.sprites.HojaSprites;

public class Moneda extends ObjetoAnimado{
	
	public static final int WIDTH = 64;
	public static final int HEIGHT = 64;
	
	
	private static final HojaSprites MONEDA1 			= new HojaSprites(Constantes.RUTA_MisCELANEA_MONEDA,440,40,10,1,false);
	private static final Animacion Moneda1Girar			= new Animacion(MONEDA1, 0, 0, 10, (byte)0, .2f, true, false);
	private static float factorVelocidad = 1f;
	private final float factorVelocidadMax = 2f;
	private final float factorVelocidadAumentadoPorMoneda = .1f;
	private  int puntaje = 5;
	
	public Moneda(final int posX,final int posY) {
		super();
		width = WIDTH;
		height = HEIGHT;
		x = posX;
		y = posY;
		velocidadMaximaX = 4.5f * factorVelocidad;
		velocidadX = -velocidadMaximaX;
		velocidadMaximaY = 0f;
		colision = new Rectangle((int)width,(int) height);
		animaciones[animacionActual].start();
		if (factorVelocidad < factorVelocidadMax)factorVelocidad += factorVelocidadAumentadoPorMoneda;
	}
	
	@Override
	protected void cargarAnimaciones() {
		animaciones = new Animacion[]{Moneda1Girar};
	}


	
	
	@Override
	public void actualizar(float delta) {
		super.actualizar(delta);
		mover();
	}

	

	@Override
	protected void mover() {
		colision.x = (int) x;
		colision.y = (int) y;
		if (x+width < 0) remover();
		super.mover();
	}
	

	
	@Override
	public void dibujar(Graphics g) {
		super.dibujar(g);
	}

	@Override
	public void colision(Objeto o) {
		if (o instanceof Jugador)remover();
	}
	
	public int getPuntaje() {
		return puntaje;
	}

}
