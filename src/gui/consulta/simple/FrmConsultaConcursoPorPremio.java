package gui.consulta.simple;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Concurso;
import model.ConcursoModel;
import util.Validaciones;

public class FrmConsultaConcursoPorPremio extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDesde;
	private JTextField txtHasta;
	private JTable table;
	private JButton btnFiltrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					FrmConsultaConcursoPorPremio frame = new FrmConsultaConcursoPorPremio();
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
	public FrmConsultaConcursoPorPremio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultaConursoPor = new JLabel("Consulta Concurso por premio");
		lblConsultaConursoPor.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaConursoPor.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblConsultaConursoPor.setBounds(44, 33, 664, 31);
		contentPane.add(lblConsultaConursoPor);
		
		JLabel lblPremio = new JLabel("Premio");
		lblPremio.setBounds(54, 101, 46, 14);
		contentPane.add(lblPremio);
		
		txtDesde = new JTextField();
		txtDesde.setBounds(182, 98, 129, 20);
		contentPane.add(txtDesde);
		txtDesde.setColumns(10);
		
		txtHasta = new JTextField();
		txtHasta.setBounds(336, 99, 129, 20);
		contentPane.add(txtHasta);
		txtHasta.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(550, 98, 89, 23);
		contentPane.add(btnFiltrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 147, 625, 193);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Precio", "Premio"
			}
		));
		scrollPane.setViewportView(table);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnFiltrar) {
			handleBtnFiltrarActionPerformed(arg0);
		}
	}
	protected void handleBtnFiltrarActionPerformed(ActionEvent arg0) {
		String preIni = txtDesde.getText().trim();
		String preFin = txtHasta.getText().trim();
		
		if (!preIni.matches(Validaciones.PRECIO)) {
			mensaje("El precio Inicial es un decimal");	
		}else if (!preFin.matches(Validaciones.PRECIO)) {
			mensaje("El precio Final es un decimal");	
		}else if ( Double.parseDouble(preIni) > Double.parseDouble(preFin) ) {
			mensaje("El precio Final es un mayor que le precio Inicial");	
		}else {
			double dbIni = Double.parseDouble(preIni);
			double dbFin = Double.parseDouble(preFin);
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			
			ConcursoModel model = new ConcursoModel();
			List<Concurso> lista =  model.listaConcursoPorPremio(dbIni, dbFin);
			
			for (Concurso x : lista) {
				Object[] fila = {x.getIdConcurso(), x.getNombre(), x.getPrecio(), x.getPremio()};
				dtm.addRow(fila);
			} 
		}
		
		
	}
	
	void mensaje(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
}



