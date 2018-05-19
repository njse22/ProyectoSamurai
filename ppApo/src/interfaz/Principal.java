package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.swing.*;

import hilos.HiloPersonajeDos;
import hilos.HiloPersonajeUno;
import hilos.HiloSaltoPersonajeUno;
import modelo.Jugador;
import modelo.Partida;
import modelo.Personaje;

public class Principal extends JFrame {
	
	public final static int PISO = 200;
	
	private Partida partida; 

	private JLabel fondo;
	
	private Etiqueta labPersonaje1; 
	private Etiqueta1 labPersonaje2;
	
	private URL urlLabel1;
	private URL urlLabel2;
	
	private Animacion animacion1 ; 
	private Animacion animacion2; 
	
	private Personaje personajeActual;
	private Personaje p1;
	private Personaje p2;
	
	private HiloPersonajeUno hilo1;
	private HiloPersonajeDos hilo2;
	private HiloSaltoPersonajeUno hilo3;
	
	private int estadoKey1;
	private int estadoKey2;
	
	/**
	 * variable de control que ayuda a cambiar las imagenes de los personajes. 
	 * */
	private int controlImagen1;
	private int controlImagenAtaqueDerecha1;
	private int controlImagenAtaqueIzquierda1;
	private int controlImagenAtaqueDerecha2;
	private int controlImagenAtaqueIzquierda2;
	private int controlImagen2;	
	private int controlImagenSalto;
	private int controlImagenSaltoBajar;
		
	private String[] keys1 = new String[4];
	private String[] keys2 = new String[4];
	
	private String keyActual;
	
	private DialogoInicio inicio;
	
	public Principal() {
		super(" Samurai Jack .VS. Mad Jack ");
		
		partida = new Partida(); 
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		Random rdn = new Random();
		int numFondo = rdn.nextInt(7) + 1;
		
	
		try {
						
			ImageIcon f = new ImageIcon("data/escenarios/"+numFondo+".gif");
			fondo = new JLabel();
			fondo.setIcon(f);
			
			urlLabel1 = new File("data/jackImg/blanco/derecha/1.png").toURI().toURL();
			labPersonaje1=new Etiqueta(urlLabel1);

			urlLabel2 = new File("data/jackImg/negro/izquierda/1.png").toURI().toURL();
			labPersonaje2=new Etiqueta1(urlLabel2);
		} 
	
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		setSize(new Dimension(768 , 336));
		
		getContentPane().add(labPersonaje2);
        labPersonaje2.setBounds(partida.getUltimo().getPosX(),partida.getUltimo().getPosY(), 51, 106);
//        labPersonaje2.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
	
        getContentPane().add(labPersonaje1);
        labPersonaje1.setBounds(partida.getPrimero().getPosX(), partida.getPrimero().getPosY(), 118, 122);
        
        getContentPane().add(fondo);
        fondo.setBounds(0, 0, 768, 336);
		
		inicio = new DialogoInicio(this);
		controlImagen1 = 1;
		controlImagenAtaqueDerecha1=1;
		controlImagenAtaqueIzquierda1=1;
		controlImagenAtaqueDerecha2=1;
		controlImagenAtaqueIzquierda2=1;
		controlImagen2 = 1;
	
		keysPersonajeUno();
		keysPersonaje2();	
		asignarMovimientos (); 
	
	}
	
	public int darEstadoKey1()
	{
		return estadoKey1;
	}
	
	public int darEstadoKey2()
	{
		return estadoKey2;
	}
		
	
	
	public Partida getPartida() {
		return partida;
	}
	
	public DialogoInicio getDialogoInicio() {
		return inicio;
	}

	public String[] darKeys1()
	{
		return keys1;
	}
	
	public String[] darKeys2()
	{
		return keys2;
	}
	
