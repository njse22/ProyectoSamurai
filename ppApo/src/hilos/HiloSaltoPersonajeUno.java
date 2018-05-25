package hilos;

import interfaz.DialogoJuego;
import modelo.Personaje;

public class HiloSaltoPersonajeUno extends Thread{

	private DialogoJuego principal;
	private Personaje personaje;

	private boolean mover = true;
	
	private int total;

	
	public HiloSaltoPersonajeUno(DialogoJuego principal, Personaje personaje)
	{
		this.principal=principal;
		this.personaje = personaje;
		
		total=6;
	}
	
	public void run() {
		
		while (mover) {
			try {

			

				sleep(60);
			}catch (Exception e) {
				// TODO: handle exception
			}
		}

		

	}
	
}
