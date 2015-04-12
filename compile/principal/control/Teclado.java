package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import principal.maquinaestado.GestorEstados;

public class Teclado implements KeyListener {

	public Tecla arriba = new Tecla();
	public Tecla abajo = new Tecla();
	public Tecla izquierda = new Tecla();
	public Tecla derecha = new Tecla();

	public boolean corriendo = false;

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			arriba.teclaPulsada();
			break;
		case KeyEvent.VK_S:
			abajo.teclaPulsada();
			break;
		case KeyEvent.VK_A:
			izquierda.teclaPulsada();
			break;
		case KeyEvent.VK_D:
			derecha.teclaPulsada();
			break;
		case KeyEvent.VK_SHIFT:
			corriendo = true;
			break;
		case KeyEvent.VK_ESCAPE:
			if (GestorEstados.getInstance().getEstadoActual() == GestorEstados.ESTADO_JUEGO)
				GestorEstados.getInstance().cambiarEstadoActual(GestorEstados.ESTADO_MENU_PRINCIPAL);
			else if(GestorEstados.getInstance().getEstadoActual() == GestorEstados.ESTADO_MENU_PRINCIPAL)System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			arriba.teclaLiberada();
			break;
		case KeyEvent.VK_S:
			abajo.teclaLiberada();
			break;
		case KeyEvent.VK_A:
			izquierda.teclaLiberada();
			break;
		case KeyEvent.VK_D:
			derecha.teclaLiberada();
			break;
		case KeyEvent.VK_SHIFT:
			corriendo = false;
			break;
		}
	}

	public void keyTyped(KeyEvent arg0) {
	}
}