package principal.graficos;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import principal.Constantes;
import principal.herramientas.CargadorRecursos;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 5979421777239930009L;
	
	private final ImageIcon icono;

	public Ventana(final SuperficieDibujo sd) {

		BufferedImage imagen = CargadorRecursos.cargarImagenCompatibleTranslucida(Constantes.RUTA_ICONO_VENTANA);
		this.icono = new ImageIcon(imagen);

		configurarVentana(sd);
	}

	private void configurarVentana(final SuperficieDibujo sd) {
		setTitle(Constantes.NOMBRE_JUEGO);
		setIconImage(icono.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		// setIconImage();
		setLayout(new BorderLayout());
		add(sd, BorderLayout.CENTER);
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}