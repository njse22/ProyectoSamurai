package interfaz;

import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * EtiquetaImagen: esta clase almacena o dibuja a cada uno de los personajes del juego 
 * */
public class EtiquetaImagen extends JLabel{

	/**
	 * icono : ImageIcon - atributo que tiene la imagen del personaje.
	 * */
	private ImageIcon icono;
	/**
	 * la siguiente EtiquetaImagen de la lista
	 * */
	private EtiquetaImagen siguiente;
	/**
	 * la anterior EtiquetaImaegn de la lista
	 * */
	private EtiquetaImagen anterrior;
	
	/**
	 * constructor de la clase inicializa cada objeto de la lista
	 * @param direccion : URL - la direccion de la imagen 
	 * */
	public EtiquetaImagen(URL direccion) {
		
		super();
		
		icono = new ImageIcon(direccion);
	}
	
	public void setUrl(URL nueva)
	{
		icono = new ImageIcon(nueva);
	}
	
//	@Override
	public void paint(Graphics g)
	{
//		super.paint(g);	
		g.drawImage(icono.getImage(),0,0,51,106,null);
	}
	
	
	
	
}
