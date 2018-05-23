package modelo;

import java.io.Serializable;

import org.junit.platform.commons.util.ToStringBuilder;

import excepciones.JugadorNoEncontradoException;

/**
 * clase que representa al jugador de la partida 
 * */

public class Jugador implements Serializable , Comparable<Jugador> {

	/** 
	 * nombre con el cual ingresa el jugador al juego.
	 * */
	private String nickname; 
	/**
	 * puntaje que acumula el jugador al ganar un apartida.
	 * */
	private long puntaje;
	/**
	 * define si el jugador le corresponde el pirmer o segundo personaje.
	 * */
	private String idPersonajeAsociado; 
	
	/**
	 * derecha: representa a un jugador que es mayor que la raiz.
	 * */
	private Jugador derecha;
	/**
	 * izquierda: representa a un jugador que es menor que la raiz.
	 * */
	private Jugador izquierda;
	
	/**
	 * constructor de la clase inicializa los atributos del jugador. 
	 * */
	public Jugador(String nickname, long puntaje, String idPersonajeAsociado) {
		this.nickname = nickname;
		this.puntaje = puntaje;
		this.idPersonajeAsociado = idPersonajeAsociado; 
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public long getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(long puntaje) {
		this.puntaje = puntaje;
	} 
	
	public String getIdPersonajeAsociado() {
		return idPersonajeAsociado;
	}

	public void setIdPersonajeAsociado(String idPersonajeAsociado) {
		this.idPersonajeAsociado = idPersonajeAsociado;
	}

	public Jugador getDerecha() {
		return derecha;
	}

	public void setDerecha(Jugador derecha) {
		this.derecha = derecha;
	}

	public Jugador getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(Jugador izquierda) {
		this.izquierda = izquierda;
	}

	/**
	 * compareTo(Jugador jugador): método implementado de la interfaz Compareble
	 * compara dos jugadores y dice quien es "mayor" a travez del criterio lexico
	 * @param jugador : Jugador que llega por parametro y sera comparado con aquel que llama al método
	 * @return 0 = si los jugadores son iguales 
	 * 		  -1 = si el jugador que llega por parametro es mayor 
	 * 		   1 = si el jugador que llega por parametro es menor.
	 * */
	@Override
	public int compareTo(Jugador jugador) {
		return nickname.compareToIgnoreCase(jugador.getNickname());
	}

	/**
	 * darMayor() : retorna al mayor de los jugadores del arbol.
	 * @return el mayor jugador del arbol.
	 * */
	public Jugador darMayor() {
		if(derecha == null)
			return this;
		else
			return derecha.darMayor();
		
	}
	
	/**
	 * darMenor() : retorna al menor de los jugadores del arbol.
	 * @return el monor jugador del arbol.
	 * */
	public Jugador darMenor() {
		if(izquierda == null)
			return this;
		else 
			return izquierda.darMenor();
	}
	
	/**
	 * agregarJugador(Jugador nuevo):void = agrega un  nuevo jugador al ABB segun el 
	 * criterio de ordenamiento
	 * @param nuevo : Jugador
	 * */
	public void agregarJugador(Jugador nuevo) {
		if(this.compareTo(nuevo) > 0) {
			if(izquierda == null)
				izquierda = nuevo;
			else
				izquierda.agregarJugador(nuevo);
		}
		else
			if(derecha == null) 
				derecha = nuevo;
			else 
				derecha.agregarJugador(nuevo); 
		}

	/**
	 * buscar(String nickName) : Jugador  
	 * busca y retorna al jugador a travez de su nnombre 
	 * @param nickName : String - nombre del jugador pasado por parametro 
	 * @return el jugador que se esta buscdando 
	 * @throws JugadorNoEncontradoException : se lanza esta excepcion cuando el jugador 
	 * que se esta buscando no existe 
	 * */
	public Jugador buscar(String nickName) throws JugadorNoEncontradoException {
		
		if( nickname.compareToIgnoreCase(nickName) == 0)  {
			return this;
		}
		else if (nickName.compareToIgnoreCase(nickName) < 0 ) {
			if(izquierda == null)
				throw new JugadorNoEncontradoException(nickName);
			else
				return izquierda.buscar(nickName);
		}
		else 
			if(derecha == null)
				throw new JugadorNoEncontradoException(nickName);
			else
				return derecha.buscar(nickName);
	}	
	
	/**
	 * eliminar(Jugador eliminar) : Jugador 
	 * */
	public Jugador eliminar(Jugador eliminar) {
		if(izquierda == null && derecha == null) {
			return null;
		}	
		if(this.compareTo(eliminar) == 0) {
			if(izquierda == null)
				return derecha;
			if(derecha == null)
				return izquierda;
		
			Jugador sucesor = derecha.darMenor();
			derecha.eliminar(sucesor);
			sucesor.izquierda = izquierda;
			sucesor.derecha = derecha;
			return sucesor;
			
		}
		else if(this.compareTo(eliminar) > 0)
			izquierda = izquierda.eliminar(eliminar);
		else 
			derecha = derecha.eliminar(eliminar);
		return this;
	}
	
	/**
	 * toString : String metodo sobreescrito de la clase Odject  
	 * @return String con la infomacion basica del jugador
	 * */
	@Override
	public String toString() {
		return "nombre: "+ this.getNickname() + "\n" + 
			   "puntage: "+ this.getPuntaje () ;
	}
	
	
	
}