	/**
	 * este metodo define los movimientos de los personajes 
	 * */
	public void asignarMovimientos () {
		
		p1 = partida.getPrimero(); 
		p2 = partida.getUltimo();
	
		animacion1 = new Animacion(labPersonaje1, 24,this);
		animacion1.Accion(keysPersonajeUno()[0], -3,  0);
		animacion1.Accion(keysPersonajeUno()[1],  3,  0);
		animacion1.Accion(keysPersonajeUno()[2],  0, 0);
		animacion1.Accion(keysPersonajeUno()[3],  0, 0);
			
		animacion2 = new Animacion(labPersonaje2, 24,this);
		animacion2.Accion(keysPersonaje2()[0], -3,  0);
		animacion2.Accion(keysPersonaje2()[1],  3,  0);
		animacion2.Accion(keysPersonaje2()[2],  0, 0);
		animacion2.Accion(keysPersonaje2()[3],  0, 0);
	}
	
	public void determinarPersonaje(String key)
	{
		boolean terminar = false;
		
		keyActual = key;
		
		for(int i=0;i<darKeys1().length && !terminar;i++)
		{
			if(darKeys1()[i].equals(key))
			{
				personajeActual=p1;
				terminar = true;
			}			
		}
		
		if(terminar==false)
		{
			personajeActual=p2;
		}
	}
	
	public void asignarEstadoKey(int key, String k)
	{
		determinarPersonaje(k);
		
		if(personajeActual==p1)
		{
			estadoKey1=key;
		}
		else {
			estadoKey2=key;
		}
	}

	public int moverX(int deltaX, int deltaXdx) {
		int mover = 0;

		if (personajeActual == p1) 
			mover = partida.getPrimero().moverEnX(deltaX, deltaXdx);
		else 
			mover = partida.getUltimo().moverEnX(deltaX, deltaXdx);
		
		return mover ; 
				
	}
	
	public int moverY(int deltaY, int deltaYdy) {
		int mover = 0;

		if (personajeActual == p1) {
			mover = partida.getPrimero().moverEnY(deltaY, deltaYdy);
		} 
			
		else 
			mover = partida.getUltimo().moverEnY(deltaY, deltaYdy);
		
		return mover ; 
	}
		
	public String[] keysPersonajeUno() {
		
		keys1[0] = "A";
		keys1[1] = "D";
		keys1[2] = "C";
		keys1[3] = "V";
		
		
		return keys1;
	}

	public String[] keysPersonaje2() {
		
		keys2[0] = "LEFT";
		keys2[1] = "RIGHT";
		keys2[2] = "M";
		keys2[3] = "N";
		
		return keys2;
	}
	
	
	public boolean intersects1()
	{
		Area a = new Area(labPersonaje1.getBounds());
		Area b = new Area(labPersonaje2.getBounds());
		return a.intersects(b.getBounds2D());
	}
	
	public boolean intersects2()
	{
		Area a = new Area(labPersonaje2.getBounds());
		Area b = new Area(labPersonaje1.getBounds());
		return a.intersects(b.getBounds2D());
	}
	
	public void moverPersonaje()
	{
		if(personajeActual==p1)
		{
			hilo1 = new HiloPersonajeUno(this);
			hilo1.start();
			if(intersects1()==true)
			System.out.println(intersects1());
		}
		
		else
		{
			hilo2 = new HiloPersonajeDos(this);
			hilo2.start();
			if(intersects2()==true)
				System.out.println(intersects2());
		}
	}
		
//	public void validarMovimiento()
//	{
//		if(personajeActual == p1) {
//
//			if(keyActual.equals(keysPersonajeUno()[2]))
//			{
//				hilo3 = new HiloSaltoPersonajeUno(this,p1);
//				hilo3.start();
//			}
//		}
//		else {
//			if (keyActual.equals(keysPersonaje2()[2])) {
//				hilo3 = new HiloSaltoPersonajeUno(this,p2);
//				hilo3.start();
//			}
//		}
//		
//	}
	
