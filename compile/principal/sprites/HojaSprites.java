package principal.sprites;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import principal.herramientas.CargadorRecursos;

public class HojaSprites {
	//Hojas de Sprites que siempre estan cargadas

	
	String direccion;			//Path del archivo
	int width, height;			//En pixeles
	BufferedImage bi;
	int columns, rows;			//Cantidad de columnas y filas de la hoja
	int widthOfCell;
	int heightOfCell;
	int transparencia;
	
	
	public HojaSprites(final String ruta,final int width, final int height, final int columnas, final int filas,boolean opaca){
		this.direccion = ruta;
		this.width = width;
		this.height = height;
		this.columns = columnas;
		this.rows = filas;
		this.widthOfCell = width / columnas;
		this.heightOfCell = height / filas;
		if (opaca) transparencia = Transparency.OPAQUE;
		else  transparencia = Transparency.TRANSLUCENT;
		this.bi = CargadorRecursos.cargarImagenCompatible(ruta,transparencia);
	}
	
	public synchronized BufferedImage getSprite(final int column, final int row){
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		BufferedImage flag = gc.createCompatibleImage(widthOfCell, heightOfCell,transparencia);
		Image im = bi.getSubimage(column * widthOfCell, row * heightOfCell, widthOfCell, heightOfCell);
		Graphics g = flag.getGraphics();
		g.drawImage(im, 0, 0, null);
		g.dispose();
		return flag;
	}
	
	public synchronized BufferedImage getSprite(final int relativeX, final int relativeY, final int width, final int height){
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		BufferedImage flag = gc.createCompatibleImage(width, height,transparencia);
		Image im = bi.getSubimage(relativeX,relativeY, width, height);
		Graphics g = flag.getGraphics();
		g.drawImage(im, 0, 0, null);
		g.dispose();
		return flag;
	}
	
	public int getColumns() {
		return columns;
	}
	public int getRows() {
		return rows;
	}
	public int getWidthOfCell() {
		return widthOfCell;
	}
	public int getHeightOfCell() {
		return heightOfCell;
	}
}
