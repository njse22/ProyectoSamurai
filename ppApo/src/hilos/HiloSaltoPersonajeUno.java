package hilos;

import interfaz.Principal;
import modelo.Personaje;

public class HiloSaltoPersonajeUno extends Thread{

	private Principal principal;
	private Personaje personaje;

	private boolean mover = true;
	
	private int total;

	
	public HiloSaltoPersonajeUno(Principal principal, Personaje personaje)
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
