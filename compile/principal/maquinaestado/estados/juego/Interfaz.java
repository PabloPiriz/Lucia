package principal.maquinaestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.Util;

public class Interfaz {
	
	GestorJuego GJ;
	BufferedImage bi;
	BufferedImage heart;
	
	public Interfaz(GestorJuego GJ) {
		this.GJ = GJ;
		bi = Util.crearImagenCompatible(Constantes.ANCHO_JUEGO, Constantes.ALTO_JUEGO, false);
		heart = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_MISCELANEA_HEART);
	}
	
	
	public void actualizar(final float delta) {
		
	}

	public void dibujar(Graphics g) {
		int x = 0;
//		Graphics gr = bi.getGraphics();
		if (g != null){
			for (int i = 0; i < GJ.j.getVidas();i++){
				g.drawImage(heart, x, 0, 32, 32, null);
				x+=32;
			}
		}
		g.setColor(Color.RED);
		g.drawString("Puntaje: " + GJ.j.getPuntaje(), 0, 150);
//		g.dispose();
		g.drawImage(bi, 0, 0, Constantes.ANCHO_JUEGO, Constantes.ALTO_JUEGO, null);
	}
}
