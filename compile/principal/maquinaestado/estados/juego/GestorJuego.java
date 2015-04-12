package principal.maquinaestado.estados.juego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.entidad.Jugador;
import principal.herramientas.CargadorRecursos;
import principal.mapa.Mapa;
import principal.mapa.TestMapa;
import principal.maquinaestado.EstadoJuego;

public class GestorJuego implements EstadoJuego {

//	Mapa mapa = new Mapa(Constantes.RUTA_MAPA);
	static TestMapa mapaActual = new TestMapa(1L);
	Interfaz interfaz;
	Jugador j = new Jugador();
	BufferedImage logo = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_LOGOTIPO);

	public GestorJuego() {
		interfaz = new Interfaz(this);
		mapaActual.agregarObjeto(j);
	}
	
	public void actualizar(final float delta) {
		mapaActual.actualizar(delta);
		interfaz.actualizar(delta);
	}

	public void dibujar(Graphics g) {
		mapaActual.dibujar(g);
		interfaz.dibujar(g);
//		mapa.dibujar(g, (int) jugador.obtenerPosicionX(), (int) jugador.obtenerPosicionY());
//		jugador.dibujar(g);
//		g.drawImage(logo, 640 - logo.getWidth() - 5, 360 - logo.getHeight() - 5, null);
//
//		g.setColor(Color.red);
//		g.drawString("X = " + jugador.obtenerPosicionX(), 20, 20);
//		g.drawString("Y = " + jugador.obtenerPosicionY(), 20, 30);
//
//		InterfazUsuario.dibujarBarraResistencia(g, jugador.resistencia);
	}
	
	public static TestMapa getMapaActual() {
		return mapaActual;
	}
}