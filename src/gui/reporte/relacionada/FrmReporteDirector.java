package gui.reporte.relacionada;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import entidad.Director;
import model.DirectorModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.GeneradorReporte;

public class FrmReporteDirector extends JFrame implements ItemListener, ActionListener {

	private static Logger log = Logger.getLogger(FrmReporteDirector.class.getName());
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JLabel lblFiltro;
	private JTextField txtFiltro;
	private JPanel panelReporte;
	private JButton btnFiltrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
					FrmReporteDirector frame = new FrmReporteDirector();
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
	public FrmReporteDirector() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1208, 666);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("Reporte de Director");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(10, 31, 1055, 36);
		contentPane.add(lblTitulo);
		
		lblFiltro = new JLabel("Nombre");
		lblFiltro.setBounds(27, 95, 69, 14);
		contentPane.add(lblFiltro);
		
		txtFiltro = new JTextField();
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(124, 91, 149, 20);
		contentPane.add(txtFiltro);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(null, "Reportes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelReporte.setBounds(27, 155, 1144, 454);
		contentPane.add(panelReporte);
		panelReporte.setLayout(new BorderLayout());
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(341, 91, 162, 23);
		contentPane.add(btnFiltrar);
		
	}

	
	public void itemStateChanged(ItemEvent e) {
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrarJButton(e);
		}
	}
	protected void actionPerformedBtnFiltrarJButton(ActionEvent e) {
		String filtro = txtFiltro.getText().trim();
		
		DirectorModel model = new DirectorModel();
		List<Director> lista = model.listaPorNombre(filtro +"%");
		
		//Datos del reporte
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
		
		//Diseño del reporte
		String jasper = "reporteDirector.jasper";	
		
		//Obtengo la ruta relativa de la imagen
		String rutaImagen = FrmReporteDirector.class.getResource("/logos/Mercedes_Logo_.jpg").getPath();
		log.info(" rutaImagen >>" + rutaImagen);
		
		//Envio la ruta relativa como parámetro
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("RUTA_IMAGEN",rutaImagen);

		//Se obtiene el reporte
		JasperPrint print = GeneradorReporte.genera(jasper, dataSource, parametros);
		
		JRViewer jRViewer = new JRViewer(print);
		
		panelReporte.removeAll();
		panelReporte.add(jRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
			
	}
}








