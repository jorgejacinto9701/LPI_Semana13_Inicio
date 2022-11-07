package gui.consulta.simple;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class FrmConsultaEquipoPorNombre extends JFrame  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFiltro;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					FrmConsultaEquipoPorNombre frame = new FrmConsultaEquipoPorNombre();
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
	public FrmConsultaEquipoPorNombre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultaEquipoPor = new JLabel("Consulta equipo por nombre");
		lblConsultaEquipoPor.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaEquipoPor.setBounds(10, 24, 622, 40);
		lblConsultaEquipoPor.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblConsultaEquipoPor);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(88, 94, 133, 14);
		contentPane.add(lblNombre);
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(231, 91, 387, 20);
		contentPane.add(txtFiltro);
		txtFiltro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 140, 671, 219);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Pa\u00EDs"
			}
		));
		scrollPane.setViewportView(table);
	}
	
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
}