package modelo;

import java.io.Serializable;

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

	public Jugador darMayor() {
		if(derecha == null)
			return this;
		else
			return derecha.darMayor();
		
	}
	
	public Jugador darMenor() {
		if(izquierda == null)
			return this;
		else 
			return izquierda.darMenor();
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
	
//	public boolean validarExistente(String nickName) {
//
//		if( nickname.compareTo(nickName) == 0)  {
//			return true;
//		}
//		else if (nickName.compareTo(nickName) < 0 ) {
//			if(izquierda == null)
//				return false;
//			else
//				return izquierda.validarExistente(nickName);
//		}
//		else {
//			if(derecha == null)
//				return false ;
//			else
//				return derecha.validarExistente(nickName);
//		}
//		
//	}
	
	
	
	public String toString() {
		return "nombre: "+ this.getNickname() + "\n" + 
			   "puntage: "+ this.getPuntaje () ;
	}
	
	
	
}

