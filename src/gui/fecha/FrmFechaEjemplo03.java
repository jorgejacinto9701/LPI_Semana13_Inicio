package gui.fecha;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import util.FechaUtil;
import util.Validaciones;

public class FrmFechaEjemplo03 extends JFrame implements ActionListener {

	/**
	 * Caso 3
	 * Validar que el rango de fecha no se mayor que seis meses (180 días)
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFechaInicio;
	private JTextField txtFechaFin;
	private JButton btnFiltrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmFechaEjemplo03 frame = new FrmFechaEjemplo03();
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
	public FrmFechaEjemplo03() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setBounds(28, 56, 81, 14);
		contentPane.add(lblFechaInicio);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setBounds(128, 53, 119, 20);
		contentPane.add(txtFechaInicio);
		txtFechaInicio.setColumns(10);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(319, 56, 90, 14);
		contentPane.add(lblFechaFin);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setBounds(419, 53, 109, 20);
		contentPane.add(txtFechaFin);
		txtFechaFin.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(564, 52, 89, 23);
		contentPane.add(btnFiltrar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrarJButton(e);
		}
	}
	protected void actionPerformedBtnFiltrarJButton(ActionEvent e) {
		String fecIni = txtFechaInicio.getText().trim();
		String fecFin = txtFechaFin.getText().trim();
		
		if (!fecIni.matches(Validaciones.FECHA)) {
			mensaje("La Fecha inicio tiene formato yyyy-MM-dd");
		}else if (!fecFin.matches(Validaciones.FECHA)) {
			mensaje("La Fecha fin tiene formato yyyy-MM-dd");
		}else if (FechaUtil.isNotSuperiorFechaYYYYMMdd(fecIni, fecFin)) {
			mensaje("La Fecha fin es superior a la Fecha inicio");
		}else if (FechaUtil.isNotSuperiorSeisMesesFechaYYYYMMdd(fecIni, fecFin)) {
			mensaje("La Fecha fin debe ser menor a 6 meses(180 días)");
		}
		
	}
	
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	
}










