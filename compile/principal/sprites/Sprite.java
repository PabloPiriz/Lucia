package principal.sprites;

import java.awt.image.BufferedImage;

public class Sprite {

	
	int width,height;
	int column, row;
	BufferedImage bi;
	HojaSprites source;			//Hoja de la cual se obtine el sprite
	
	
	public Sprite(final int column, final int row, final HojaSprites source){
		this.width = source.getWidthOfCell();
		this.height = source.getHeightOfCell();
		this.source = source;
		this.column = column;
		this.row = row;
		this.bi = source.getSprite(column, row);
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public BufferedImage getImagen() {
		return bi;
	}
}
