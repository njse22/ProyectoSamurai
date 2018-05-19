package interfaz;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DialogoInicio extends JDialog  implements ActionListener{

	public final static String MOSTRAR_LISTA_JUGADORES = "JUGADORES",  MOSTRAR_LISTA_PUNTAGES = "MEJOR PUNTAGE", COMENZAR = "COMENZAR", AGREGAR_NUEVO_JUGADOR = "AGREGAR"; 

	private JButton btnListJugodor , btnListPuntage, btnComenzar, btnAgregar; 
	
	private JPanel panelOpciones;

	private DialogListaJugadores panelJugador;
	private DialogoPuntages puntages;
	
	private Principal principal;

	public DialogoInicio(Principal principal) {
		super();
		this.principal = principal;
	
		panelJugador = new DialogListaJugadores(this);
		puntages = new DialogoPuntages(this);
		
		setSize(608, 400);
		setLocationRelativeTo( null );	
		
		iniciarOpciones();

	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		ImageIcon fondo = new ImageIcon("data/imgExt/portada2.jpg");
		
		g.drawImage(fondo.getImage(), 0, 0, null);
		
	}
	
	public void iniciarOpciones() {
		
		panelOpciones = new JPanel();
	
		btnComenzar = new JButton("COMENZAR");
		btnComenzar.setActionCommand(COMENZAR); 
		btnComenzar.addActionListener(this); 
		
		btnListJugodor = new JButton("Mostrat Lista De jugadores");
		btnListJugodor.setActionCommand(MOSTRAR_LISTA_JUGADORES); 
		btnListJugodor.addActionListener(this); 
		
		btnListPuntage = new JButton("Mostras Mejores Puntages");
		btnListPuntage.setActionCommand(MOSTRAR_LISTA_PUNTAGES); 
		btnListPuntage.addActionListener(this); 
		
		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setActionCommand(AGREGAR_NUEVO_JUGADOR);
		btnAgregar.addActionListener(this);
		
		panelOpciones.setLayout(null);
		btnComenzar.setBounds(10, 300, 120, 25);
		btnListJugodor.setBounds(10, 325, 250, 25);
		btnListPuntage.setBounds(10, 350, 250, 25);
		btnAgregar.setBounds(0, 0, 120, 25);
		
		panelOpciones.add(btnComenzar);
		panelOpciones.add(btnListJugodor);
		panelOpciones.add(btnListPuntage);
//		panelOpciones.add(btnAgregar);
		
		add(panelOpciones);
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
			}
		}
		
		else if (MOSTRAR_LISTA_JUGADORES.equals(e.getActionCommand())) {	
			panelJugador.setVisible(true);
			dispose();
			
		}	
		
		else if(MOSTRAR_LISTA_PUNTAGES.equals(e.getActionCommand())) {
			puntages.setVisible(true);
			dispose();
			
		}
		
//		else if (AGREGAR_NUEVO_JUGADOR.equals(e.getActionCommand())) {
//
//			
//			
//		}
//		
//		
	}
	
}
