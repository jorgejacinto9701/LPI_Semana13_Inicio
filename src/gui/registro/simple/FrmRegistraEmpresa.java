package gui.registro.simple;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import entidad.Empresa;
import model.EmpresaModel;
import util.Conversiones;
import util.Validaciones;

public class FrmRegistraEmpresa extends JFrame implements ActionListener, KeyListener {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtRuc;
	private JTextField txtFec;
	private JButton btnRegistrar;
	private JComboBox<String> cboSede;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistraEmpresa frame = new FrmRegistraEmpresa();
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
	public FrmRegistraEmpresa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistroDeEmpresa = new JLabel("Registro de empresa");
		lblRegistroDeEmpresa.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRegistroDeEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDeEmpresa.setBounds(10, 11, 414, 30);
		contentPane.add(lblRegistroDeEmpresa);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(97, 74, 64, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(204, 71, 164, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblRuc = new JLabel("Ruc");
		lblRuc.setBounds(97, 105, 64, 14);
		contentPane.add(lblRuc);
		
		txtRuc = new JTextField();
		txtRuc.addKeyListener(this);
		txtRuc.setColumns(10);
		txtRuc.setBounds(204, 102, 164, 20);
		contentPane.add(txtRuc);
		
		JLabel lblFechaCreacin = new JLabel("Fecha Creaci\u00F3n");
		lblFechaCreacin.setBounds(97, 133, 97, 14);
		contentPane.add(lblFechaCreacin);
		
		txtFec = new JTextField();
		txtFec.setColumns(10);
		txtFec.setBounds(204, 130, 164, 20);
		contentPane.add(txtFec);
		
		JLabel lblSedes = new JLabel("Sedes");
		lblSedes.setBounds(97, 161, 64, 14);
		contentPane.add(lblSedes);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon(FrmRegistraEmpresa.class.getResource("/iconos/save.gif")));
		btnRegistrar.setBounds(166, 202, 141, 30);
		contentPane.add(btnRegistrar);
		
		cboSede = new JComboBox<String>();
		cboSede.addItem("[Seleccione]");
		cboSede.addItem("Lima");
		cboSede.addItem("Arequipa");
		cboSede.addItem("Tacna");
		cboSede.setBounds(204, 161, 164, 22);
		contentPane.add(cboSede);
	}
	
	
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			handle_btnRegistrar_actionPerformed(e);
		}
	}
	protected void handle_btnRegistrar_actionPerformed(ActionEvent e) {
			String nom = txtNombre.getText();
			String ruc = txtRuc.getText();
			String fec = txtFec.getText();
			String sed = cboSede.getSelectedItem().toString();
		
			if (!nom.matches(Validaciones.TEXTO)) {
				mensaje("El nombre es de 2 a 20 caracteres");
			}else if (!ruc.matches(Validaciones.RUC)) {
				mensaje("El ruc es de 11 dígitos");
			}else if (!fec.matches(Validaciones.FECHA)) {
				mensaje("La fecha es de formato yyyy-MM-dd");
			}else if (cboSede.getSelectedIndex() ==0) {
				mensaje("Seleccione la Sede");
			}else {
					Empresa obj = new Empresa();
					obj.setNombre(nom);
					obj.setRuc(ruc);
					obj.setFechaCreacion(Conversiones.toFecha(fec));
					obj.setSede(sed);
					obj.setEstado(1);
					
					EmpresaModel model = new EmpresaModel();
					int salida = model.insertaEmpresa(obj);
					if (salida>0) {
						mensaje("Registro exitoso");	
					}else {
						mensaje("Error en el registro");
					}
			}
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtRuc) {
			handle_txtRuc_keyTyped(e);
		}
	}
	protected void handle_txtRuc_keyTyped(KeyEvent e) {
		//Si no es dígito anulamos el evento
		if (!Character.isDigit(e.getKeyChar())) {
			e.consume();
		}
		
		//El ruc si es más de 11 anulamos el evento
		String ruc = txtRuc.getText() + e.getKeyChar();
		if (ruc.length()>11) {
			e.consume();
		}
		
	}
}










