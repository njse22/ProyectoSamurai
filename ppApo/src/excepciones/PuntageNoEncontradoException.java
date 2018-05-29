package excepciones;

/**
 * clase que representa una excepcion relacionada con el puntaje buscado
 */
public class PuntageNoEncontradoException extends Exception{

/**
 * Atributo que representa una cadena especificando la excepcion
 */
	private String puntajeNoEncontrado;
/**
 * Contructor de la clase 
 * @param puntageNoEncontrado cadena especificando la excepcion
 */
	public PuntageNoEncontradoException(String puntageNoEncontrado) {
		this.puntajeNoEncontrado = puntageNoEncontrado;
		
	}
	
	
	
}
