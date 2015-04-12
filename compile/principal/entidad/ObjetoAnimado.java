package principal.entidad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.sprites.Animacion;

public abstract class ObjetoAnimado extends Objeto{
	
	protected Animacion[] animaciones;
	protected int animacionActual = 0;
	
	protected abstract void cargarAnimaciones(); 
	
	public ObjetoAnimado() {
		cargarAnimaciones();
	}
	
	protected void cambiarAnimacion(final int index) {
		if (index != animacionActual){
			animaciones[animacionActual].stop();
			animacionActual = index;
			animaciones[animacionActual].start();
		}
	}
	
	@Override
	public void actualizar(float delta) {
		animaciones[animacionActual].Actualizar(delta);
	}
	@Override
	public void dibujar(Graphics g) {
		BufferedImage imagen = animaciones[animacionActual].getSpriteActual().getImagen();
		g.drawImage(imagen, (int) x, (int) y, (int) width, (int) height, null);
		g.setColor(Color.pink);
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}
	
}
