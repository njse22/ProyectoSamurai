package modelo;

//import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import excepciones.JugadorNoEncontradoException;

/**
 * claase que representa una partida del juego donde se enfrentaran dos jugadores.
 * */
public class Partida {

	/**
	 * constante que representa la cantidad de personajes que hay en el arreglo. 
	 * */
//	public final static int CANT_PERSONAJES = 2; 
	
	/**
	 * ArrayList donde se encuentran todos los jugadores que ingresen al juego.
	 * */
	
	/**
	 * arreglo donde se encuentra los dos personajes del juego. 
	 * */
	private Personaje primero; 
	private Personaje ultimo;
	
	private Jugador raiz;
	
	private int numJugadores;
	 
	/**
	 * constructor de la clase inicializa una partida.
	 * */
	public Partida() {
		
		this.primero = new Personaje(1000, true, 80 , 220, 50, 50, Personaje.PRIMER_PERSONAJE);
		this.ultimo  = new Personaje(1000, true, 600, 220, 50, 50, Personaje.SEGUNDO_PERSONAJE);
 
	}

	public Personaje getPrimero() {
		return primero;
	}

	public Personaje getUltimo() {
		return ultimo;
	}
	
	public void setPrimero(Personaje primero) {
		this.primero = primero;
	}
	
	public void setUltimo(Personaje ultimo) {
		this.ultimo = ultimo;
	}
	
	public Jugador getRaiz() {
		return raiz;
	}

	public void setraiz(Jugador raiz) {
		this.raiz = raiz;
	}
	
	public Jugador darMayor() {
		if(raiz == null)
			return null;
		else 
			return raiz.darMayor();
	}
	
	public Jugador darMenor() {
		if (raiz == null)
			return null;
		else 
			return raiz.darMenor();
	}
	
	
	public void agregar(String nickName) {
		
		if (!existe(nickName)) {
			Jugador nuevo = new Jugador (nickName, 0, "");
			if (raiz == null)
				raiz = nuevo;
			else {
				raiz.agregarJugador(nuevo);
			} 	
		}
		else {
			//sin saber que hacer en esta parte ... si el jugador existe lo logico seria 
			//cambiar sus atributos a los que tenia antes de iniciar una nueva partida.
		}
	
	}

	
	public boolean existe(String nickName) {
		boolean existe = false ;
		
		try {
			if (buscarJugador(nickName) != null) {
				existe = true;
			}
		}catch (JugadorNoEncontradoException e) {
			e.getMessage();
		}
		if (existe) {
			System.out.println("el personaje existe...");
		}
		
		return existe;
		
	}
	
	
//	public boolean validarExistente(String nickName) {
//		
//		if(raiz == null) 
//			return false;
//		else 
//			return raiz.validarExistente(nickName);
//		
//	}
	
	public Jugador buscarJugador(String nickName) throws JugadorNoEncontradoException{
		
		if(raiz == null)
			throw new JugadorNoEncontradoException(nickName);
		else
			return raiz.buscar(nickName);
		
	}
	
	public Jugador eliminar(Jugador eliminar) {
		return raiz.eliminar(eliminar);
	}
	
	public void inOrden(Jugador nodo) {

		if(nodo != null) {
			inOrden(nodo.getIzquierda());
			System.out.println(nodo.getNickname());
			inOrden(nodo.getDerecha());
		}
	}
	
 
	/*
	 * serializarArchivos() : void
	 * este metodo serializa a los jugadores 
	 * @throws I
	 * */
	
	public void serializarArchivos() throws IOException , FileNotFoundException{
		
		FileOutputStream fos = new FileOutputStream("data/serializacion/jugadores.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		try {
			oos.writeObject(raiz);
		}catch (FileNotFoundException e) {
			e.getMessage();
		}catch (IOException e) {
			e.getMessage();
		}
		
		finally {
			
			try {
				if (fos != null) {
					fos.close();
				}
				if(oos != null) {
					oos.close();
				}
				
			}catch (IOException e) {
				e.getMessage();
			}	
		}
	}
	
	public void recuperarInformacion() throws FileNotFoundException, IOException {
		
		FileInputStream fis = new FileInputStream("data/serializacion/jugadores.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Jugador nuevaRaiz = (Jugador)ois.readObject();
			raiz = nuevaRaiz;
			
			
		}catch (Exception e) {
			e.getMessage();
		}
		finally {
			if(fis != null) {
				fis.close();
			}
			if(ois != null) {
				ois.close();
			}
		}
		
		
	}
	
}
