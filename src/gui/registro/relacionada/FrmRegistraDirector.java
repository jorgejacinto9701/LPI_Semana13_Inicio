package gui.registro.relacionada;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import entidad.Director;
import entidad.Grado;
import model.DirectorModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmRegistraDirector extends JFrame implements ActionListener 
							{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Variables globales
	JLabel lblTitulo, lblNombre, lblGrado, lblFecha;
	JTextField  txtNombre, txtFecha;
	JButton btnRegistrar;
	private JComboBoxBD cboGrado;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	
	//Constructor
	public FrmRegistraDirector(){
		setTitle("Registro de Jugador");
		setBounds(10,10,544,312);
		getContentPane().setLayout(null);
		
		lblTitulo = new JLabel("Registro de Director");
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		lblTitulo.setForeground(Color.RED);
		lblTitulo.setBackground(Color.WHITE);
		lblTitulo.setOpaque(true);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 15));            
		lblTitulo.setBounds(0, 10, 528, 25);
		getContentPane().add(lblTitulo);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(30, 80, 120, 25);
		getContentPane().add(lblNombre);		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(200, 80, 284, 25);
		getContentPane().add(txtNombre);

		lblGrado = new JLabel("Grado");
		lblGrado.setBounds(30, 152, 120, 25);
		getContentPane().add(lblGrado);
		
		lblFecha = new JLabel("Fecha nacimiento");
		lblFecha.setBounds(30, 116, 120, 25);
		getContentPane().add(lblFecha);		
		
		txtFecha = new JTextField();
		txtFecha.setBounds(200, 116, 185, 25);
		getContentPane().add(txtFecha);	
	
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setIcon(new ImageIcon(FrmRegistraDirector.class.getResource("/iconos/save.gif")));
		btnRegistrar.setBounds(213,201,120,33);
		getContentPane().add(btnRegistrar);
		
		cboGrado = new JComboBoxBD(rb.getString("SQL_GRADO"));
		cboGrado.setBounds(200, 153, 185, 22);
		getContentPane().add(cboGrado);

	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		FrmRegistraDirector frm = new FrmRegistraDirector();
		frm.setVisible(true);
	}


	
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrarJButton(e);
		}
	}
	protected void actionPerformedBtnRegistrarJButton(ActionEvent e) {
			String nom = txtNombre.getText().trim();
			String fec = txtFecha.getText().trim();
			int indexGrado = cboGrado.getSelectedIndex();
			
			if (!nom.matches(Validaciones.TEXTO)) {
				mensaje("El nombre es de 2 a 20 caracteres");
			}else if (!fec.matches(Validaciones.FECHA)) {
				mensaje("La fecha tiene formato YYYY-MM-dd");
			}else if (indexGrado ==0) {
				mensaje("Seleccione un grado");
			}else {
				String grado = cboGrado.getSelectedItem().toString();
				String idGrado = grado.split(":")[0];
				
				Grado objGrado = new Grado();
				objGrado.setIdGrado(Integer.parseInt(idGrado));
				
				Director objDirector = new Director();
				objDirector.setNombre(nom);
				objDirector.setFechaNacimiento(Date.valueOf(fec));
				objDirector.setGrado(objGrado);
				
				DirectorModel model = new DirectorModel();
				int salida = model.insertaDirector(objDirector);
				if (salida > 0) {
					mensaje("Se insertó correctamente");
				}else {
					mensaje("Error en el Registro");
				}
				
			}
		
	}
}

















