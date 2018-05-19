package modelo;

//import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
	
	public void agregar(String nickName) {
		Jugador nuevo = new Jugador (nickName, 0, "");

		if (raiz == null)
			raiz = nuevo;
		else {
			raiz.agregarJugador(nuevo);
		} 	
			
	}

	public Jugador buscarJugador(String nickName){
		
		if(raiz == null)
			return null;
		else
			return raiz.buscar(nickName);
		
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
