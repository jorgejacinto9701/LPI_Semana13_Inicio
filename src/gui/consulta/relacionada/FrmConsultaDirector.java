package gui.consulta.relacionada;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Director;
import model.DirectorModel;
import util.JComboBoxBD;

public class FrmConsultaDirector extends JFrame implements ItemListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBoxBD cboGrado;
	private JTable table;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					FrmConsultaDirector frame = new FrmConsultaDirector();
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
	public FrmConsultaDirector() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1091, 554);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultaConursoPor = new JLabel("Consulta director");
		lblConsultaConursoPor.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaConursoPor.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblConsultaConursoPor.setBounds(29, 33, 1178, 31);
		contentPane.add(lblConsultaConursoPor);
		
		JLabel lblGrado = new JLabel("Grado");
		lblGrado.setBounds(39, 89, 46, 14);
		contentPane.add(lblGrado);
		
		cboGrado = new JComboBoxBD(rb.getString("SQL_GRADO"), "[Todos]");
		cboGrado.addItemListener(this);
		cboGrado.setBounds(153, 85, 361, 22);
		contentPane.add(cboGrado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 149, 1019, 338);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Fecha Nacimiento", "Fecha Registro", "Estado", "Grado"
			}
		));
		
		//Tamaño a las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		scrollPane.setViewportView(table);
		
		//desabilita mover las columnas
		table.getTableHeader().setReorderingAllowed(false);
		
		//desabilita el cambio de tamaño
		table.getTableHeader().setResizingAllowed(false);
		 
		//selecciona una sola fila
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		//Desabilitar la edicion en las celdas
		table.setDefaultEditor(Object.class, null);
		
		//Colores alternados de la fila
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		defaults.putIfAbsent("Table.alternateRowColor", new Color(176, 245, 215));
		
		//Alineación
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		scrollPane.setViewportView(table);
	}

	
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboGrado) {
			itemStateChangedCboGradoJComboBox(e);
		}
	}
	protected void itemStateChangedCboGradoJComboBox(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			int index = cboGrado.getSelectedIndex();
			String item = cboGrado.getSelectedItem().toString();
			
			System.out.println("Index ==> " + index);
			System.out.println("Item ==> " + item);
			
			DirectorModel model = new DirectorModel();
			List<Director> lst = null;
			
			if (index == 0) {
				lst = new ArrayList<Director>();
			}else if (index == 1) {
				lst = model.listaTodos();
			}else {
				String[] separado = item.split(":");
				int idGrado = Integer.parseInt(separado[0]);
				lst = model.listaPorGrado(idGrado);
			}
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			
			for (Director x : lst) {
				Object[] f = {x.getIdDirector(), 
							  x.getNombre(), 
							  x.getFechaNacimiento(), 
							  x.getFechaRegistro(), 
							  x.getEstado()==1?"Activo":"Inactivo",
							  x.getGrado().getNombre()};
				dtm.addRow(f);
			}
			
			
			
		}
	}
}



