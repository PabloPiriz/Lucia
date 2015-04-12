package principal.maquinaestado.estados.menuinicial;

import java.awt.Graphics;

import principal.Constantes;
import principal.graficos.GestorGraficos;
import principal.maquinaestado.EstadoJuego;
import principal.maquinaestado.GestorEstados;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;
import principal.swing.Boton;
import principal.swing.Componente;
import principal.swing.EventComponente;
import principal.swing.Menu;

public class GestorMenuInicial implements EstadoJuego, EventComponente{
	private final int BOTON_NUEVA_PARTIDA = 0;
	
	private Menu menuPrincipal;
	private HojaSprites hs;
	
	
	
	public GestorMenuInicial() {
		init();
	}
	
	private void init(){
		hs = new HojaSprites(Constantes.RUTA_MENU1_BOTONES, 471, 200, 1, 2, false);
		menuPrincipal = new Menu(200,200,Constantes.ANCHO_JUEGO-400,Constantes.ALTO_JUEGO-400,Constantes.RUTA_MENU1_FONDO);
		Boton b = new Boton(BOTON_NUEVA_PARTIDA,new Sprite(0,0,hs),new Sprite(0,1,hs));
		b.setLocalizacion(0, 0,250, 100);
		b.addEvent(this);
		menuPrincipal.agregarComponente(b);
		b.centrarX();
		b.centrarY();
		GestorGraficos.getSd().addMouseListener(menuPrincipal);
		GestorGraficos.getSd().addKeyListener(menuPrincipal);
		GestorGraficos.getSd().addMouseMotionListener(menuPrincipal);
	}

	@Override
	public void actualizar(float delta) {
		menuPrincipal.actualizar(delta);
	}

	@Override
	public void dibujar(Graphics g) {
		menuPrincipal.dibujar(g);
	}
	
	@Override
	public void EventClicked(Componente c) {
		switch (c.getId()) {
		case BOTON_NUEVA_PARTIDA:
				GestorEstados.getInstance().cambiarEstadoActual(GestorEstados.ESTADO_JUEGO);
			break;

		default:
			break;
		}
	}

}
