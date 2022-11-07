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

import entidad.Club;
import model.ClubModel;
import util.JComboBoxBD;

public class FrmConsultaClub extends JFrame implements ItemListener   {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBoxBD cboPais;
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
					FrmConsultaClub frame = new FrmConsultaClub();
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
	public FrmConsultaClub() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1022, 574);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultaDeCampeonatos = new JLabel("Consulta de club");
		lblConsultaDeCampeonatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeCampeonatos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConsultaDeCampeonatos.setBounds(10, 26, 986, 36);
		contentPane.add(lblConsultaDeCampeonatos);
		
		JLabel lblPais = new JLabel("Pa\u00EDs");
		lblPais.setBounds(59, 104, 46, 14);
		contentPane.add(lblPais);
		
		cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"), "[Todos]");
		cboPais.addItemListener(this);
		cboPais.setBounds(175, 100, 313, 22);
		contentPane.add(cboPais);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 160, 945, 336);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Fecha Creaci\u00F3n", "Fecha Registro", "Estado", "Pa\u00EDs"
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
		if (e.getSource() == cboPais) {
			itemStateChangedCboPaisJComboBox(e);
		}
	}
	protected void itemStateChangedCboPaisJComboBox(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			int index = cboPais.getSelectedIndex();
			String item = cboPais.getSelectedItem().toString();
			
			System.out.println("Index ==> " + index);
			System.out.println("Item ==> " + item);
			
			ClubModel model = new ClubModel();
			List<Club> lstClub = null;
			
			if (index == 0) {
				lstClub = new ArrayList<Club>();
			}else if (index == 1) {
				lstClub = model.listaTodos();
			}else {
				String[] separado = item.split(":");
				int idPais = Integer.parseInt(separado[0]);
				lstClub = model.listaPorPais(idPais);
			}
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			
			for (Club x : lstClub) {
				Object[] f = {x.getIdClub(), 
							  x.getNombre(), 
							  x.getFechaCreacion(), 
							  x.getFechaRegistro(), 
							  x.getEstado()==1?"Activo":"Inactivo",
							  x.getPais().getNombre()};
				dtm.addRow(f);
			}
			
			
			
		}
	}
}










