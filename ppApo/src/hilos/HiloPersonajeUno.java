package hilos;

import interfaz.DialogoJuego;

public class HiloPersonajeUno extends Thread{

	
	private DialogoJuego principal;

	private boolean mover = true;
	
	
	public HiloPersonajeUno(DialogoJuego principal) {
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
	
	public synchronized void parar() {
//		System.out.println("algo");
		mover = false ;
	}
	
	
}
