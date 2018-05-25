package hilos;

import interfaz.DialogoJuego;

public class HiloPersonajeDos extends Thread{


	
	private DialogoJuego principal;

	private boolean mover = true;
	
	
	public HiloPersonajeDos(DialogoJuego principal) {
		this.principal = principal;
		
	}
	
	public void run () {

		while(mover)
		{
			try {
				principal.pintarImagen();
				Thread.sleep(59);
				mover = false ;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
}
