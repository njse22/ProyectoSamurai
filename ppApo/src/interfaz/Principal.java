package interfaz;

import java.awt.Container;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//import javax.jws.soap.SOAPBinding.Style;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.Style;

import excepciones.JugadorExistenteException;
import excepciones.JugadorNoEncontradoException;

public class Principal extends JFrame  implements ActionListener{

	public final static String MOSTRAR_LISTA_JUGADORES = "JUGADORES"; 
	public final static String COMENZAR = "COMENZAR";
	
	
	private JButton btnListJugodor , btnListPuntage, btnComenzar, btnAgregar; 
	
	private JPanel panelOpciones;

	private DialogListaJugadores panelJugador;
	
	private DialogoJuego principal;

	private int contadorDeAperturas;

	public Principal(DialogoJuego principal) {
		super();
		this.principal = principal;
	
		panelJugador = new DialogListaJugadores(this);
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		contadorDeAperturas = 0;
				
		setSize(608, 400);
		setLocationRelativeTo( null );	
		
		iniciarOpciones();

		
	}
	
	public void crearPanelJuego() {
		JLabel imagen = new JLabel();
		ImageIcon icono = new ImageIcon("data/imgExt/portada2.jpg");
		imagen = new JLabel("");
		imagen.setIcon(icono);
		this.add(imagen); 
	}
	 
	
	
//	public void paint(Graphics g) {
//		super.paint(g);
//		
//		ImageIcon fondo = new ImageIcon("data/imgExt/portada2.jpg");
//		
//		g.drawImage(fondo.getImage(), 0, 0, null);
//		
//	}
	
	public void iniciarOpciones() {
		
		panelOpciones = new JPanel();
	
		 
		
		btnComenzar = new JButton("COMENZAR");
		btnComenzar.setActionCommand(COMENZAR); 
		btnComenzar.addActionListener(this); 
		
		btnListJugodor = new JButton("Mostrar Lista De Jugadores");
		btnListJugodor.setActionCommand(MOSTRAR_LISTA_JUGADORES); 
		btnListJugodor.addActionListener(this); 
		
//		btnListPuntage = new JButton("Mostras Mejores Puntages");
//		btnListPuntage.addActionListener(this); 
//		
//		btnAgregar = new JButton("AGREGAR");
//		btnAgregar.addActionListener(this);
		
		panelOpciones.setLayout(null);
		btnComenzar.setBounds(10, 300, 120, 25);
		btnListJugodor.setBounds(10, 325, 250, 25);
//		btnListPuntage.setBounds(10, 350, 250, 25);
//		btnAgregar.setBounds(0, 0, 120, 25);
		
		panelOpciones.add(btnComenzar);
		panelOpciones.add(btnListJugodor);
//		panelOpciones.add(btnListPuntage);
//		panelOpciones.add(btnAgregar);
		
		crearPanelJuego();
		
		add(panelOpciones);
		
		
		
	}
	
	
	public DialogoJuego getPrincipal() {
		return principal;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(COMENZAR.equals(e.getActionCommand())){
			
			try {
				String nickName = JOptionPane.showInputDialog("Nombre Jugador 1");
				principal.getPartida().agregar(nickName);
				String nickName2 = JOptionPane.showInputDialog("Nombre Jugador 2");
				principal.getPartida().agregar(nickName2);
				principal.getPartida().serializarArchivos();
				principal.setVisible(true);	
				dispose();
				
			}catch (IOException e1) {
				// TODO: handle exception
			} catch (JugadorNoEncontradoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JugadorExistenteException e1) {
				
				e1.printStackTrace();
			}
		}
		
		else if (MOSTRAR_LISTA_JUGADORES.equals(e.getActionCommand())) {	
			panelJugador.setVisible(true);
			panelJugador.removeAllElementsOfModel();
			panelJugador.refrescarLista(principal.getPartida().getRaiz());
			dispose();
		}	
		
		
		
	}

	
	
}
