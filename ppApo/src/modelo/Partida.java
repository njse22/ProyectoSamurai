package modelo;

//import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import excepciones.JugadorExistenteException;
import excepciones.JugadorNoEncontradoException;

/**
 * claase que representa una partida del juego donde se enfrentaran dos jugadores.
 * */
public class Partida {

	/**
	 * primero : Personaje, atributo que representa al primero de la lsita de jugadores  
	 * */
	private Personaje primero; 
	/**
	 * ultimo : Personaje, atributo que representa al ultimo de la lsita de jugadores
	 * */
	private Personaje ultimo;
	
	/**
	 * raiz : Jugador, atributo que represneta a la raiz del ABB de jugadores
	 * */
	private Jugador raiz;
	 
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
	
	/**
	 * darMayor() : Jugador - busca y retorna el mayor jugador del ABB de jugadores
	 * @return el mayor jugador del ABB
	 * */
	public Jugador darMayor() {
		if(raiz == null)
			return null;
		else 
			return raiz.darMayor();
	}
	
	/**
	 * darMenor() : retorna al menor de los jugadores del arbol.
	 * @return el monor jugador del arbol.
	 * */
	public Jugador darMenor() {
		if (raiz == null)
			return null;
		else 
			return raiz.darMenor();
	}
	
	/**
	 * agregar(String nickName) : void 
	 * agrega un nuevo jugador al ABB de jugadores segun elo criterio de ordenamiento correspondiente 
	 * valida primero si el jugador exite en caso de que no lanza la Excepci�n JugadorExistenteException 
	 * @param nickName : String - representa el nombre del jugador del juagador que se quiere agregar.
	 * @throws JugadorNoEncontradoException 
	 * @throws JugadorExistenteException 
	 * */
	public void agregar(String nickName) throws JugadorNoEncontradoException, JugadorExistenteException {
		
		if (!existe(nickName)) {
			Jugador nuevo = new Jugador (nickName, 0, "");
			if (raiz == null)
				raiz = nuevo;
			else {
				raiz.agregarJugador(nuevo);
			} 	
		}// fin del if 
		else {
			try {
				Jugador encontrado = buscarJugador(nickName);
				encontrado.setPuntaje(encontrado.getPuntaje());				
			}catch (JugadorNoEncontradoException e) {
				e.getMessage();
			}
			
			throw new JugadorExistenteException(nickName);

		}//fin del else
	
	}// fin del metodo

	/**
	 * existe(String nickName) : boolean - busca un jugador deacuerdo a su nombre 
	 * @return true = si el jugador buscado por nombre existe 
	 * 		   false = si el jugador buscado por nombre NO existe
	 * */
	public boolean existe(String nickName) {
		boolean existe = false ;
		
		try {
			if (buscarJugador(nickName) != null) {
				existe = true;
			}
		}catch (JugadorNoEncontradoException e) {
			e.getMessage();
		}

		return existe;
		
	}
	
	/**
	 * buscarJugador(String nickName) : Jugador 
	 * busca un jugador con su nombre pasado por parametro
	 * si no es encontrado lanza la excepci�n  
	 * @param nickName : String = nombre del jugador a buscar
	 * pre: nombre != null , nombre != " "
	 * @return el jugador que ha sido buscado a traves del nombre
	 * @throws JugadorNoEncontradoException  
	 * */
	public Jugador buscarJugador(String nickName) throws JugadorNoEncontradoException{
		if(raiz == null)
			throw new JugadorNoEncontradoException(nickName);
		else
			return raiz.buscar(nickName);
		
	}
	
	/**
	 * eliminar(Jugador eliminar) : Jugador 
	 * */
	public Jugador eliminar(Jugador eliminar) {
		return raiz.eliminar(eliminar);
	}
 
	/**
	 * serializarArchivos() : void
	 * este metodo serializa a los jugadores 
	 * @throws IOException
	 * @throws FileNotFoundException
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
	
	/**
	 * recuperarInformacion() : void 
	 * recupera la informacion guardada a tarvez de la persistencia. 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * */
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
