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

	public final static String ORDENAR_POR_NOMBRE = "ORDENAR_POR_NOMBRE";
	public final static String ORDENAR_POR_PUNTAGE = "ORDENAR_POR_PUNTAGE";
	public final static String BUSCAR = "BUSCAR";
	public final static String REGRESAR = "REGRESAR";
	public final static String ELIMINAR = "ELIMINAR";
	
	private JButton btnOrdenarNombre;
	private JButton btnOrdenarPuntage;
	private JButton btnBuscar;
	private JButton btnRegresar;
	private JButton btnEliminar;
	
	private DefaultListModel<Jugador> model;

	private JList<Jugador> listaJugadores;

	private JScrollPane scroll; 
	
	private JPanel panelLista;
	
	private Principal dialogo;

	public DialogListaJugadores(Principal dialogo) {
		this.dialogo = dialogo;
		
		panelLista = new JPanel();
				
		panelLista.setBorder(new TitledBorder(" Jugadores "));

		panelLista.setLayout(new GridLayout(2, 1));

		btnOrdenarNombre = new JButton("ORDENAR NOMBRE");
		btnOrdenarPuntage = new JButton("ORDENAR PUNTAGE");
		btnBuscar = new JButton("BUSCAR");
		btnRegresar = new JButton("REGRESAR");
		btnEliminar = new JButton("ELIMINAR");
		
		model = new DefaultListModel<Jugador>();
		listaJugadores = new JList<>(model);
		scroll = new JScrollPane(listaJugadores);
		
		btnOrdenarNombre.setActionCommand(ORDENAR_POR_NOMBRE);
		btnOrdenarPuntage.setActionCommand(ORDENAR_POR_PUNTAGE);
		btnBuscar.setActionCommand(BUSCAR);
		btnRegresar.setActionCommand(REGRESAR);
		btnEliminar.setActionCommand(ELIMINAR);
		
		btnOrdenarNombre.addActionListener(this);
		btnOrdenarPuntage.addActionListener(this);
		btnBuscar.addActionListener(this);
		btnRegresar.addActionListener(this);
		btnEliminar.addActionListener(this);
		
		JPanel auxpanelOpcion = new JPanel();
		auxpanelOpcion.add(btnOrdenarNombre);
		auxpanelOpcion.add(btnOrdenarPuntage);
		auxpanelOpcion.add(btnBuscar);
		auxpanelOpcion.add(btnRegresar);
		auxpanelOpcion.add(btnEliminar);
		
		JPanel auxPanelLista = new JPanel();
		listaJugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaJugadores.setPreferredSize(new Dimension(400, 300));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		auxPanelLista.add(scroll);
		
		setLocationRelativeTo( null );
		setSize(450, 200);
		
		refrescarLista(dialogo.getPrincipal().getPartida().getRaiz());
		
		add(auxPanelLista, BorderLayout.NORTH);
		add(auxpanelOpcion, BorderLayout.SOUTH);
	
	}

	public DefaultListModel<Jugador> getModel() {
		return model;
	}

	public void setModel(DefaultListModel<Jugador> model) {
		this.model = model;
	}
	
	
	public void limpiarLista() {
		model.removeAllElements();
	}
	
	public void refrescarLista() {

		try {
			if (dialogo.getPrincipal().getPartida().getRaiz() != null)
				dialogo.getPrincipal().getPartida().inOrden( dialogo.getPrincipal().getPartida().getRaiz() );
				
			for (int i = 0; i < dialogo.getPrincipal().getPartida().getMejoresPuntajes().size(); i++) {
				model.addElement(dialogo.getPrincipal().getPartida().getMejoresPuntajes().get(i));
			}
			
			dialogo.getPrincipal().getPartida().recuperarInformacion();
			
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog( this, e.getMessage() ," ERROR... al cargar la lista ",JOptionPane.ERROR_MESSAGE );
		}catch (IOException e) {
			JOptionPane.showMessageDialog( this, e.getMessage() ," ERROR... al cargar la lista ",JOptionPane.ERROR_MESSAGE );
		}catch (Exception e) {
			JOptionPane.showMessageDialog( this, e.getMessage() ," ERROR... al cargar la lista ",JOptionPane.ERROR_MESSAGE );
		}	
	}
	
	
	public void refrescarLista(Jugador nodo) {
//		model.removeAllElements();
		try {
			dialogo.getPrincipal().getPartida().recuperarInformacion();

			if(nodo != null) {
				refrescarLista(nodo.getIzquierda());
				model.addElement(nodo);
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
	
	public void removeAllElementsOfModel() {
		model.removeAllElements();
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
		else if (ELIMINAR.equals(e.getActionCommand())) {
			try {

				String nickName = JOptionPane.showInputDialog("Ingrese el nombre del jugador que desea Eliminar: ");
				Jugador encontrado = dialogo.getPrincipal().getPartida().buscarJugador(nickName);
				dialogo.getPrincipal().getPartida().eliminar(encontrado);
//				limpiarLista();
				model.removeAllElements();
				refrescarLista();
//				refrescarLista(dialogo.getPrincipal().getPartida().getRaiz());
				
			}catch (JugadorNoEncontradoException e1) {
				JOptionPane.showMessageDialog( this, "El juagdor: " + e1.getJugadorNoEncontrado() + " no ha sido encontrado "," ERROR... ",JOptionPane.ERROR_MESSAGE );
			}	
		}
		
		else if (ORDENAR_POR_NOMBRE.equals(e.getActionCommand())) {
			
			
			
			
		}
		else if(ORDENAR_POR_PUNTAGE.equals(e.getActionCommand())) {
			
			dialogo.getPrincipal().getPartida().ordenarPuntagesPorBurbuja();
			model.removeAllElements();
			refrescarLista();
			
			
			
		} 
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
