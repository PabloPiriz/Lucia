package principal.mapa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import principal.Constantes;
import principal.entidad.Moneda;
import principal.entidad.Objeto;
import principal.herramientas.CargadorRecursos;
import principal.log.Log;

public class TestMapa {

	int width = Constantes.ANCHO_JUEGO, height = Constantes.ALTO_JUEGO;
	BufferedImage fondo = CargadorRecursos.cargarImagenCompatibleOpaca(Constantes.RUTA_MAPA1_FONDO1);
	ArrayList<Objeto> objetos = new ArrayList<Objeto>();
	Iterator<Objeto> itActualizar;
	long seed = 1L;
	Random aleatorio;

	public TestMapa(final long seed) {
		this.seed = seed;
		aleatorio = new Random(this.seed);
	}

	public void agregarObjeto(final Objeto obj) {
		objetos.add(obj);
		obj.setId(objetos.size());
	}

	public void actualizar(final float delta) {
			testGenerarMonedasAleatorias(delta);
			itActualizar = objetos.iterator();
			while (itActualizar.hasNext()) {
				Objeto o = itActualizar.next();
				o.actualizar(delta);
				if (o.isRemover())itActualizar.remove();
			}
			// Algoritmo para la deteccion de colisiones
			ArrayList<Objeto> obj = (ArrayList<Objeto>) objetos.clone();
			for (int i = 0; i < obj.size(); i++) {
				for (int j = 0; j < obj.size() - i; j++) {
					if (i != j + i){
						Objeto o1 = obj.get(i);
						Objeto o2 = obj.get(i + j);
						if (o1.getColisionCube().intersects(o2.getColisionCube())) {
							o1.colision(o2);
							o2.colision(o1);
						}
					}
				}
			}
	}

	private int delayMaxMonedas = 2;
	private int delayMinMonedas = 1;
	private float delayMonedas;
	private float delayActualMonedas = 0;

	public void testGenerarMonedasAleatorias(final double delta) {
		if (delayActualMonedas <= 0) {
			delayMonedas = aleatorio.nextInt(delayMaxMonedas - delayMinMonedas) + delayMinMonedas;
			delayActualMonedas = delayMonedas;
		}
		delayActualMonedas -= delta;
		if (delayActualMonedas <= 0) {
			int y = aleatorio.nextInt(height - Moneda.HEIGHT);
			agregarObjeto(new Moneda(width, y));
		}
	}

	public void dibujar(Graphics g) {
		g.drawImage(fondo, 0, 0, width, height, null);
		Iterator<Objeto> it = objetos.iterator();
		while (it.hasNext()) {
				it.next().dibujar(g);
		}
	}

	/**
	 * Verifica si el rectangulo se encuentra dentro del mapa
	 * 
	 * @param r
	 *            - Rectangle a evaluar
	 * @return boolean[4] || N,S,E,W || false = no colision
	 */
	public boolean[] enMapa(final Rectangle r) {
		boolean flag[] = new boolean[4]; // N,S,E,W
		if (r.y <= 0)
			flag[0] = true;
		if (r.y + r.height >= height)
			flag[1] = true;
		if (r.x + r.width >= width)
			flag[2] = true;
		if (r.x <= 0)
			flag[3] = true;
		return flag;
	}

	public Rectangle getColisionCube() {
		return new Rectangle(0, 0, width, height);
	}
}
