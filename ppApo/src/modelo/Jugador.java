package modelo;

import java.io.Serializable;

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
	 * define si el jugador escojio el pirmer o segundo personaje.
	 * */
	private String idPersonajeAsociado; 
	
	
	private Jugador derecha;
	private Jugador izquierda;
	
//	private Jugador padre;
	
	
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

	@Override
	public int compareTo(Jugador jugador) {
		return nickname.compareToIgnoreCase(jugador.getNickname());
	}

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

	public Jugador buscar(String nickName) {
		
		if( nickname.compareTo(nickName) == 0)  {
			return this;
		}
		else if (nickName.compareTo(nickName) < 0 ) {
			if(izquierda == null)
				return null;
			else
				return izquierda.buscar(nickName);
		}
		else 
			if(derecha == null)
				return null;
			else
				return derecha.buscar(nickName);

	}	

	
	
	
	
}

