package principal.herramientas;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class Util {

	private static GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDefaultConfiguration();
	
	public static BufferedImage crearImagenCompatible(final int width, final int height, final boolean opaca){
		int transparency;
		if (opaca) transparency = Transparency.OPAQUE;
		else transparency = Transparency.TRANSLUCENT;
		BufferedImage imagenAcelerada = gc.createCompatibleImage(width, height,	transparency);
		return imagenAcelerada;
	}
	
}