	public void pintarImagenSalto()
	{
		try {
			urlLabel1= new File("data/jackImg/blanco/derecha/"+controlImagenSalto+".png").toURI().toURL();
			labPersonaje1.setUrl(urlLabel1);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		controlImagenSalto++; 
		
		if (controlImagenSalto > 3) {
			
			controlImagenSalto = 1; 
		}
	}
	
	
	public void pintarBajada()
	{
		try {
			urlLabel1= new File("data/jackImg/blanco/derecha/"+controlImagenSaltoBajar+".png").toURI().toURL();
			labPersonaje1.setUrl(urlLabel1);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		controlImagenSalto++; 
		
		if (controlImagenSalto > 6) {	
			controlImagenSalto = 4; 
		}
	}

	public void pintarImagen() {
		
		if(personajeActual==p1)
		{		
			if(keyActual.equals(keysPersonajeUno()[1]))
			{
				try {
					urlLabel1= new File("data/jackImg/blanco/derecha/"+controlImagen1+".png").toURI().toURL();
					labPersonaje1.setUrl(urlLabel1);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				controlImagen1++; 
			
				if (controlImagen1 > 6) {
					
					controlImagen1 = 1; 
				}
				
			}
			else if(keyActual.equals(keysPersonajeUno()[0]))
			{	
				try {
					urlLabel1= new File("data/jackImg/blanco/izquierda/"+controlImagen1+".png").toURI().toURL();
					labPersonaje1.setUrl(urlLabel1);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				controlImagen1++; 
				if (controlImagen1 > 6) {
					controlImagen1 = 1; 
				}
			}
			
			else if(keyActual.equals(keysPersonajeUno()[2]))
			{
				try {
					urlLabel1= new File("data/jackImg/blanco/ataque_izquierda/"+controlImagenAtaqueIzquierda1+".png").toURI().toURL();
					labPersonaje1.setUrl(urlLabel1);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				controlImagenAtaqueIzquierda1++; 
				if (controlImagenAtaqueIzquierda1 > 13) {
					controlImagenAtaqueIzquierda1 = 1; 
				}
			}
			
			else if(keyActual.equals(keysPersonajeUno()[3]))
			{
				try {
					urlLabel1= new File("data/jackImg/blanco/ataque_derecha/"+controlImagenAtaqueDerecha1+".png").toURI().toURL();
					labPersonaje1.setUrl(urlLabel1);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				controlImagenAtaqueDerecha1++; 
				if (controlImagenAtaqueDerecha1 > 13) {
					controlImagenAtaqueDerecha1 = 1; 
				}
			}
			
		}
		else
		{
			if(keyActual.equals(keysPersonaje2()[1]))
			{		
				try {
					urlLabel2= new File("data/jackImg/negro/derecha/"+controlImagen2+".png").toURI().toURL();
					labPersonaje2.setUrl(urlLabel2);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				controlImagen2++; 
				if (controlImagen2 > 6) {
					controlImagen2 = 1; 
				}
			}
			else if(keyActual.equals(keysPersonaje2()[0]))
			{
				try {
					urlLabel2= new File("data/jackImg/negro/izquierda/"+controlImagen2+".png").toURI().toURL();
					labPersonaje2.setUrl(urlLabel2);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

				controlImagen2++; 
				if (controlImagen2 > 6) {
					controlImagen2 = 1; 
				}
			}
			
			else if(keyActual.equals(keysPersonaje2()[2]))
			{
				try {
					urlLabel2= new File("data/jackImg/negro/ataque_derecha/"+controlImagenAtaqueDerecha2+".png").toURI().toURL();
					labPersonaje2.setUrl(urlLabel2);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				controlImagenAtaqueDerecha2++; 
				if (controlImagenAtaqueDerecha2 > 13) {
					controlImagenAtaqueDerecha2 = 1; 
				}
			}
			
			else if(keyActual.equals(keysPersonaje2()[3]))
			{
				try {
					urlLabel2= new File("data/jackImg/negro/ataque_izquierda/"+controlImagenAtaqueIzquierda2+".png").toURI().toURL();
					labPersonaje2.setUrl(urlLabel2);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				controlImagenAtaqueIzquierda2++; 
				if (controlImagenAtaqueIzquierda2 > 13) {
					controlImagenAtaqueIzquierda2 = 1; 
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		Principal p = new Principal();
		

		DialogoInicio comenzar = p.getDialogoInicio();
		
		comenzar.setVisible(true);

	}
	
}