package principal.graficos;

import principal.Constantes;

public class GestorGraficos {
	private static SuperficieDibujo sd = new SuperficieDibujo(Constantes.ANCHO_PANTALLA_COMPLETA,
			Constantes.ALTO_PANTALLA_COMPLETA);
	private static Ventana ventana = new Ventana(getSd());

	public static SuperficieDibujo getSd() {
		return sd;
	}

}
