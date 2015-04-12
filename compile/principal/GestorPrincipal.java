package principal;

import principal.graficos.GestorGraficos;
import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.log.Log;
import principal.maquinaestado.GestorEstados;

public class GestorPrincipal {
	private boolean enFuncionamiento = false;
	private int ancho;
	private int alto;

	private Log log;
	private GestorEstados ge;

	private static int fps = 0;
	private static int aps = 0;

	private GestorPrincipal( final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;
	}

	public static void main(String[] args) {
		GestorPrincipal gp = new GestorPrincipal(Constantes.ANCHO_PANTALLA_COMPLETA,
				Constantes.ALTO_PANTALLA_COMPLETA);

		gp.iniciarJuego();
		gp.iniciarBuclePrincipal();
	}

	private void iniciarJuego() {
		enFuncionamiento = true;
		inicializar();
	}

	private void inicializar() {
		log = new Log();
		ge = new GestorEstados();
	}

	private void iniciarBuclePrincipal() {
		int actualizacionesAcumuladas = 0;
		int framesAcumulados = 0;

		final int NS_POR_SEGUNDO = 1000000000;
		final int APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0;

		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime();

			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;

			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

			while (delta >= 1) {
				actualizar(1.0f / APS_OBJETIVO);
				actualizacionesAcumuladas++;
				delta--;
			}

			dibujar();
			framesAcumulados++;

			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {

				aps = actualizacionesAcumuladas;
				fps = framesAcumulados;

				actualizacionesAcumuladas = 0;
				framesAcumulados = 0;
				referenciaContador = System.nanoTime();
			}
		}
	}

	private void actualizar(final float delta) {
		ge.actualizar(delta);
		log.actualizar();
	}

	private void dibujar() {
		GestorGraficos.getSd().dibujar(ge);
	}

	public static int obtenerFPS() {
		return fps;
	}

	public static int obtenerAPS() {
		return aps;
	}
}
