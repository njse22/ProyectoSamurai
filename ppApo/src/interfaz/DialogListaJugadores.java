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

/**
 * Clase que permite ordenar y buscar entre una lista de jugadores
 * @author CAMILO
 *
 */
public class DialogListaJugadores extends JDialog implements ActionListener{
	
	/**
	 * Constante de boton ordenar
	 */

	public final static String ORDENAR = "ORDENAR";
	
	/**
	 * Constante de boton buscar
	 */
	public final static String BUSCAR = "BUSCAR";
	
	/**
	 * Constante de boton regresar
	 */
	public final static String REGRESAR = "REGRESAR";
	
	/**
	 * Constante de boton eliminar
	 */
	public final static String ELIMINAR = "ELIMINAR";
	
	
	/**
	 * atributo para el boton ordenar
	 */
	private JButton btnOrdenar;
	
	/**
	 * atributo para el boton buscar
	 */
	private JButton btnBuscar;
	
	/**
	 * atributo para el boton regresar
	 */
	private JButton btnRegresar;
	
	/**
	 * atributo para el boton eliminar
	 */
	private JButton btnEliminar;
	
	/**
	 * atributo para agregar objetos de tipo String a la lista
	 */
	private DefaultListModel<String> model;
	
	/**
	 * Atributo de tipo lista en el que se incluyen los nombres de los jugadores
	 */
	private JList<String> listaJugadores;
	
	/**
	 * atributo para moverse en la lista de jugadores
	 */
	private JScrollPane scroll; 
	
	/**
	 * panel contenedor de la lista
	 */
	private JPanel panelLista;
	
	
	/*
	 * relacion con la clase Principal
	 */
	private Principal dialogo;

	/**
	 * Constructor de la clase
	 * @param dialogo
	 */
	public DialogListaJugadores(Principal dialogo) {
		this.dialogo = dialogo;
		
		panelLista = new JPanel();
				
		panelLista.setBorder(new TitledBorder(" Jugadores "));

		panelLista.setLayout(new GridLayout(2, 1));

		btnOrdenar = new JButton("ORDENAR");
		btnBuscar = new JButton("BUSCAR");
		btnRegresar = new JButton("REGRESAR");
		btnEliminar = new JButton("ELIMINAR");
		
		model = new DefaultListModel<String>();
		listaJugadores = new JList<>(model);
		scroll = new JScrollPane(listaJugadores);
		
		btnOrdenar.setActionCommand(ORDENAR);
		btnBuscar.setActionCommand(BUSCAR);
		btnRegresar.setActionCommand(REGRESAR);
		btnEliminar.setActionCommand(ELIMINAR);
		
		btnOrdenar.addActionListener(this);
		btnBuscar.addActionListener(this);
		btnRegresar.addActionListener(this);
		btnEliminar.addActionListener(this);
		
		JPanel auxpanelOpcion = new JPanel();
		auxpanelOpcion.add(btnOrdenar);
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

	/**
	 * metodo que permite obtener un modelo
	 * @return
	 */
	public DefaultListModel<String> getModel() {
		return model;
	}

	/**
	 * metodo que permite reasignar un modelo
	 * @param model
	 */
	public void setModel(DefaultListModel<String> model) {
		this.model = model;
	}
	
	/**
	 * metodo para remover los componentes de la clase
	 */
	public void limpiarLista() {
		model.removeAllElements();
	}
	
	/**
	 * metodo que permite actualizar la lista
	 * @param nodo
	 */
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
	
	/**
	 * metodo que permite remover los elementos String de la list
	 */
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
				refrescarLista(dialogo.getPrincipal().getPartida().getRaiz());
				
			}catch (JugadorNoEncontradoException e1) {
				JOptionPane.showMessageDialog( this, "El juagdor: " + e1.getJugadorNoEncontrado() + " no ha sido encontrado "," ERROR... ",JOptionPane.ERROR_MESSAGE );
			}	
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
