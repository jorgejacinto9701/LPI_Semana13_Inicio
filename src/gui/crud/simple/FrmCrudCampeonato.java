package gui.crud.simple;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Campeonato;
import model.CampeonatoModel;
import util.Validaciones;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class FrmCrudCampeonato extends JFrame implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtAnno;
	private JTable table;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JCheckBox chkEstado;
	
	// Es el id de la fila seleccionado 
	// -1 ==> NO se selecciono NADA
	int idSeleccionado = -1;

	// ModelCampeonato-->Es la clase donde estan los
	// métodos insert, update, delete, listar en la BD

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
					FrmCrudCampeonato frame = new FrmCrudCampeonato();
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
	public FrmCrudCampeonato() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMantenimientoCampeonato = new JLabel("Mantenimiento Campeonato");
		lblMantenimientoCampeonato.setOpaque(true);
		lblMantenimientoCampeonato.setBackground(Color.RED);
		lblMantenimientoCampeonato.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoCampeonato.setForeground(Color.WHITE);
		lblMantenimientoCampeonato.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMantenimientoCampeonato.setBounds(10, 11, 508, 59);
		contentPane.add(lblMantenimientoCampeonato);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(105, 95, 84, 26);
		contentPane.add(lblNombre);

		JLabel lblAnno = new JLabel("A\u00F1o");
		lblAnno.setBounds(104, 132, 46, 26);
		contentPane.add(lblAnno);

		txtNombre = new JTextField();
		txtNombre.setBounds(184, 98, 211, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtAnno = new JTextField();
		txtAnno.setBounds(183, 135, 86, 20);
		contentPane.add(txtAnno);
		txtAnno.setColumns(10);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon(FrmCrudCampeonato.class.getResource("/iconos/add.gif")));
		btnRegistrar.setBounds(57, 216, 114, 33);
		contentPane.add(btnRegistrar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setIcon(new ImageIcon(FrmCrudCampeonato.class.getResource("/iconos/edit.gif")));
		btnActualizar.setBounds(357, 216, 114, 33);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(FrmCrudCampeonato.class.getResource("/iconos/delete.gif")));
		btnEliminar.setBounds(205, 216, 114, 33);
		contentPane.add(btnEliminar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 260, 508, 184);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nombre", "A\u00F1o", "Estado"
			}
		));
		scrollPane.setViewportView(table);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setSelected(true);
		chkEstado.setBounds(185, 175, 84, 23);
		contentPane.add(chkEstado);

		// Traer todos los campeonatos de la BD
		lista();
	}



	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() {
		txtNombre.setText("");
		txtAnno.setText("");
		txtNombre.requestFocus();
	}

	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			handle_btnActualizar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			handle_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnRegistrar) {
			handle_btnRegistrar_actionPerformed(e);
		}
	}
	protected void handle_btnRegistrar_actionPerformed(ActionEvent e) {
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
		CampeonatoModel model = new CampeonatoModel();
		List<Campeonato> lista = model.listaTodos();
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		Object[] fila = null; 
		for (Campeonato x : lista) {
			fila = new Object[]{ x.getIdCampeonato(), x.getNombre(), x.getAnnio(), getFormatEstado(x.getEstado())};
			dtm.addRow(fila);
		}
	}
	private void inserta() {
		String nom = txtNombre.getText().trim();
		String anio = txtAnno.getText().trim();
		
		if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 3 a 20 caracteres");
		}else if (!anio.matches(Validaciones.ANHO)) {
			mensaje("El año es de 4 dígitos");
		}else {
			Campeonato obj = new Campeonato();
			obj.setNombre(nom);
			obj.setAnnio(Integer.parseInt(anio));
			
			CampeonatoModel model = new CampeonatoModel();
			int salida = model.insertaCampeonato(obj);
			
			if (salida > 0) {
				idSeleccionado = -1;
				lista();
				limpiarCajasTexto();
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
		int anio =  (Integer)table.getValueAt(fila, 2);
		String estado =  (String)table.getValueAt(fila, 3);
		
		System.out.println(idSeleccionado + " - " +  nombre + " - " + anio + " - " + estado);
		
		txtNombre.setText(nombre);
		txtAnno.setText(String.valueOf(anio));
		chkEstado.setSelected(getBooleanEstado(estado));
	}
	
	private void actualiza() {
		String nom = txtNombre.getText().trim();
		String anio = txtAnno.getText().trim();
		boolean estado = chkEstado.isSelected();
		
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		}else if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 3 a 20 caracteres");
		}else if (!anio.matches(Validaciones.ANHO)) {
			mensaje("El año es de 4 dígitos");
		}else {
			Campeonato obj = new Campeonato();
			obj.setIdCampeonato(idSeleccionado);
			obj.setNombre(nom);
			obj.setAnnio(Integer.parseInt(anio));
			obj.setEstado(getIntegerEstado(estado));
			
			CampeonatoModel model = new CampeonatoModel();
			int salida = model.actualizaCampeonato(obj);
			
			if (salida > 0) {
				idSeleccionado = -1;
				lista();
				limpiarCajasTexto();
				mensaje("Se actualizó correctamente");
			}else {
				mensaje("Error en la Actualización");
			}
		}
		
	}
	
	private void elimina() {
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		}else {
			CampeonatoModel model = new CampeonatoModel();
			int salida = model.eliminaCampeonato(idSeleccionado);
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
	
	private String getFormatEstado(int estado) {
		return estado == 1 ? "Activo" : "Inactivo";
	}
	
	private boolean getBooleanEstado(String estado) {
		return estado == "Activo" ? true : false;
	}
	
	private int getIntegerEstado(boolean estado) {
		return estado ? 1 : 0;
	}
}



