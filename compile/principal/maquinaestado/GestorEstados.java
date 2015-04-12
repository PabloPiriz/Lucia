package principal.maquinaestado;

import java.awt.Graphics;

import principal.maquinaestado.estados.juego.GestorJuego;
import principal.maquinaestado.estados.menuinicial.GestorMenuInicial;

public class GestorEstados {
	public final static int ESTADO_MENU_PRINCIPAL = 0;
	public final static int ESTADO_JUEGO = 1;
	
	private final int CANT_ESTADOS = 2;
	private static GestorEstados ge = null;
	
	private EstadoJuego[] estados;
	private EstadoJuego estadoActual;
	private int nroEstadoActual = 0;
	

	public GestorEstados() {
		ge = this;
		iniciarEstados();
		iniciarEstadoActual();
	}

	private void iniciarEstados() {
		estados = new EstadoJuego[CANT_ESTADOS];
		estados[0] = new GestorMenuInicial();
		estados[1] = new GestorJuego();
		// Añadir e iniciar los demás estados a medida que los creemos
	}

	private void iniciarEstadoActual() {
		estadoActual = estados[ESTADO_MENU_PRINCIPAL];
		nroEstadoActual = ESTADO_MENU_PRINCIPAL;
	}

	public void actualizar(final float delta) {
		estadoActual.actualizar(delta);
	}

	public void dibujar(final Graphics g) {
		estadoActual.dibujar(g);
	}

	public void cambiarEstadoActual(final int nuevoEstado) {
		estadoActual = estados[nuevoEstado];
		nroEstadoActual = nuevoEstado;
	}

	public EstadoJuego obtenerEstadoActual() {
		return estadoActual;
	}
	
	public static GestorEstados getInstance(){
		return ge;
	}
	public int getEstadoActual() {
		return nroEstadoActual;
	}
}