package principal.mapa;

import principal.Constantes;
import principal.sprites.Sprite;

public class Mapa {
	
	private int id, width, height;
	private String nombre;
	private Sprite obj[];
	private String data;
	
	private Mapa(final int id, final int width, final int height, final String nombre) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.nombre = nombre;
	}
	
	public Mapa(final String data){
		String []datos = data.split(Constantes.MAPA_SEPARAR_LINEA);
	}
	
	/* 1- Nombre del Mapa [Luna sangrienta]
	 * 2- Width,height [8,8]
	 * 3- Posicion inicial del jugador [0,0]
	 * 4- Hojas de sprites utilizadas [1,2,3,4,5]
	 * 5- Sprites utilizados de las hojas [Sprite,Hoja] [1-1,2-1,5-2] (sprite 1 y 2 de la hoja 1, y sprite 5 de la hoja 2)
	 * 5- Los Sprites se les da un numero comenzando por 0 [el sprite 1-1 tiene un identificador de 0, el 2-1 id 1, 5-2 id 2]
	 * 5- Se utilizan en pasos posteriores
	 * 6- Mascara de solidos 0 = no solido - 1 = solido, tamaño de la linea = Width(liena 2), Cantidad de lineas = Height(linea 2)
	 * 6- [11111111
	 * 6-  10000001
	 * 6-  10000001
	 * 6-  10000001
	 * 6-  10000001
	 * 6-  10000001
	 * 6-  10000001
	 * 6-  11111111]
	 * 7- Id 
	 */
}
