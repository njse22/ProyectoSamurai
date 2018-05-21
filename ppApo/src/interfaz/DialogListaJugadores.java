package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import excepciones.JugadorNoEncontradoException;
import modelo.Jugador;

public class DialogListaJugadores extends JDialog implements ActionListener{

	public final static String ORDENAR = "ORDENAR";
	public final static String BUSCAR = "BUSCAR";
	public final static String REGRESAR = "REGRESAR";
	
	private JButton btnOrdenar;
	private JButton btnBuscar;
	private JButton btnRegresar;
	
	private DefaultListModel<String> model;
	private JList listaJugadores;
	private JScrollPane scroll; 
	
	private JPanel panelLista;
	
	private DialogoInicio dialogo;

	public DialogListaJugadores(DialogoInicio dialogo) {
		this.dialogo = dialogo;
		
		panelLista = new JPanel();
				
		panelLista.setBorder(new TitledBorder(" Jugadores "));

		panelLista.setLayout(new GridLayout(2, 1));

		btnOrdenar = new JButton("ORDENAR");
		btnBuscar = new JButton("BUSCAR");
		btnRegresar = new JButton("REGRESAR");
		
		model = new DefaultListModel<String>();
		listaJugadores = new JList(model);
		scroll = new JScrollPane(listaJugadores);
		
		btnOrdenar.setActionCommand(ORDENAR);
		btnBuscar.setActionCommand(BUSCAR);
		btnRegresar.setActionCommand(REGRESAR);
		
		btnOrdenar.addActionListener(this);
		btnBuscar.addActionListener(this);
		btnRegresar.addActionListener(this);
		
		JPanel auxpanelOpcion = new JPanel();
		auxpanelOpcion.add(btnOrdenar);
		auxpanelOpcion.add(btnBuscar);
		auxpanelOpcion.add(btnRegresar);
		
		JPanel auxPanelLista = new JPanel();
		listaJugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaJugadores.setPreferredSize(new Dimension(300, 300));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		auxPanelLista.add(scroll);
		
		setLocationRelativeTo( null );
		setSize(325, 200);
		
		refrescarLista(dialogo.getPrincipal().getPartida().getRaiz());
		
		add(auxPanelLista, BorderLayout.NORTH);
		add(auxpanelOpcion, BorderLayout.SOUTH);
	
	}

	public DefaultListModel<String> getModel() {
		return model;
	}

	public void setModel(DefaultListModel<String> model) {
		this.model = model;
	}
	
	
	public void refrescarLista(Jugador nodo) {
//		model.removeAllElements();
		try {
			dialogo.getPrincipal().getPartida().recuperarInformacion();

			if(nodo != null) {
				refrescarLista(nodo.getIzquierda());
				model.addElement(nodo.getNickname());
				refrescarLista(nodo.getDerecha());
			}
		
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog( this, e.getMessage() ," ERROR... al cargar la lista ",JOptionPane.ERROR_MESSAGE );
		}catch (IOException e) {
			JOptionPane.showMessageDialog( this, e.getMessage() ," ERROR... al cargar la lista ",JOptionPane.ERROR_MESSAGE );
		}catch (Exception e) {
			JOptionPane.showMessageDialog( this, e.getMessage() ," ERROR... al cargar la lista ",JOptionPane.ERROR_MESSAGE );
		}

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(REGRESAR.equals(e.getActionCommand())) {
			dialogo.setVisible(true);
			dispose();
		}
		else if (BUSCAR.equals(e.getActionCommand())) {
			
			try {		
//				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font( "Arial", Font.ROMAN_BASELINE, 18)));       
//				JOptionPane.showMessageDialog(null,"MESSAGE","ERROR",JOptionPane.WARNING_MESSAGE);

				String nickName = JOptionPane.showInputDialog("Ingrese el nombre del jugador que desea Buscar: ");
				Jugador encontrado = dialogo.getPrincipal().getPartida().buscarJugador(nickName);
				JOptionPane.showMessageDialog( this, encontrado.toString() ," Información del jugador: ", JOptionPane.INFORMATION_MESSAGE );
				
			}catch (JugadorNoEncontradoException e1) {
				JOptionPane.showMessageDialog( this, "El juagdor: " + e1.getJugadorNoEncontrado() + " no ha sido encontrado "," ERROR... ",JOptionPane.ERROR_MESSAGE );
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
