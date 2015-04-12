package principal.graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.GestorPrincipal;
import principal.control.GestorControles;
import principal.herramientas.Util;
import principal.maquinaestado.GestorEstados;

public class SuperficieDibujo extends Canvas {

	private static final long serialVersionUID = -6227038142688953660L;
	private BufferedImage bi;
	private int ancho;
	private int alto;

	public SuperficieDibujo(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;
		
		bi = Util.crearImagenCompatible(Constantes.ANCHO_JUEGO, Constantes.ALTO_JUEGO, true);		
		
		setIgnoreRepaint(true);
		setCursor(GestorControles.raton.obtenerCursor());
		setPreferredSize(new Dimension(ancho, alto));
		addKeyListener(GestorControles.teclado);
		setFocusable(true);
		requestFocus();
	}

	public void dibujar(final GestorEstados ge) {
		final BufferStrategy buffer = getBufferStrategy();

		if (buffer == null) {
			createBufferStrategy(4);
			return;
		}

		final Graphics2D g = (Graphics2D) bi.getGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA);

//		if (Constantes.FACTOR_ESCALADO_X != 1.0 || Constantes.FACTOR_ESCALADO_Y != 1.0) {
//			g.scale(Constantes.FACTOR_ESCALADO_X, Constantes.FACTOR_ESCALADO_Y);
//		}

		ge.dibujar(g);

		g.setColor(Color.green);
		g.drawString("FPS: " + GestorPrincipal.obtenerFPS(), 20, 60);
		g.drawString("APS: " + GestorPrincipal.obtenerAPS(), 20, 50);

		g.dispose();

		final Graphics2D g2 = (Graphics2D) buffer.getDrawGraphics();
		g2.drawImage(bi, 0, 0, Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA, null);
		Toolkit.getDefaultToolkit().sync();
		g2.dispose();
		buffer.show();
	}

	public int obtenerAncho() {
		return ancho;
	}

	public int obtenerAlto() {
		return alto;
	}
}