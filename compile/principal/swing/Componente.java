package principal.swing;

import java.awt.Graphics;
import java.awt.Point;

import principal.log.Log;

public abstract class Componente implements Event {
	protected int id;
	protected int x,y,width,height;
	protected Menu menu;
	
	public boolean sobreElComponente(Point p){
		return p.x > x + menu.getX() && p.x < x + menu.getX() + width && p.y > y + menu.getY() && p.y < y + menu.getY() + height;
	}
	
	public void dibujar(Graphics g){
		
	}
	
	public void addMenu(Menu menu){
		this.menu = menu;
	}
	
	public void setLocalizacion(final int x,final int y,final int width,final int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void centrarX(){
		if (menu != null){
			x = (menu.getWidth()/2) - width/2;
		}
	}
	
	public void centrarY(){
		if (menu != null){
			y = (menu.getHeight()/2) - height/2;
		}
	}
	
	public int getId() {
		return id;
	}
}
