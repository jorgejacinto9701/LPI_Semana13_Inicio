package gui.consulta.simple;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Docente;
import model.DocenteModel;
import util.FechaUtil;
import util.Validaciones;

public class FrmConsultaDocentePorFecha extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtInicio;
	private JTextField txtFin;
	private JTable table;
	private JButton btnFiltrar;

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
					FrmConsultaDocentePorFecha frame = new FrmConsultaDocentePorFecha();
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
	public FrmConsultaDocentePorFecha() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConsultaDeDirector = new JLabel("Consulta de docente por fecha de nacimiento");
		lblConsultaDeDirector.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConsultaDeDirector.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeDirector.setBounds(10, 31, 674, 48);
		contentPane.add(lblConsultaDeDirector);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio");
		lblFechaDeInicio.setBounds(10, 132, 104, 20);
		contentPane.add(lblFechaDeInicio);
		
		JLabel lblFechaDeFin = new JLabel("Fecha de Fin");
		lblFechaDeFin.setBounds(256, 135, 85, 14);
		contentPane.add(lblFechaDeFin);
		
		txtInicio = new JTextField();
		txtInicio.setBounds(97, 132, 149, 20);
		contentPane.add(txtInicio);
		txtInicio.setColumns(10);
		
		txtFin = new JTextField();
		txtFin.setBounds(348, 132, 149, 20);
		contentPane.add(txtFin);
		txtFin.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(522, 131, 162, 23);
		contentPane.add(btnFiltrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 165, 664, 222);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nombre", "DNI","Fecha Nacimiento", 
			}
		));
		scrollPane.setViewportView(table);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnFiltrar) {
			do_btnFiltrar_actionPerformed(arg0);
		}
	}
	protected void do_btnFiltrar_actionPerformed(ActionEvent arg0) {
		String fecIni  = txtInicio.getText().trim();
		String fecFin  = txtFin.getText().trim();
		
		if (!fecIni.matches(Validaciones.FECHA)) {
			mensaje("La fecha Inicio tiene formato yyyy-MM-dd");
		}else if (!fecFin.matches(Validaciones.FECHA)) {
			mensaje("La fecha Fin tiene formato yyyy-MM-dd");
		}else if (FechaUtil.isNotSuperiorFechaYYYYMMdd(fecIni, fecFin)) {
			mensaje("La Fecha fin es superior a la Fecha inicio");
		}else if (FechaUtil.isNotSuperiorSeisMesesFechaYYYYMMdd(fecIni, fecFin)) {
			mensaje("La Fecha fin debe ser mayor que la fecha Inicio en máximo 6 meses (180 días)");
		}else {
			Date dtIni = Date.valueOf(fecIni);
			Date dtFin = Date.valueOf(fecFin);
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			
			DocenteModel model = new DocenteModel();
			List<Docente> lista =  model.listaDocentePorFecha(dtIni, dtFin);
			
			for (Docente x : lista) {
				Object[] fila = {x.getIdDocente(), x.getNombre(), x.getDni(), x.getFechaNacimiento()};
				dtm.addRow(fila);
			}
					
		}
		
	}
	
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
}











