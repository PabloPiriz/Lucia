package principal.sprites;

public class Animacion {

	// public static final Animacion ANIMACION1 = new
	// Animacion(HojaSprites.TEST1, .08f, true,false);
	// public static final Animacion ANIMACION2 = new
	// Animacion(HojaSprites.TEST2, 3, 0, 13, (byte)1, .1f, true,false);
	// public static final Animacion ANIMACION3 = new
	// Animacion(HojaSprites.TEST3, .1f, true,true);

	public static final byte STATE_ATRAS = -1;
	public static final byte STATE_PAUSA = 0;
	public static final byte STATE_ADELANTE = 1;

	public final int START_SPRITE = 0;

	byte state = 0; // Indica el estado actual de la animacion (-1)<- |0| ->(1)

	volatile AnimacionEvent listeners[] = null;
	volatile Sprite[] animacion; // Coleccion de sprites que conforman la
									// animacion
	boolean repeat;
	boolean start;
	boolean fin;
	boolean rebobinar; // Indica si al final de la animacion la misma se
						// reproduce a la inversa
	float delayActualizacion; // Lapso de tiempo entre un sprite y otro(ej: 2.0
								// = 2s)
	float delayActual;
	volatile int spriteActual; // Indice del sprite actual
	int cambios = 0;

	/**
	 * @param animacion
	 *            - Array de Sprites
	 * @param delay
	 *            - Tiempo entre un sprite y otro (en segundos)
	 * @param repeat
	 *            - Repetible
	 * @param rebobinar
	 *            - Indica si la animacion se reproduce a la inversa al terminar
	 */

	public Animacion(final Sprite[] animacion, final float delay, final boolean repeat, final boolean rebobinable) {
		this.animacion = animacion;
		this.delayActualizacion = delay;
		this.repeat = repeat;
		this.spriteActual = START_SPRITE;
		this.fin = false;
		this.delayActual = 0;
		this.rebobinar = rebobinable;
	}

