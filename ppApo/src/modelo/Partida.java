package modelo;

//import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
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
	
	
	private int numJugadores ;
	 
	/**
	 * constructor de la clase inicializa una partida.
	 * */
	public Partida() {
		this.numJugadores = 0;
		this.primero = new Personaje(1000, true, 80 , 220, 50, 50, Personaje.PRIMER_PERSONAJE);
		this.ultimo  = new Personaje(1000, true, 600, 220, 50, 50, Personaje.SEGUNDO_PERSONAJE);
 
	}

	public int getNumJugadores() {
		return numJugadores;
	}

	public void setNumJugadores(int numJugadores) {
		this.numJugadores = numJugadores;
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
			if (raiz == null) {
				raiz = nuevo;
				numJugadores++;
			}
				
			else {
				if( raiz.agregarJugador(nuevo) )
					numJugadores++;
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
 
	public ArrayList<String> crearListaMejoresPuntajes(ArrayList<Jugador> aOrdenar){
		
		ArrayList<String> mejoresPuntajes = new ArrayList<String>(); 
		Jugador auxPrimero = raiz;
		
		
		while(auxPrimero != null) {
			
			if ( true ) {
				
			}
			
//			mejoresPuntajes.add();
			
			
		}
		
		
		
		return null; 
	}

	//*************************************************************************************
	
	/* METODOS PARA VALANCEAR UN ABB (HAY QUE MOVERLOS A LA CLASE JUGADOR HE INSERTARLOS 
	 * EN LOS METODOS DE AGREGAR SEGUN SEA EL CASO CORRESPONDIENTE ... REVISAR EL LIBRO 
	 * ESTRUCTURAS DE DATOS ARBOLES AVL CAPITULO 19.4 EN LA PAGINA 730 DEL PDF )
	 * */
	
	// caso 1 del valanseo 
	public Jugador rotacionIzquierda(Jugador j2 ) {
		
		Jugador j1 = j2.getIzquierda();
		
		j2.setIzquierda(j1.getDerecha());
		j1.setDerecha(j2);
		
		return j1;
	}
	

	public Jugador rotacionDerecha(Jugador j1) {
		
		Jugador j2 = j1.getDerecha();
		
		j1.setDerecha(j2.getIzquierda());
		j2.setIzquierda(j1);
		return j2;
		
	}
	
	
	//caso 3 del valanseo
	public Jugador rotacionDobleDerecha(Jugador j3) {
		
		j3.setDerecha(rotacionDerecha(j3.getDerecha()));
		return rotacionDerecha(j3); 
		
	}
	
	
	
	public Jugador rotacionDobleIzquierda(Jugador j3) {
		
		j3.setIzquierda(rotacionIzquierda(j3.getIzquierda()));
		
		return rotacionIzquierda(j3);  
	}
	
	
	public int calcularAltura() {
		if (raiz == null) {
			return 0;
		}
		else 
			return raiz.calcularAltura();
		
	}
	
	//************************************************************************************
	
	public ArrayList<Jugador> inOrden(Jugador nodo){
		
		ArrayList<Jugador> jugadoresInorden = new ArrayList<Jugador>(); 
		
		Jugador auxPrimero = nodo; 
		jugadoresInorden.add(auxPrimero);
		
		for (int i = 0; i < 9 && auxPrimero != null ; i++) {
			
			if (auxPrimero.getIzquierda() != null)
				auxPrimero = auxPrimero.getIzquierda();
			
			jugadoresInorden.add(auxPrimero);
			
			if(auxPrimero.getDerecha() != null)
				auxPrimero = auxPrimero.getDerecha(); 
			
		}
		
		return jugadoresInorden; 
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
