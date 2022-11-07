package gui.crud.relacionada;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import util.JComboBoxBD;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class FrmCrudDirector extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JCheckBox chkEstado;
	private JComboBoxBD cboGrado;
	// Es el id de la fila seleccionado 
	// -1 ==> NO se selecciono NADA
	int idSeleccionado = -1;
	private JTextField txtFechaNacimiento;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	private JScrollPane scrollPane;
	private JTable table;
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
					FrmCrudDirector frame = new FrmCrudDirector();
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
	public FrmCrudDirector() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMantenimientoDirector = new JLabel("Mantenimiento Director");
		lblMantenimientoDirector.setOpaque(true);
		lblMantenimientoDirector.setBackground(Color.RED);
		lblMantenimientoDirector.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoDirector.setForeground(Color.WHITE);
		lblMantenimientoDirector.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMantenimientoDirector.setBounds(10, 11, 733, 59);
		contentPane.add(lblMantenimientoDirector);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 95, 84, 26);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(212, 98, 245, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon(FrmCrudDirector.class.getResource("/iconos/add.gif")));
		btnRegistrar.setBounds(629, 100, 114, 30);
		contentPane.add(btnRegistrar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setIcon(new ImageIcon(FrmCrudDirector.class.getResource("/iconos/edit.gif")));
		btnActualizar.setBounds(629, 182, 114, 30);
		contentPane.add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setIcon(new ImageIcon(FrmCrudDirector.class.getResource("/iconos/delete.gif")));
		btnEliminar.setBounds(629, 141, 114, 30);
		contentPane.add(btnEliminar);

	
		chkEstado = new JCheckBox("Activo");
		chkEstado.setSelected(true);
		chkEstado.setBounds(212, 189, 84, 23);
		contentPane.add(chkEstado);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(34, 132, 130, 14);
		contentPane.add(lblFechaDeNacimiento);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(212, 129, 186, 20);
		contentPane.add(txtFechaNacimiento);
		
		cboGrado = new JComboBoxBD(rb.getString("SQL_GRADO"));
		cboGrado.setBounds(212, 160, 185, 22);
		contentPane.add(cboGrado);
		
		JLabel lblGrado = new JLabel("Grado");
		lblGrado.setBounds(34, 157, 120, 25);
		contentPane.add(lblGrado);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 239, 723, 216);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Fecha Nacimiento", "Estado", "Grado"
			}
		));
		scrollPane.setViewportView(table);


	}



	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() {
		txtNombre.setText("");
		txtNombre.requestFocus();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizarJButton(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminarJButton(e);
		}
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrarJButton(e);
		}
	}
	protected void actionPerformedBtnRegistrarJButton(ActionEvent e) {
	}
	protected void actionPerformedBtnEliminarJButton(ActionEvent e) {
	}
	protected void actionPerformedBtnActualizarJButton(ActionEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			mouseClickedTableJTable(e);
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
	protected void mouseClickedTableJTable(MouseEvent e) {
	}
}