	/**
	 * Cargar todos los sprites de la hoja, incluido los vacios
	 * 
	 * @param source
	 *            - HojaSprites
	 * @param delay
	 *            - Tiempo entre un sprite y otro (en segundos)
	 * @param repeat
	 *            - Indica si se repite o no
	 * @param rebobinar
	 *            - Indica si la animacion se reproduce a la inversa al terminar
	 */
	public Animacion(final HojaSprites source, final float delay, final boolean repeat, final boolean rebobinable) {
		int columns = source.getColumns();
		int rows = source.getRows();
		Sprite animacion[] = new Sprite[columns * rows];
		int count = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				animacion[count] = new Sprite(j, i, source);
				count++;
			}
		}
		this.animacion = animacion;
		this.delayActualizacion = delay;
		this.repeat = repeat;
		this.spriteActual = START_SPRITE;
		this.fin = false;
		this.delayActual = 0;
		this.rebobinar = rebobinable;
	}

	/**
	 * Carga una secuencia de sprites de una HojaSprite, la secuencia tiene que
	 * encontrarse ordenada en una fila o columna
	 * 
	 * @param source
	 *            - HojaSprite
	 * @param columna
	 *            - Columna del sprite inicial
	 * @param fila
	 *            - Fila del sprite inicial
	 * @param cant
	 *            - Cantidad de sprites con los que cuenta
	 * @param type
	 *            - 0 Horizontal | 1 Vertical (Cualquier numero diferente de 0 o
	 *            1 dara como resultado Vertical)
	 * @param delay
	 *            - Tiempo entre un sprite y otro (en segundos)
	 * @param repeat
	 *            - Indica si se repite o no
	 * @param rebobinar
	 *            - Indica si la animacion se reproduce a la inversa al terminar
	 */
	public Animacion(final HojaSprites source, final int columna, final int fila, final int cant, final byte type,
			final float delay, final boolean repeat, final boolean rebobinable) {
		Sprite animacion[] = new Sprite[cant];
		if (type == 0) {
			for (int i = 0; i < cant; i++) {
				animacion[i] = new Sprite(columna + i, fila, source);
			}
		} else {
			for (int i = 0; i < cant; i++) {
				animacion[i] = new Sprite(columna, fila + i, source);
			}
		}
		this.animacion = animacion;
		this.delayActualizacion = delay;
		this.repeat = repeat;
		this.spriteActual = START_SPRITE;
		this.fin = false;
		this.delayActual = 0;
		this.rebobinar = rebobinable;
	}

	/**
	 * Actualiza la animacion con respecto al tiempo dado
	 * 
	 * @param delta
	 *            - En segundos
	 */
	public void Actualizar(final float delta) {
		if (start) {
			delayActual += delta;
			while (delayActual >= delayActualizacion) {
				delayActual -= delayActualizacion;
				if (rebobinar) {
					if (state == STATE_ADELANTE)
						NextSprite();
					else if (state == STATE_ATRAS)
						previousSprite();
					else
						;
				} else {
					NextSprite();
				}
			}
		}
	}

	public void start() {
		if (!start) {
			delayActual = 0;
			start = true;
			fin = false;
			spriteActual = START_SPRITE;
			state = STATE_ADELANTE;
			EventStartAnimacion();
		}
	}

	public void stop() {
		if (start) {
			delayActual = 0;
			start = false;
			fin = true;
			// spriteActual = START_SPRITE;
			// state = STATE_ADELANTE;
		}
	}

	/**
	 * Agrega un Sprite a la lista
	 * 
	 * @param elem
	 */
	public void AgregarAnimacion(final Sprite elem) {
		Sprite[] flag = animacion.clone();
		animacion = new Sprite[animacion.length + 1];
		for (int i = 0; i < flag.length; i++) {
			animacion[i] = flag[i];
		}
		animacion[flag.length] = elem;
	}

	/**
	 * Pasa al siguiente sprite
	 */
	public void NextSprite() {
		boolean changeSprite = false;
		if (!fin) {
			if (spriteActual >= animacion.length - 1 && !rebobinar) {
				fin = true;
			} else if (spriteActual >= animacion.length - 1 && rebobinar) {
				state = STATE_ATRAS;
			} else {
				spriteActual++;
				changeSprite = true;
			}
		}
		if (repeat && fin && !rebobinar) {
			fin = false;
			spriteActual = 0;
			EventReplayAnimacion();
			changeSprite = true;
		}

		if (fin) {
			EventEndAnimacion();
		}
		if (changeSprite) {
			EventChangeSprite();
		}
	}

	/**
	 * Pasa al Sprite anterior
	 */
	public void previousSprite() {
		if (spriteActual <= 0 && !repeat) {
			spriteActual = animacion.length - 1;
			EventEndAnimacion();
		} else if (spriteActual > 0)
			spriteActual--;
		else if (spriteActual <= 0 && repeat)
			state = STATE_ADELANTE;
		EventChangeSprite();
	}

	public void EventEndAnimacion() {
		if (listeners != null) {
			for (AnimacionEvent elem : listeners) {
				elem.EventEndAnimacion();
			}
		}
	}

	public void EventStartAnimacion() {
		if (listeners != null) {
			for (AnimacionEvent elem : listeners) {
				elem.EventStartAnimacion();
			}
		}
	}

	public void EventReplayAnimacion() {
		if (listeners != null) {
			for (AnimacionEvent elem : listeners) {
				elem.EventReplayAnimacion();
			}
		}
	}

	public void EventChangeSprite() {
		if (listeners != null) {
			for (AnimacionEvent elem : listeners) {
				elem.EventChangeSprite();
			}
		}
	}

	public void rebobinar() {
		rebobinar = true;
		state = STATE_ATRAS;
	}

	/**
	 * Obtiene el Sprite actual
	 * 
	 * @return Sprite
	 */
	public Sprite getSpriteActual() {
		return animacion[spriteActual];
	}

	public boolean isFin() {
		return fin;
	}

	/**
	 * Agrega un listener a la animacion
	 * 
	 * @param elem
	 *            - AnimacionEvent
	 */
	public void addListener(AnimacionEvent elem) {
		if (listeners == null) {
			listeners = new AnimacionEvent[1];
			listeners[0] = elem;
		} else {
			AnimacionEvent flag[] = listeners.clone();
			listeners = new AnimacionEvent[flag.length + 1];
			for (int i = 0; i < flag.length; i++) {
				listeners[i] = flag[i];
			}
			listeners[flag.length] = elem;
		}
	}

	public interface AnimacionEvent {
		public void EventEndAnimacion();

		public void EventStartAnimacion();

		public void EventReplayAnimacion();

		public void EventChangeSprite();
	}

}