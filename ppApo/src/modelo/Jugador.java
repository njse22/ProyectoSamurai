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
	 * compareTo(Jugador jugador): m�todo implementado de la interfaz Compareble
	 * compara dos jugadores y dice quien es "mayor" a travez del criterio lexico
	 * @param jugador : Jugador que llega por parametro y sera comparado con aquel que llama al m�todo
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
	public boolean agregarJugador(Jugador nuevo) {
		if(this.compareTo(nuevo) > 0) {
			if(izquierda == null) {
				izquierda = nuevo;
				//rotacionIzquierda(izquierda);
				return true;
			}
				
			else {
				izquierda.agregarJugador(nuevo);
//				rotacionDobleIzquierda(izquierda);
				return false;
			}
				
		}
		else
			if(derecha == null) {
				derecha = nuevo;
//				rotacionDerecha(derecha);
				return true;
			} 
				
			else {
				derecha.agregarJugador(nuevo);
//				rotacionDobleDerecha(derecha);
				return false ;
			}
				 
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
		else {
			if(derecha == null)
				throw new JugadorNoEncontradoException(nickName);
			else
				return derecha.buscar(nickName);
		}
		
	}	
	
	/**
	 * eliminar(Jugador eliminar) : Jugador 
	 * elimina un jugador del ABB de jugadores 
	 * @param eliminar : Jugador - el jugador a eliminar de la estructura
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
	
	
	public int calcularAltura() {
		
			if ( derecha == null && izquierda == null )
			return 1;
			else {
				int a1 ;
				int a2 ;
				if ( izquierda == null )
					a1 = 0 ;
				else
					a1 = izquierda.calcularAltura() ;
				if ( derecha == null)
					a2 = 0 ;
				else
					a2 = derecha.calcularAltura();
				return 1 + Math . max ( a1 , a2 ) ;
			}
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
		
	
	
	
}
