package gui.crud.simple;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Jugador;
import model.JugadorModel;
import util.Conversiones;
import util.Validaciones;

public class FrmCrudJugador extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtFecha;
	private JButton btnIngresar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JCheckBox chkEstado;
	private JScrollPane scrollPane;
	private JTable table;
	// -1 indica que no se ha selecionado nada en la grilla o Jtable
	private int idSeleccionado = -1;

	int hoveredRow = -1, hoveredColumn = -1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudJugador frame = new FrmCrudJugador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCrudJugador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMantenimientoDeJugador = new JLabel("Mantenimiento de Jugador");
		lblMantenimientoDeJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoDeJugador.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMantenimientoDeJugador.setBounds(10, 24, 641, 38);
		contentPane.add(lblMantenimientoDeJugador);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(50, 95, 130, 14);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(50, 135, 130, 14);
		contentPane.add(lblApellido);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(50, 176, 130, 14);
		contentPane.add(lblFechaDeNacimiento);

		txtNombre = new JTextField();
		txtNombre.setBounds(259, 92, 181, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setBounds(259, 132, 181, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);

		txtFecha = new JTextField();
		txtFecha.setBounds(259, 173, 146, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(this);
		btnIngresar.setIcon(new ImageIcon(FrmCrudJugador.class.getResource("/iconos/add.gif")));
		btnIngresar.setBounds(495, 91, 130, 30);
		contentPane.add(btnIngresar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setIcon(new ImageIcon(FrmCrudJugador.class.getResource("/iconos/edit.gif")));
		btnActualizar.setBounds(495, 168, 130, 30);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(FrmCrudJugador.class.getResource("/iconos/delete.gif")));
		btnEliminar.setBounds(495, 127, 130, 30);
		contentPane.add(btnEliminar);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setSelected(true);
		chkEstado.setBounds(259, 209, 97, 30);
		contentPane.add(chkEstado);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 246, 611, 166);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellido", "Fecha nacimiento", "Estado"
			}
		));
		
		
		//alineación
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		//tamano de la fila	
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		//selecciona una sola fila
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//desabilita mover las columnas
		table.getTableHeader().setReorderingAllowed(false);
		
		scrollPane.setViewportView(table);
		
		//color de la fila seleccionada
		table.setSelectionBackground(Color.GREEN);
		
	    //No se pueda editar
	    table.setDefaultEditor(Object.class, null);
	    
		
	    
		lista();
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtFecha.setText("");
		txtNombre.requestFocus();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			handle_btnActualizar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			handle_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnIngresar) {
			handle_btnIngresar_actionPerformed(e);
		}
	}
	protected void handle_btnIngresar_actionPerformed(ActionEvent e) {
		inserta();
	}
	protected void handle_btnEliminar_actionPerformed(ActionEvent e) {
		elimina();
	}
	protected void handle_btnActualizar_actionPerformed(ActionEvent e) {
		actualiza();
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			handle_table_mouseClicked(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void handle_table_mouseClicked(MouseEvent e) {
		busca();
	}
	
	private void lista() {
		JugadorModel model = new JugadorModel();
		List<Jugador> lista = model.listaJugador();
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		Object[] fila = null;
		for (Jugador x : lista) {
			fila = new Object[] {x.getIdJugador(), x.getNombre(), x.getApellido(), x.getFechaNacimiento(), getFormatoEstado(x.getEstado())};
			dtm.addRow(fila);
		}
	}
	
	private void inserta() {
		String nom = txtNombre.getText();
		String ape = txtApellido.getText();
		String fec = txtFecha.getText();
		
		if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje("El apellido es de 2 a 20 caracteres");
		}else if (!fec.matches(Validaciones.FECHA)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else {
			Jugador obj = new Jugador();
			obj.setNombre(nom);
			obj.setApellido(ape);
			obj.setFechaNacimiento(Conversiones.toFecha(fec));
			
			JugadorModel model = new JugadorModel();
			int salida = model.insertaJugador(obj);
			
			if (salida > 0) {
				lista();
				limpiarCajasTexto();
				idSeleccionado = -1;
				mensaje("Se insertó correctamente");
			}else {
				mensaje("Error en el Registro");
			}
			
		}	
	}
	
	private void busca() {
		int fila = table.getSelectedRow();
		
		idSeleccionado = (Integer)table.getValueAt(fila, 0);
		String nombre =  (String)table.getValueAt(fila, 1);
		String apellido =  (String)table.getValueAt(fila, 2);
		Date fecha =  (Date)table.getValueAt(fila, 3);
		String estado =  (String)table.getValueAt(fila, 4);
		
		System.out.println(idSeleccionado + " - " + nombre + " - " + apellido + " - " + fecha + " - " + estado);
		
		txtNombre.setText(nombre);
		txtApellido.setText(apellido);
		txtFecha.setText(String.valueOf(fecha));
		chkEstado.setSelected(getBooleanEstado(estado));
	}
	
	private void elimina() {
		if (idSeleccionado == -1) {
			mensaje("Seleccione una Fila");
		}else {
			JugadorModel model = new JugadorModel();
			int salida = model.eliminaJugador(idSeleccionado);
			
			if (salida > 0) {
				lista();
				idSeleccionado = -1;
				limpiarCajasTexto();
				mensaje("Se eliminó correctamente");
			}else {
				mensaje("Error en la eliminación");
			}
		}
	}
	
	private void actualiza() {
		String nom = txtNombre.getText();
		String ape = txtApellido.getText();
		String fec = txtFecha.getText();
		boolean estado = chkEstado.isSelected();
		
		if (idSeleccionado == -1) {
			mensaje("Seleccione una Fila");
		}else if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje("El apellido es de 2 a 20 caracteres");
		}else if (!fec.matches(Validaciones.FECHA)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else {
			Jugador obj = new Jugador();
			obj.setIdJugador(idSeleccionado);
			obj.setNombre(nom);
			obj.setApellido(ape);
			obj.setFechaNacimiento(Conversiones.toFecha(fec));
			obj.setEstado(getIntegerEstado(estado));
			
			JugadorModel model = new JugadorModel();
			int salida = model.actualizaJugador(obj);
			
			if (salida > 0) {
				lista();
				limpiarCajasTexto();
				idSeleccionado = -1;
				mensaje("Se actulizó correctamente");
			}else {
				mensaje("Error en la actualización");
			}
			
		}	
	}
	
	private String getFormatoEstado(int estado) {
		return estado == 1? "Activo":"Inactivo";
	}
	private boolean getBooleanEstado(String estado) {
		return estado == "Activo"? true:false;
	}
	private int getIntegerEstado(boolean estado) {
		return estado? 1:0;
	}
}



