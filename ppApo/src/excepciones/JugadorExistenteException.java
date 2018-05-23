package excepciones;

public class JugadorExistenteException extends Exception {
	
	private String juagdorExistente;

	public JugadorExistenteException(String juagdorExistente) {
		this.juagdorExistente = juagdorExistente;
	
	}

	public String getJuagdorExistente() {
		return juagdorExistente;
	}


}
