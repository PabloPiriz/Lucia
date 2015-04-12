package principal.maquinaestado;

import java.awt.Graphics;

public interface EstadoJuego {
	void actualizar(final float delta);

	void dibujar(final Graphics g);
}